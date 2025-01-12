package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class ErrorProcedure extends Procedure {

    private boolean redLightActive = false;

    public ErrorProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);

        steps = 60;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        if(step % 5 == 0) {
            if(redLightActive) {
                strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
            }
            else {
                strip.setAllPixels(LIOColor.fromRGB(ColorRGB.RED));
            }
            redLightActive = !redLightActive;
        }
        step++;
        if(step > steps) {
            strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }

    @Override
    public boolean validateBundleData() {
        return true;
    }
}
