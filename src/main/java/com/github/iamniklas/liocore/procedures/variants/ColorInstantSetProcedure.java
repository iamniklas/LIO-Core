package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

public class ColorInstantSetProcedure extends Procedure {

    private LEDDataBundle bundle;

    private LIOColor targetColor;

    public ColorInstantSetProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        targetColor = LIOColor.fromRGB(_bundle.colorPrimary);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        strip.setAllPixels(targetColor);
        postUpdate();
        finishProcedure();
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle bundle) {

    }
}
