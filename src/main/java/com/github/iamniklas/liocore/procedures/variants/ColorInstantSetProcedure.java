package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

public class ColorInstantSetProcedure extends Procedure implements Runnable {

    private LEDDataBundle bundle;

    private LIOColor targetColor;

    public ColorInstantSetProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        targetColor = LIOColor.fromRGB(bundle.colorPrimary);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        strip.setAllPixels(targetColor);
        postUpdate();
        finishProcedure();
    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }

    @Override
    public void run() {
        strip.setAllPixels(targetColor);
    }
}
