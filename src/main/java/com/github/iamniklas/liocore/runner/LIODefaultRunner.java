package com.github.iamniklas.liocore.runner;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.network.javalin.JavalinHandler;

public class LIODefaultRunner extends Runner {

    private JavalinHandler javalinHandler;
    private LEDStripManager ledStripManager;

    public LIODefaultRunner(LEDRenderer _renderer) {
        super(_renderer);
    }

    @Override
    public void initialize() {
        ledStripManager = new LEDStripManager(renderer, ProgramConfiguration.configuration.ledCount);
        System.out.println("LED Strip Manager initialized");

        javalinHandler = new JavalinHandler(ledStripManager, ProgramConfiguration.configuration);
        System.out.println("Javalin Handler initialized");
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
