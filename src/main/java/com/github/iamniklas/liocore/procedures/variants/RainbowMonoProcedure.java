package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorHSV;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class RainbowMonoProcedure extends Procedure {

    private ColorHSV colorHsv = new ColorHSV(0, 1.0f, 1.0f);

    public RainbowMonoProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
    }

    @Override
    public void start() {
        ledUpdateModel.bundle.speed = Math.max(1.0f, ledUpdateModel.bundle.speed);
    }

    @Override
    public void update() {
        colorHsv.h = colorHsv.h > 360.0f ? 0 : (int) (colorHsv.h + ledUpdateModel.bundle.speed);
        strip.setAllPixels(LIOColor.fromHSV(colorHsv));
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }

    @Override
    public boolean validateBundleData() {
        return ledUpdateModel.bundle.speed != null;
    }
}
