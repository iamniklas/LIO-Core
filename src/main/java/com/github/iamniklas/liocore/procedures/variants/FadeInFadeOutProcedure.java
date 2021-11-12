package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

public class FadeInFadeOutProcedure extends Procedure {

    private LEDDataBundle bundle;

    private float[] colorPartModifier = {1.0f, 1.0f, 1.0f};

    public FadeInFadeOutProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        LIOColor targetColor = _bundle.colorPrimary.toSystemColor();
        colorPartModifier[0] = targetColor.r / 255.0f;
        colorPartModifier[1] = targetColor.g / 255.0f;
        colorPartModifier[2] = targetColor.b / 255.0f;

        steps = 180;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        double d = Math.abs(Math.sin(Math.toRadians(step)));

        strip.setAllPixels(
            new LIOColor(
                    (int)Math.round(colorPartModifier[0] * d * 255.0f),
                    (int)Math.round(colorPartModifier[0] * d * 255.0f),
                    (int)Math.round((colorPartModifier[0] * d * 255.0f))
            )
        );

        step += 5;

        if(step >= steps) {
            strip.setAllPixels(new LIOColor(0, 0, 0));
            finishProcedure();
        }
    }
}
