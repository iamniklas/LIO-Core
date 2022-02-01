package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;

public abstract class Procedure {
    protected LEDStripManager strip;
    protected LEDDataBundle dataBundle;
    public ProcedureCalls procCalls;
    protected int step = 0;
    protected int steps = 0;
    protected int modulo = 1;
    protected boolean moduloInvert = false;
    protected boolean isSubProcedure = false;

    public Procedure(LEDDataBundle _bundle) {
        dataBundle = _bundle;

        strip = _bundle.ledStrip;
        procCalls = _bundle.procedureCalls;
        if(_bundle.puModulo != null) {
            modulo = _bundle.puModulo;
        }
        if(_bundle.puModuloInvert != null) {
            moduloInvert = _bundle.puModuloInvert;
        }
    }

    public void start() { procCalls.onProcedureStart(this); }
    public abstract void update();
    public abstract void updateLEDDataBundle(LEDDataBundle bundle);

    public void postUpdate() {
        if (modulo < 2) return;
        //TODO Get LED Strip size from LEDManager
        for (int i = 0; i < 300; i++) {
            if (moduloInvert) {
                if (i % modulo != 0) {
                    strip.setPixel(i, ColorRGB.black.toSystemColor());
                }
            } else {
                if (i % modulo == 0) {
                    strip.setPixel(i, ColorRGB.black.toSystemColor());
                }
            }
        }
        if (step > steps) {
            finishProcedure();
        }
    }

    protected void finishProcedure(boolean _clearStrip) {
        strip.procContainer.removeCurrentProcedure();
        if (_clearStrip) {
            strip.setAllPixels(ColorRGB.black.toSystemColor());
        }
        procCalls.onProcedureFinish();
    }

    protected void finishProcedure() {
        strip.procContainer.removeCurrentProcedure();
        procCalls.onProcedureFinish();
    }
}
