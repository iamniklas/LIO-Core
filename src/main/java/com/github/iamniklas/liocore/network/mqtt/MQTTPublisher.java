package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.ProgramConfiguration;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;
import java.util.concurrent.Callable;

public class MQTTPublisher {

    private final MemoryPersistence persistence = new MemoryPersistence();
    public IMqttClient client = new MqttClient(ProgramConfiguration.instance.mqttBrokerAddress, UUID.randomUUID().toString(), persistence);

    public MQTTPublisher() throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setUserName(ProgramConfiguration.instance.mqttUser);
        options.setPassword(ProgramConfiguration.instance.mqttPassword.toCharArray());
        client.connect(options);
    }
}
