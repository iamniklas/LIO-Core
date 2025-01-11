package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.util.ArrayList;
import java.util.Random;

public class GlitterProcedure extends Procedure {

    private LEDDataBundle bundle;

    private ArrayList<Float> ledX = new ArrayList<>();
    private float tcount = 0.0f;

    public GlitterProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            ledX.add(0.0f);
        }
    }

    @Override
    public void start() {
        Random r = new Random();
        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            ledX.set(i, (float) (r.nextFloat() * (Math.PI * 2 - 0.0f) + 0.0f));
        }
    }

    @Override
    public void update() {
        tcount += bundle.speed;

        if(tcount > Math.PI * 2)
            tcount = 0.0f;

        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            strip.setPixel(i, LIOColor.fromRGB(bundle.color.dim((float) Math.abs(Math.sin(tcount + ledX.get(i))))));
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }

    @Override
    public boolean validateBundleData() {
        return bundle.color != null && bundle.speed != null;
    }
}
