package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
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
    public void start() { }

    @Override
    public void update() {
        if(step % 5 == 0) {
            if(redLightActive) {
                strip.setAllPixels(Color.black);
            }
            else {
                strip.setAllPixels(Color.red);
            }
            redLightActive = !redLightActive;
        }
        step++;
        if(step > steps) {
            strip.setAllPixels(Color.black);
            finishProcedure();
        }
    }
}