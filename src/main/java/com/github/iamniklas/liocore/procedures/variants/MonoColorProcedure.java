package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class MonoColorProcedure extends Procedure implements Runnable {

    public MonoColorProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        strip.setAllPixels(LIOColor.fromRGB(ledUpdateModel.bundle.color));
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }

    @Override
    public boolean validateBundleData() {
        return ledUpdateModel.bundle.color != null;
    }

    @Override
    public void run() {
        strip.setAllPixels(LIOColor.fromRGB(ledUpdateModel.bundle.color));
    }
}
