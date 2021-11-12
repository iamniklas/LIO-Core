package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

public class BootCompleteProcedure extends Procedure {

    private LEDDataBundle bundle;

    public BootCompleteProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        steps = 720;
    }

    @Override
    public void start() {
        strip.setAllPixels(ColorRGB.black.toSystemColor());
    }

    @Override
    public void update() {
        if(step == 0)
            start();

        double d = Math.abs(Math.sin(Math.toRadians(step)));
        LIOColor c = new LIOColor(0, (int)(d * 255), 0);
        for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
            if(i % 10 == 0) {
                strip.setPixel(i, c);
            }
        }
        step += 5;
        if(step == steps) {
            strip.setAllPixels(new LIOColor(0, 0, 0));
            finishProcedure();
        }
    }
}
