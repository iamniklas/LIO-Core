package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.LEDValueUpdateModel;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.UUID;

public class MQTTListener {
    String clientID = UUID.randomUUID().toString();
    IMqttClient mqttClient = null;

    IMqttCallback callback = null;

    String macAddress;
    String hostname = "";
    String deviceIdentifier = "";

    public MQTTListener(IMqttCallback _callback) {
        callback = _callback;
        try {
            byte[] binaryMacAddress = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            String[] hexadecimal = new String[binaryMacAddress.length];
            for (int i = 0; i < binaryMacAddress.length; i++) {
                hexadecimal[i] = String.format("%02X", binaryMacAddress[i]);
            }

            macAddress = String.join("-", hexadecimal);
            hostname = InetAddress.getLocalHost().getHostName();
            deviceIdentifier = String.format("%s:%s", hostname, macAddress);
        } catch (SocketException | UnknownHostException | NullPointerException e) {
            //TODO: This is only a temporary solution, improve in an upcoming update
            hostname="any";
            macAddress="any";
            System.out.println("Unable to gather network address information");
        }
        finally {
            deviceIdentifier = String.format("%s:%s", hostname, macAddress);
            System.out.println("---------------");
            System.out.println("---MQTT Integration---");
            System.out.println("Hostname: " + hostname);
            System.out.println("Mac Address: " + macAddress);
            System.out.println("Device Identifier: " + deviceIdentifier);
            System.out.println("If Hostname and/or Mac Address is 'any', we couldn't get the network address information for this device");
            System.out.println("---------------");
            System.out.println("Send procedure update to this device:   led/update/"+deviceIdentifier);
            System.out.println("Send procedure update to all devices:   led/update/all");
            System.out.println("Send value update to this device:       led/update/variable/"+deviceIdentifier);
            System.out.println("Send value update to all devices:       led/update/variable/all");
            System.out.println("---------------");
            System.out.println("---------------");
        }
    }

    public void connect() throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        mqttClient = new MqttClient("tcp://000raspberry.ddns.net:1883", clientID, persistence);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setUserName("lio");
        options.setPassword("8kNhtmUG6kmUm3djdEE7MXmvAg4662".toCharArray());
        mqttClient.connect(options);

        mqttClient.subscribe(Topics.UPDATE+deviceIdentifier, (topic, message) ->
                callback.onLEDUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class)));
        mqttClient.subscribe(Topics.UPDATE_ALL, (topic, message) ->
                callback.onLEDUpdateModelReceiveAll(new Gson().fromJson(message.toString(), LEDUpdateModel.class)));

        mqttClient.subscribe(Topics.VARIABLE_UPDATE+deviceIdentifier, (topic, message) ->
                callback.onLEDValueUpdateModelReceive(new Gson().fromJson(message.toString(), LEDValueUpdateModel.class)));
        mqttClient.subscribe(Topics.VARIABLE_UPDATE_ALL, (topic, message) ->
                callback.onLEDValueUpdateModelReceiveAll(new Gson().fromJson(message.toString(), LEDValueUpdateModel.class)));
    }

    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }
}
