package com.github.iamniklas.liocore.network.mqtt.calls;

import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.mqtt.Topics;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.Callable;

public class LEDLiveUpdateVariableCall implements Callable<Void> {

    private final IMqttClient client;

    private String TOPIC;
    private final boolean sendToAll;
    private final LEDUpdateModel updateModel;

    public LEDLiveUpdateVariableCall(IMqttClient _client, String _deviceId, LEDUpdateModel _updateModel, boolean _sendToAll) {
        client = _client;
        sendToAll = _sendToAll;
        updateModel = _updateModel;
        TOPIC = Topics.VARIABLE_UPDATE_PUBLISH + _deviceId;
    }

    public LEDLiveUpdateVariableCall(IMqttClient _client, LEDUpdateModel _updateModel, String _deviceId) {
        client = _client;
        sendToAll = false;
        updateModel = _updateModel;
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

        MqttMessage msg = new MqttMessage(new Gson().toJson(updateModel).getBytes());
        msg.setQos(2);
        msg.setRetained(false);

        client.publish(TOPIC, msg);

        return null;
    }
}
