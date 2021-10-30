package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

public class FadeInFadeOutProcedure extends Procedure {

    private LEDDataBundle bundle;

    private float[] colorPartModifier = {1.0f, 1.0f, 1.0f};

    public FadeInFadeOutProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        Color targetColor = _bundle.colorPrimary.toSystemColor();
        colorPartModifier[0] = targetColor.getRed() / 255.0f;
        colorPartModifier[1] = targetColor.getGreen() / 255.0f;
        colorPartModifier[2] = targetColor.getBlue() / 255.0f;
    }

    @Override
    public void start() { }

    @Override
    public void update() {
        double d = Math.abs(Math.sin(Math.toRadians(step)));

        strip.setAllPixels(
            new Color(
                Math.round(colorPartModifier[0] * d * 255.0f),
                Math.round(colorPartModifier[0] * d * 255.0f),
                Math.round((colorPartModifier[0] * d * 255.0f))
            )
        );

        step += 5;

        if(step >= steps) {
            strip.setAllPixels(Color.black);
            finishProcedure();
        }
    }
}
