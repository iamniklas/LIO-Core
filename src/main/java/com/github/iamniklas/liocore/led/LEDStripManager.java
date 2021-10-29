package com.github.iamniklas.liocore.led;

import com.github.iamniklas.liocore.procedures.LEDStrip;
import com.github.iamniklas.liocore.procedures.ProcContainer;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.ProcedureCalls;

import java.awt.*;

public class LEDStripManager implements ProcedureCalls {
    public static final int LED_COUNT = 300;
    public LEDStrip ledStrip = new LEDStrip(LED_COUNT);
    //TODO protected LEDStatus ledStatus = new LEDStatus();
    public ProcContainer procContainer = new ProcContainer(this);
    public int frametime = 16;

    private LEDRenderer renderer;
    private boolean clearOnExit;

    public LEDStripManager(LEDRenderer _renderer, boolean _clearOnExit) {
        renderer = _renderer;
        clearOnExit = _clearOnExit;
    }

    public void update() {
        procContainer.update();
        procContainer.postUpdate();

        for (int i = 0; i < LED_COUNT; i++) {
            renderer.setColorData(i, ledStrip.getColorByPixel(i));
        }
        renderer.render();

        try {
            Thread.sleep(frametime);
        }
        catch (InterruptedException ie) {

        }
    }

    public void setPixel(int _index, Color _color) {
        ledStrip.setPixel(_index, _color);
    }

    public void setArea(int _start, int _end, Color _color) {
        for (int i = _start; i < _end; i++) {
            ledStrip.setPixel(i, _color);
        }
    }

    public void setAllPixels(Color _color) {
        for (int i = 0; i < LED_COUNT; i++) {
            ledStrip.setPixel(i, _color);
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
