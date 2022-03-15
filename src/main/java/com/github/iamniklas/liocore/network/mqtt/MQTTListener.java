package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

public class MQTTListener implements MqttCallback {
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

    public int connect() {
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            mqttClient = new MqttClient(ProgramConfiguration.configuration.mqttBrokerAddress, clientID, persistence);
            mqttClient.setCallback(this);

            MqttConnectOptions options = new MqttConnectOptions();

            options.setCleanSession(ProgramConfiguration.configuration.mqttCleanSession);
            options.setAutomaticReconnect(ProgramConfiguration.configuration.mqttAutomaticReconnect);
            options.setConnectionTimeout(ProgramConfiguration.configuration.mqttConnectionTimeout);
            options.setExecutorServiceTimeout(ProgramConfiguration.configuration.mqttConnectionTimeout);
            options.setUserName(ProgramConfiguration.configuration.mqttUser);
            options.setPassword(ProgramConfiguration.configuration.mqttPassword.toCharArray());

            mqttClient.connect(options);

            mqttClient.subscribe(Topics.UPDATE_LISTEN, (topic, message) ->
                    callback.onLEDUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class), false));

            mqttClient.subscribe(Topics.UPDATE_ALL_LISTEN_PUBLISH, (topic, message) ->
                    callback.onLEDUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class), true));


            mqttClient.subscribe(Topics.VARIABLE_UPDATE_LISTEN, (topic, message) ->
                    callback.onLEDValueUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class), false));

            mqttClient.subscribe(Topics.VARIABLE_UPDATE_ALL_LISTEN_PUBLISH, (topic, message) ->
                    callback.onLEDValueUpdateModelReceive(new Gson().fromJson(message.toString(), LEDUpdateModel.class), true));

            return 0;
        } catch (MqttException e) {
            e.printStackTrace();

            return 1;
        }
    }

    public void disconnect() throws MqttException {
        mqttClient.disconnect();
    }

    public String getDeviceIdentifier() {
        return ProgramConfiguration.configuration.mqttDeviceName;
    }

    //Integrated Mqtt Callback Interface
    @Override
    public void connectionLost(Throwable cause) {
        System.err.println("Connection Lost");
        System.err.println(cause.getMessage());
        System.exit(1);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
