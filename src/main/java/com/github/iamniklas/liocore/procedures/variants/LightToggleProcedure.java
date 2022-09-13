package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.ProcedureAction;

public class LightToggleProcedure extends Procedure {

    private boolean lightIsOn = true;

    public LightToggleProcedure(LEDUpdateModel _updateModel) {
        super(_updateModel);
    }

    @Override
    public void update() {
        if(lightIsOn) {
            strip.setAllPixels(LIOColor.fromRGB(ledUpdateModel.bundle.colorPrimary));
        } else {
            strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {
        super.updateLEDDataBundle(ledDataBundle);
    }

    @Override
    public void onActionReceived(ProcedureAction procedureAction) {
        super.onActionReceived(procedureAction);

        switch (procedureAction) {
            case Toggle:
                lightIsOn = !lightIsOn;
                break;
        }
    }
}
