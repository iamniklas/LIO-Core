package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

public class ReadyProcedure extends Procedure {

    public ReadyProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);

        steps = 720;
    }

    @Override
    public void start() {
        strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
    }

    @Override
    public void update() {
        if(step == 0)
            start();

        double d = Math.abs(Math.sin(Math.toRadians(step)));
        LIOColor c = new LIOColor(0, (int)(d * 255), 0);
        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            if(i % 10 == 0) {
                strip.setPixel(i, c);
            }
        }
        step += 5;
        if(step >= steps) {
            strip.setAllPixels(new LIOColor(0, 0, 0));
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }

    @Override
    public boolean validateBundleData() {
        return true;
    }
}
