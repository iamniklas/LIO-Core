package com.github.iamniklas.liocore;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.network.*;
import com.github.iamniklas.liocore.network.mqtt.IMqttCallback;
import com.github.iamniklas.liocore.network.mqtt.MQTTListener;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

public class Main {
    public static void main(String[] args) throws MqttException {
        ProgramConfiguration.configuration = ProgramConfiguration.readConfigFromFile();

        MQTTListener listener = new MQTTListener(new IMqttCallback() {
            @Override
            public void onLEDUpdateModelReceive(LEDUpdateModel _updateModel, boolean _callForAllDevices) {
                System.out.println(new Gson().toJson(_updateModel));
            }

            @Override
            public void onLEDValueUpdateModelReceive(LEDUpdateModel _valueUpdateModel, boolean _callForAllDevices) {
                System.out.println("onLEDValueUpdateModelReceive");
            }
        });
        listener.connect();

        System.out.println("Ready");
    }
}
