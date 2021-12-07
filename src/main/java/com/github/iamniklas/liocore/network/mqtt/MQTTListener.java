package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.LEDValueUpdateModel;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

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
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws MqttException {
        mqttClient = new MqttClient("tcp://000raspberry.ddns.net:1883", clientID);

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
                callback.onLEDUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class)));

        mqttClient.subscribe(Topics.VARIABLE_UPDATE+deviceIdentifier, (topic, message) ->
                callback.onLEDValueUpdateModelReceive(new Gson().fromJson(message.toString(), LEDValueUpdateModel.class)));
        mqttClient.subscribe(Topics.VARIABLE_UPDATE_ALL, (topic, message) ->
                callback.onLEDValueUpdateModelReceiveAll(new Gson().fromJson(message.toString(), LEDValueUpdateModel.class)));
    }
}
