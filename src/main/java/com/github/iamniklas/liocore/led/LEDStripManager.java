package com.github.iamniklas.liocore.led;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.procedures.*;

public class LEDStripManager implements ProcedureCalls {
    public static int ledCount;
    public final LEDStrip ledStrip;

    public ProcContainer procContainer = new ProcContainer(this);

    private final LEDRenderer renderer;

    public LEDStripManager(LEDRenderer _renderer, int _ledCount) {
        renderer = _renderer;
        ledCount = _ledCount;

        ledStrip = new LEDStrip(ledCount);
    }

    public void update() {
        procContainer.update();
        procContainer.postUpdate();
    }

    public void render() {
        renderer.render(ledStrip.getStripData());
    }

    public void waitFrametime() {
        try {
            Thread.sleep(ProgramConfiguration.configuration.frametime);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void setPixel(int _index, LIOColor _color) {
        ledStrip.setPixel(_index, _color);
    }
    public void setPixel(int _index, int _r, int _g, int _b) {
        ledStrip.setPixel(_index, new LIOColor(_r, _g, _b));
    }

    public void setArea(int _start, int _end, LIOColor _color) {
        for (int i = _start; i < _end; i++) {
            ledStrip.setPixel(i, _color);
        }
    }

    public void setAllPixels(LIOColor _color) {
        for (int i = 0; i < ledCount; i++) {
            ledStrip.setPixel(i, _color);
        }
    }
    public void setAllPixels(int _r, int _g, int _b) {
        LIOColor color = new LIOColor(_r, _g, _b);
        for (int i = 0; i < ledCount; i++) {
            ledStrip.setPixel(i, color);
        }
    }

    @Override
    public void onProcedureStart(Procedure _procedure) {
        _procedure.start();
    }

    @Override
    public void onProcedureQueued() {}

    @Override
    public void onProcedureFinish() {}
}
