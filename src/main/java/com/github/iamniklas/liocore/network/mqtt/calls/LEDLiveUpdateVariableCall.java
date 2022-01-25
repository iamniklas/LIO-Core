package com.github.iamniklas.liocore.network.mqtt.calls;

import com.github.iamniklas.liocore.network.mqtt.Topics;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import java.util.concurrent.Callable;

public class LEDLiveUpdateVariableCall implements Callable<Void> {

    private final IMqttClient client;

    private String TOPIC;
    private boolean sendToAll = false;

    public LEDLiveUpdateVariableCall(IMqttClient _client, String _deviceId, boolean _sendToAll) {
        client = _client;
        sendToAll = _sendToAll;
        TOPIC = Topics.VARIABLE_UPDATE_PUBLISH + _deviceId;
    }

    public LEDLiveUpdateVariableCall(IMqttClient _client, String _deviceId) {
        client = _client;
        TOPIC = Topics.VARIABLE_UPDATE_PUBLISH + _deviceId;
    }

    @Override
    public Void call() throws Exception {
        if(!client.isConnected()) {
            return null;
        }

        if(sendToAll) {
            TOPIC = Topics.VARIABLE_UPDATE_ALL_LISTEN_PUBLISH;
        }

        MqttMessage msg = new MqttMessage("".getBytes());
        msg.setQos(2);
        msg.setRetained(false);

        client.publish(TOPIC, msg);

        return null;
    }
}
