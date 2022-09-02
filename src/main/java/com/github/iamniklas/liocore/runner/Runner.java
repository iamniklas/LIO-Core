package com.github.iamniklas.liocore.runner;

import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;

abstract class Runner {
    protected LEDRenderer renderer;

    Runner(LEDRenderer _renderer) {
        renderer = _renderer;
    }

    public abstract void initialize();
    public abstract LEDStripManager getLEDStripManager();
    public abstract void update();
}
