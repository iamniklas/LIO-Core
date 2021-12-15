package com.github.iamniklas.liocore.network.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;
import java.util.concurrent.Callable;

public class MQTTPublisher {

    private final MemoryPersistence persistence = new MemoryPersistence();
    public IMqttClient client = new MqttClient("tcp://000raspberry.ddns.net:1883", UUID.randomUUID().toString(), persistence);

    public MQTTPublisher() throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setUserName("lio");
        options.setPassword("8kNhtmUG6kmUm3djdEE7MXmvAg4662".toCharArray());
        client.connect(options);
    }
}
