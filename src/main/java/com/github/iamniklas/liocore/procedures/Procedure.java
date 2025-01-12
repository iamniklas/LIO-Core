package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import org.apache.log4j.Logger;

public abstract class Procedure {
    private static final Logger log = Logger.getLogger(Procedure.class);
    public LEDUpdateModel ledUpdateModel;
    protected LEDStripManager strip;
    public ProcedureCalls procCalls;
    protected int step = 0;
    protected int steps = 0;
    protected int modulo = 1;
    protected boolean moduloInvert = false;
    protected boolean isSubProcedure = false;
    protected boolean bundleDataValid = false;

    public Procedure(LEDUpdateModel _updateModel) {
        ledUpdateModel = _updateModel;

        strip = ledUpdateModel.bundle.ledStrip;
        procCalls = ledUpdateModel.bundle.procedureCalls;
        if(ledUpdateModel.bundle.modulo != null) {
            modulo = ledUpdateModel.bundle.modulo;
        }
        if(ledUpdateModel.bundle.moduloInvert != null) {
            moduloInvert = ledUpdateModel.bundle.moduloInvert;
        }
    }

    public void start() { }
    public abstract void update();
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {
        ledUpdateModel.bundle = ledDataBundle;

        if(ledUpdateModel.bundle.modulo != null) {
            modulo = ledUpdateModel.bundle.modulo;
        }
        if(ledUpdateModel.bundle.moduloInvert != null) {
            moduloInvert = ledUpdateModel.bundle.moduloInvert;
        }
    }

    public boolean bundleIsValid() {
        return bundleDataValid;
    }

    public abstract boolean validateBundleData();
    protected boolean checkSingleField(Object _field, String _fieldName) {
        if(_field == null) {
            log.error("Field " + _fieldName + " is not set but required. Procedure will not be queued.");
            return false;
        }
        return true;
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
    }

    protected void finishProcedure(boolean _clearStrip) {
        strip.procContainer.removeCurrentProcedure();
        if (_clearStrip) {
            strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
        }
        procCalls.onProcedureFinish();
    }
}
