package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

public class ColorInstantSetProcedure extends Procedure {

    private LEDDataBundle bundle;

    private Color targetColor;

    public ColorInstantSetProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        targetColor = bundle.colorPrimary.toSystemColor();
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
}
