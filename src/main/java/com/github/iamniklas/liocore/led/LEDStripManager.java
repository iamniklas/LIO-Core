package com.github.iamniklas.liocore.led;

import com.github.iamniklas.liocore.procedures.LEDStrip;
import com.github.iamniklas.liocore.procedures.ProcContainer;
import com.github.iamniklas.liocore.procedures.ProcedureCalls;

import java.awt.*;

public abstract class LEDStripManager implements ProcedureCalls {
    public static final int LED_COUNT = 300;
    public LEDStrip ledStrip = new LEDStrip(LED_COUNT);
    //TODO protected LEDStatus ledStatus = new LEDStatus();
    public ProcContainer procContainer;
    private int frameTime = 16;

    private LEDRenderer renderer;

    public LEDStripManager() {

    }

    public void update() {
        procContainer.update();
        procContainer.postUpdate();

        for (int i = 0; i < LED_COUNT; i++) {
            renderer.setColorData(i, ledStrip.getColorByPixel(i));
        }

        try {
            Thread.sleep(frameTime);
        }
        catch (InterruptedException ie) {

        }
    }

    public abstract void setPixel(int _index, Color _color);
    public abstract void setArea(int _start, int _end, Color _color);
    public abstract void setAllPixels(Color _color);
}
