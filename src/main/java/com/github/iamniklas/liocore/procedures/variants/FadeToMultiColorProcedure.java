package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.colorspaces.ColorRGBA;
import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class FadeToMultiColorProcedure extends Procedure {

    private LEDDataBundle bundle;

    ColorRGB[] baseColor = new ColorRGB[300];
    ColorRGB targetColor;
    float speed = 0.0f;
    int counter = 0;
    float[] alphaStep = new float[300];
    float[] alphaAddValue = new float[300];

    public FadeToMultiColorProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        targetColor = bundle.colorPrimary;
        speed = bundle.speed;
        steps = (int) Math.ceil((speed / (ProgramConfiguration.configuration.frametime / 1000.0f)));
        for (int i = 0; i < alphaAddValue.length; i++) {
            alphaAddValue[i] = 1 / (float)steps;
        }
        start();
    }

    @Override
    public void start() {
        for (int i = 0; i < baseColor.length; i++) {
            baseColor[i] = strip.ledStrip.getColorByPixel(i).toRGB();
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < alphaStep.length; i++) {
            alphaStep[i] += alphaAddValue[i];
            ColorRGBA outputColor = new ColorRGBA(baseColor[i].r, baseColor[i].g, baseColor[i].b, 255 - (int)(alphaStep[i] * 255));
            strip.setPixel(i, LIOColor.fromRGB(outputColor.toRGB(targetColor)));
        }
        step++;
        if(step > steps) {
            finishProcedure();
        }
    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }
}
