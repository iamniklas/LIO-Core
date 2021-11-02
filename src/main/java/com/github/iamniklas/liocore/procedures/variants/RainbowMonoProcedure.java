package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.ColorHSV;
import com.github.iamniklas.liocore.procedures.Procedure;

public class RainbowMonoProcedure extends Procedure {

    private LEDDataBundle bundle;

    private ColorHSV colorHsv = new ColorHSV(0, 1.0f, 1.0f);
    private float speed = 1.0f;

    public RainbowMonoProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        speed = Math.max(1.0f, bundle.speed);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        colorHsv.h = colorHsv.h > 360.0f ? 0 : (int) (colorHsv.h + speed);
        strip.setAllPixels(colorHsv.toRGB().toSystemColor());
    }
}
