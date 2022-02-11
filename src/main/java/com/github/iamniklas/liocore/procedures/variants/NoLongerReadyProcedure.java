package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.procedures.Procedure;

public class NoLongerReadyProcedure extends Procedure {

    private LEDDataBundle bundle;

    private boolean redLightActive = false;

    public NoLongerReadyProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        steps = 60;
    }

    @Override
    public void start() {
        super.start();
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
            finishProcedure();
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle bundle) {

    }
}
