package com.github.iamniklas.liocore;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.javalin.JavalinHandler;
import com.github.iamniklas.liocore.network.mqtt.MQTTPublisher;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.models.Direction;
import com.github.iamniklas.liocore.procedures.variants.RainbowMonoProcedure;
import com.github.iamniklas.liocore.procedures.variants.RainbowProcedure;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws MqttException {
        LEDStripManager ledStripManager = new LEDStripManager(new LEDRenderer() {
            @Override
            public void render(ArrayList<LIOColor> _colorData) {

            }
        }, 300);
        ProgramConfiguration.configuration = ProgramConfiguration.readConfigFromFile();

        LEDDataBundle ledDataBundle = new LEDDataBundle();
        ledDataBundle.ledStrip = ledStripManager;
        ledDataBundle.procedureCalls = ledStripManager;
        ledDataBundle.direction = Direction.Left;
        ledDataBundle.speed = 15f;
        ledDataBundle.repetitions = 1.6f;
        RainbowMonoProcedure rainbowProcedure = new RainbowMonoProcedure(ledDataBundle);

        ledStripManager.procContainer.queueProcedure(rainbowProcedure);

        System.out.println(new Gson().toJson(rainbowProcedure));

        /*MQTTListener listener = new MQTTListener(new IMqttCallback() {
            @Override
            public void onLEDUpdateModelReceive(LEDUpdateModel _updateModel, boolean _callForAllDevices) {
                System.out.println(new Gson().toJson(_updateModel));
            }

            @Override
            public void onLEDValueUpdateModelReceive(LEDUpdateModel _valueUpdateModel, boolean _callForAllDevices) {
                System.out.println("onLEDValueUpdateModelReceive");
            }
        });
        listener.connect();*/

        JavalinHandler h = new JavalinHandler(ledStripManager, ProgramConfiguration.configuration);

        MQTTPublisher mqttPublisher = new MQTTPublisher();
        try {
            mqttPublisher.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Ready");
    }
}
