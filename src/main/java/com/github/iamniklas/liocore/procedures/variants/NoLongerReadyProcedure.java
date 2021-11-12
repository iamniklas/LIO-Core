package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

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
                strip.setAllPixels(ColorRGB.black.toSystemColor());
            }
            else {
                strip.setAllPixels(ColorRGB.red.toSystemColor());
            }
            redLightActive = !redLightActive;
        }
        step++;
        if(step > steps) {
            strip.setAllPixels(ColorRGB.black.toSystemColor());
            finishProcedure();
        }
    }
}
