package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorHSV;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class RainbowMonoProcedure extends Procedure {

    private LEDDataBundle bundle;

    private ColorHSV colorHsv = new ColorHSV(0, 1.0f, 1.0f);
    private float speed = 1.0f;

    public RainbowMonoProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        speed = Math.max(1.0f, bundle.speed);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        colorHsv.h = colorHsv.h > 360.0f ? 0 : (int) (colorHsv.h + speed);
        strip.setAllPixels(LIOColor.fromHSV(colorHsv));
    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }
}
