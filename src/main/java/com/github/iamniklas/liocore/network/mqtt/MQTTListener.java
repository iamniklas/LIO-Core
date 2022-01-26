package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

public class MQTTListener {
    String clientID = UUID.randomUUID().toString();
    IMqttClient mqttClient = null;

    IMqttCallback callback;

    public MQTTListener(IMqttCallback _callback) {
        callback = _callback;

        System.out.println("---------------");
        System.out.println("---MQTT Integration---");
        System.out.println("Device Name: " + ProgramConfiguration.configuration.mqttDeviceName);
        System.out.println("---------------");
        System.out.println("Send procedure update to this device:   " + Topics.UPDATE_LISTEN);
        System.out.println("Send procedure update to all devices:   " + Topics.UPDATE_ALL_LISTEN_PUBLISH);
        System.out.println("Send value update to this device:       " + Topics.VARIABLE_UPDATE_LISTEN);
        System.out.println("Send value update to all devices:       " + Topics.VARIABLE_UPDATE_ALL_LISTEN_PUBLISH);
        System.out.println("---------------");
        System.out.println("---------------");
    }

    public void connect() throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        mqttClient = new MqttClient(ProgramConfiguration.configuration.mqttBrokerAddress, clientID, persistence);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(ProgramConfiguration.configuration.mqttAutomaticReconnect);
        options.setCleanSession(ProgramConfiguration.configuration.mqttCleanSession);
        options.setConnectionTimeout(ProgramConfiguration.configuration.mqttConnectionTimeout);
        options.setUserName(ProgramConfiguration.configuration.mqttUser);
        options.setPassword(ProgramConfiguration.configuration.mqttPassword.toCharArray());

        mqttClient.connect(options);


        mqttClient.subscribe(Topics.UPDATE_LISTEN, (topic, message) ->
                callback.onLEDUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class)));

        mqttClient.subscribe(Topics.UPDATE_ALL_LISTEN_PUBLISH, (topic, message) ->
                callback.onLEDUpdateModelReceiveAll(new Gson().fromJson(message.toString(), LEDUpdateModel.class)));


        mqttClient.subscribe(Topics.VARIABLE_UPDATE_LISTEN, (topic, message) ->
                callback.onLEDValueUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class)));

        mqttClient.subscribe(Topics.VARIABLE_UPDATE_ALL_LISTEN_PUBLISH, (topic, message) ->
                callback.onLEDValueUpdateModelReceiveAll(new Gson().fromJson(message.toString(), LEDUpdateModel.class)));
    }

    public void disconnect() throws MqttException {
        mqttClient.disconnect();
    }

    public String getDeviceIdentifier() {
        return ProgramConfiguration.configuration.mqttDeviceName;
    }
}
