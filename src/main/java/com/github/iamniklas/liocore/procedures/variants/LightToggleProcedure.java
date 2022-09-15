package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.interpolation.Interpolation;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.ProcedureAction;

public class LightToggleProcedure extends Procedure {

    private boolean lightIsOn = true;

    private int lowerBound = 0;
    private int upperBound = LEDStripManager.ledCount - 1;
    private int counter = 0;

    public LightToggleProcedure(LEDUpdateModel _updateModel) {
        super(_updateModel);
    }

    @Override
    public void update() {
        if(lightIsOn) {
            strip.setArea(
                    lowerBound,
                    Math.min(
                            Math.round(Interpolation.getInterpolationValue(counter/300f, ledUpdateModel.bundle.interpolation) * LEDStripManager.ledCount),
                            upperBound + 1),
                    LIOColor.fromRGB(ledUpdateModel.bundle.colorPrimary)
            );
        } else {
            strip.setArea(
                    lowerBound,
                    Math.min(
                            Math.round(Interpolation.getInterpolationValue(counter/300f, ledUpdateModel.bundle.interpolation) * LEDStripManager.ledCount),
                            upperBound + 1),
                    LIOColor.fromRGB(ColorRGB.BLACK)
            );
        }

        if(counter < upperBound) {
            float duration = (ledUpdateModel.bundle.duration == null) ? 1.0f : (float)ledUpdateModel.bundle.duration;
            counter += 5 * ( 1 / duration);
        }
        if(counter > upperBound) {
            counter = LEDStripManager.ledCount;
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
                counter = 0;
                lightIsOn = !lightIsOn;
                break;
            case Enable:
                lightIsOn = true;
                break;
            case Disable:
                lightIsOn = false;
                break;
        }
    }
}
