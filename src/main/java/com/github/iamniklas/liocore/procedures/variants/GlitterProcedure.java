package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.util.ArrayList;
import java.util.Random;

public class GlitterProcedure extends Procedure {

    private LEDDataBundle bundle;

    private ColorRGB baseColor;
    private ArrayList<Float> ledX = new ArrayList<>();
    private float speed = 0.1f;
    private float tcount = 0.0f;

    public GlitterProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        baseColor = bundle.colorPrimary;
        speed = bundle.speed;
        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            ledX.add(0.0f);
        }
        start();
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
        tcount += speed;

        if(tcount > Math.PI * 2)
            tcount = 0.0f;

        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            strip.setPixel(i, LIOColor.fromRGB(baseColor.dim((float) Math.abs(Math.sin(tcount + ledX.get(i))))));
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle bundle) {

    }
}
