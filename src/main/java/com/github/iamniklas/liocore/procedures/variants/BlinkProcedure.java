package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class BlinkProcedure extends Procedure {

    private LEDDataBundle bundle;

    private LIOColor blinkColor;
    private int frames = 10;
    private int modulo = 2;

    public BlinkProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        blinkColor = LIOColor.fromRGB(bundle.colorPrimary);
        frames = bundle.duration;
        modulo = bundle.modulo;
        steps = frames;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        step++;
        if(step % modulo == 0) {
            strip.setAllPixels(blinkColor);
        }
        else {
            strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
        }

        if(step == steps) {
            strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
            finishProcedure();
        }
    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }
}