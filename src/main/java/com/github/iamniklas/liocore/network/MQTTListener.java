package com.github.iamniklas.liocore.network;

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

    byte[] macAddress;

    public MQTTListener(IMqttCallback _callback) {
        callback = _callback;
        try {
            macAddress = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            String[] hexadecimal = new String[macAddress.length];
            for (int i = 0; i < macAddress.length; i++) {
                hexadecimal[i] = String.format("%02X", macAddress[i]);
            }
            String macAddress = String.join("-", hexadecimal);
            System.out.println(macAddress);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    void connect() throws MqttException {
        mqttClient = new MqttClient("tcp://000raspberry.ddns.net:1883", clientID);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setUserName("lio");
        options.setPassword("8kNhtmUG6kmUm3djdEE7MXmvAg4662".toCharArray());
        mqttClient.connect(options);


        mqttClient.subscribe("led/update/testdevice", (topic, message) ->
                callback.onLEDUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class)));

        mqttClient.subscribe("led/valueupdate/testdevice", (topic, message) ->
                callback.onLEDValueUpdateModelReceive(new Gson().fromJson(message.toString(), LEDValueUpdateModel.class)));
    }
}
