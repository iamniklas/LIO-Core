package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;

public abstract class Procedure {
    public LEDUpdateModel ledUpdateModel;
    protected LEDStripManager strip;
    public ProcedureCalls procCalls;
    protected int step = 0;
    protected int steps = 0;
    protected int modulo = 1;
    protected boolean moduloInvert = false;
    protected boolean isSubProcedure = false;

    public Procedure(LEDUpdateModel _updateModel) {
        ledUpdateModel = _updateModel;

        strip = ledUpdateModel.bundle.ledStrip;
        procCalls = ledUpdateModel.bundle.procedureCalls;
        if(ledUpdateModel.bundle.puModulo != null) {
            modulo = ledUpdateModel.bundle.puModulo;
        }
        if(ledUpdateModel.bundle.puModuloInvert != null) {
            moduloInvert = ledUpdateModel.bundle.puModuloInvert;
        }
    }

    public void start() { procCalls.onProcedureStart(this); }
    public abstract void update();
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {
        ledUpdateModel.bundle = ledDataBundle;

        if(ledUpdateModel.bundle.puModulo != null) {
            modulo = ledUpdateModel.bundle.puModulo;
        }
        if(ledUpdateModel.bundle.puModuloInvert != null) {
            moduloInvert = ledUpdateModel.bundle.puModuloInvert;
        }
    }

    public void onActionReceived(ProcedureAction procedureAction) {

    }

    public void postUpdate() {
        if (modulo < 2) return;
        //TODO Get LED Strip size from LEDManager
        for (int i = 0; i < 300; i++) {
            if (moduloInvert) {
                if (i % modulo != 0) {
                    strip.setPixel(i, LIOColor.fromRGB(ColorRGB.BLACK));
                }
            } else {
                if (i % modulo == 0) {
                    strip.setPixel(i, LIOColor.fromRGB(ColorRGB.BLACK));
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
            strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
        }
        procCalls.onProcedureFinish();
    }

    protected void finishProcedure() {
        strip.procContainer.removeCurrentProcedure();
        procCalls.onProcedureFinish();
    }
}
