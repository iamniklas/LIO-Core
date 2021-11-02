package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.led.colorspace.ColorRGBA;
import com.github.iamniklas.liocore.procedures.Procedure;

public class FadeToMultiColorProcedure extends Procedure {

    private LEDDataBundle bundle;

    ColorRGB[] baseColor = new ColorRGB[300];
    ColorRGB targetColor;
    float speed = 0.0f;
    int counter = 0;
    float[] alphaStep = new float[300];
    float[] alphaAddValue = new float[300];

    public FadeToMultiColorProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        targetColor = bundle.colorPrimary;
        speed = bundle.speed;
        steps = (int) Math.ceil((speed / (strip.frametime / 1000.0f)));
        for (int i = 0; i < alphaAddValue.length; i++) {
            alphaAddValue[i] = 1 / (float)steps;
        }
        start();
    }

    @Override
    public void start() {
        super.start();
        for (int i = 0; i < baseColor.length; i++) {
            baseColor[i] = ColorRGB.fromSystemColor(strip.ledStrip.getColorByPixel(i));
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < alphaStep.length; i++) {
            alphaStep[i] += alphaAddValue[i];
            ColorRGBA outputColor = new ColorRGBA(baseColor[i].r, baseColor[i].g, baseColor[i].b, 255 - (int)(alphaStep[i] * 255));
            strip.setPixel(i, outputColor.toRGB(targetColor).toSystemColor());
        }
        step++;
        if(step > steps) {
            finishProcedure();
        }
    }
}
