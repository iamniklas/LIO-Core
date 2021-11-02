package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.interpolation.*;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

/**
 *
 * @deprecated This procedure will be removed in a future core update and will be replaced with the existing FillStripProcedure
 */
@Deprecated
public class FillStripInterpolatedProcedure extends Procedure {

    private LEDDataBundle bundle;

    private int litLeds = 0;
    private float pPercentage = 0.0f;
    private Color fillColor = Color.black;
    private InterpolationType interpolationType = InterpolationType.EaseInExpo;

    public FillStripInterpolatedProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        fillColor = bundle.colorPrimary.toSystemColor();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        pPercentage = step / (float) LEDStripManager.LED_COUNT;

        step++;
        litLeds = (int) Math.min(300, Interpolation.getInterpolationValue(pPercentage, interpolationType) * 300.0f);

        strip.setAllPixels(Color.black);
        strip.setArea(0, litLeds, fillColor);
        if(step > LEDStripManager.LED_COUNT) {
            finishProcedure();
        }
    }
}
