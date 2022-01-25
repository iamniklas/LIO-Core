package com.github.iamniklas.liocore.network.mqtt.calls;

import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.mqtt.Topics;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.Callable;

public class LEDUpdateProcedureCall implements Callable<Void> {

    private final IMqttClient client;
    private final LEDUpdateModel updateModel;

    private String TOPIC;
    private final boolean sendToAll;

    public LEDUpdateProcedureCall(IMqttClient _client, String _deviceId, LEDUpdateModel _updateModel, boolean _sendToAll) {
        client = _client;
        updateModel = _updateModel;
        sendToAll = _sendToAll;
        TOPIC = Topics.UPDATE_PUBLISH + _deviceId;
    }

    public LEDUpdateProcedureCall(IMqttClient _client, String _deviceId, LEDUpdateModel _updateModel) {
        client = _client;
        updateModel = _updateModel;
        sendToAll = false;
        TOPIC = Topics.UPDATE_PUBLISH + _deviceId;
    }

    @Override
    public Void call() throws Exception {
        if(!client.isConnected()) {
            return null;
        }

        if(sendToAll) {
            TOPIC = Topics.UPDATE_ALL_LISTEN_PUBLISH;
        }

        MqttMessage msg = new MqttMessage(new Gson().toJson(updateModel).getBytes());
        msg.setQos(2);
        msg.setRetained(false);

        client.publish(TOPIC, msg);

        return null;
    }
}
