package com.github.iamniklas.liocore;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.javalin.JavalinHandler;
import com.github.iamniklas.liocore.procedures.ProcedureType;
import com.github.iamniklas.liocore.procedures.models.Direction;
import com.github.iamniklas.liocore.procedures.variants.RainbowMonoProcedure;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LEDStripManager ledStripManager = new LEDStripManager(new LEDRenderer() {
            @Override
            public void render(ArrayList<LIOColor> _colorData) {

            }
        }, 300);

        ProgramConfiguration.configuration = ProgramConfiguration.readConfigFromFile();

        LEDUpdateModel updateModel = new LEDUpdateModel();
        LEDDataBundle ledDataBundle = new LEDDataBundle();
        ledDataBundle.ledStrip = ledStripManager;
        ledDataBundle.procedureCalls = ledStripManager;
        ledDataBundle.direction = Direction.Left;
        ledDataBundle.speed = 15f;
        ledDataBundle.repetitions = 1.6f;
        //
        updateModel.procedure = ProcedureType.Rainbow;
        updateModel.bundle = ledDataBundle;
        //
        RainbowMonoProcedure rainbowProcedure = new RainbowMonoProcedure(updateModel);

        JavalinHandler h = new JavalinHandler(ledStripManager, ProgramConfiguration.configuration);

        System.out.println("Ready");

        ledStripManager.procContainer.queueProcedure(rainbowProcedure);
    }
}
