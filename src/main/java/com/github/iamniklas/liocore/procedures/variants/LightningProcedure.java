package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.procedures.Procedure;

public class LightningProcedure extends Procedure {

    private LEDDataBundle bundle;

    private float speed = 0.0f;
    private float attenuation = 0.0f;

    public LightningProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        speed = bundle.speed;
        attenuation = bundle.value1;
        steps = bundle.duration * 50;
    }

    @Override
    public void start() { }

    @Override
    public void update() {
        strip.setAllPixels(ColorRGB.white50.dim((float) Math.abs(Math.sin((step / speed)))).toSystemColor());

        speed += attenuation;

        if(step > steps) {
            finishProcedure(true);
        }
        step++;
    }
}
