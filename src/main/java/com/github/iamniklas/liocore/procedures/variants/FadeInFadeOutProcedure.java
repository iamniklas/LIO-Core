package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

public class FadeInFadeOutProcedure extends Procedure {

    private LEDDataBundle bundle;

    private float[] colorPartModifier = {1.0f, 1.0f, 1.0f};

    public FadeInFadeOutProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        LIOColor targetColor = LIOColor.fromRGB(bundle.colorPrimary);
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

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }
}
