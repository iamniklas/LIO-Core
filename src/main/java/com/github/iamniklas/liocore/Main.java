package com.github.iamniklas.liocore;

import com.github.iamniklas.liocore.network.mqtt.IMqttCallback;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.LEDValueUpdateModel;
import com.github.iamniklas.liocore.network.mqtt.MQTTListener;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
    public static void main(String[] args) throws MqttException {
        new MQTTListener(new IMqttCallback() {
            @Override
            public void onLEDUpdateModelReceive(LEDUpdateModel _updateModel) {

            }

            @Override
            public void onLEDUpdateModelReceiveAll(LEDUpdateModel _updateModel) {
                
            }

            @Override
            public void onLEDValueUpdateModelReceive(LEDValueUpdateModel _valueUpdateModel) {

            }

            @Override
            public void onLEDValueUpdateModelReceiveAll(LEDValueUpdateModel _valueUpdateModel) {

            }
        }).connect();
    }
}
