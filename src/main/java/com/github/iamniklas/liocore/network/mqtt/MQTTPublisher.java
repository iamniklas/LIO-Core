package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

public class MQTTPublisher {

    private final MemoryPersistence persistence = new MemoryPersistence();
    public IMqttClient client = new MqttClient(ProgramConfiguration.configuration.mqttBrokerAddress, UUID.randomUUID().toString(), persistence);

    public MQTTPublisher() throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setUserName(ProgramConfiguration.configuration.mqttUser);
        options.setPassword(ProgramConfiguration.configuration.mqttPassword.toCharArray());
        client.connect(options);
    }
}
