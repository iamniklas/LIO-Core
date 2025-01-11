package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class LightningProcedure extends Procedure {

    public LightningProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
    }

    @Override
    public void start() {
        super.start();

        steps = ledUpdateModel.bundle.duration * 50;
    }

    @Override
    public void update() {
        strip.setAllPixels(LIOColor.fromRGB(ColorRGB.WHITE50.dim((float) Math.abs(Math.sin((step / ledUpdateModel.bundle.speed))))));

        ledUpdateModel.bundle.speed += ledUpdateModel.bundle.attenuation;

        if(step > steps) {
            finishProcedure(true);
        }
        step++;
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }

    @Override
    public boolean validateBundleData() {
        return ledUpdateModel.bundle.speed != null && ledUpdateModel.bundle.attenuation != null && ledUpdateModel.bundle.duration != null;
    }
}
