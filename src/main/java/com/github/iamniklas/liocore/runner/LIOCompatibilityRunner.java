package com.github.iamniklas.liocore.runner;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.javalin.JavalinHandler;
import com.github.iamniklas.liocore.network.mqtt.IMqttCallback;
import com.github.iamniklas.liocore.network.mqtt.MQTTListener;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.ProcedureFactory;
import com.google.gson.Gson;

@Deprecated
public class LIOCompatibilityRunner extends Runner {

    private MQTTListener mqttListener;
    private JavalinHandler javalinHandler;
    private LEDStripManager ledStripManager;

    public LIOCompatibilityRunner(LEDRenderer _renderer) {
        super(_renderer);
    }

    @Override
    public void initialize() {
        ledStripManager = new LEDStripManager(renderer, ProgramConfiguration.configuration.ledCount);
        System.out.println("LED Strip Manager initialized");

        mqttListener = new MQTTListener(new IMqttCallback() {
            @Override
            public void onLEDUpdateModelReceive(LEDUpdateModel _updateModel, boolean _callForAllDevices) {
                _updateModel.bundle.ledStrip = ledStripManager;
                _updateModel.bundle.procedureCalls = ledStripManager;
                Procedure p = ProcedureFactory.getProcedure(_updateModel);
                ledStripManager.procContainer.removeCurrentProcedure();
                ledStripManager.procContainer.queueProcedure(p);
            }

            @Override
            public void onLEDValueUpdateModelReceive(LEDUpdateModel _valueUpdateModel, boolean _callForAllDevices) {
                System.out.println("Call for Live Update");
                System.out.println(new Gson().toJson(_valueUpdateModel));
                ledStripManager.procContainer.getActiveProcedure().updateLEDDataBundle(_valueUpdateModel.bundle);
            }
        });
        int mqttConnectionResult = mqttListener.connect();
        if(mqttConnectionResult == 0) {
            System.out.println("MQTT Connected");
        }
        else {
            System.out.println("MQTT Connecting Failed");
        }

        javalinHandler = new JavalinHandler(ledStripManager, ProgramConfiguration.configuration);
        System.out.println("Javalin Handler initialized");

        if(ProgramConfiguration.configuration.startingProcedure != null) {
            ProgramConfiguration.configuration.startingProcedure.bundle.ledStrip = ledStripManager;
            ProgramConfiguration.configuration.startingProcedure.bundle.procedureCalls = ledStripManager;
            ledStripManager.procContainer.replaceActiveProcedure(
                    ProcedureFactory.getProcedure(ProgramConfiguration.configuration.startingProcedure)
            );
        }
    }

    @Override
    public LEDStripManager getLEDStripManager() {
        return ledStripManager;
    }

    @Override
    public void update() {
        ledStripManager.update();
        ledStripManager.render();
        ledStripManager.waitFrametime();
    }
}
