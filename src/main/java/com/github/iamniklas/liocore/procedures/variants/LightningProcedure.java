package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class LightningProcedure extends Procedure {

    private LEDDataBundle bundle;

    private float speed = 0.0f;
    private float attenuation = 0.0f;

    public LightningProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        speed = bundle.speed;
        attenuation = bundle.value1;
        steps = bundle.duration * 50;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        strip.setAllPixels(LIOColor.fromRGB(ColorRGB.WHITE50.dim((float) Math.abs(Math.sin((step / speed))))));

        speed += attenuation;

        if(step > steps) {
            finishProcedure(true);
        }
        step++;
    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }
}
