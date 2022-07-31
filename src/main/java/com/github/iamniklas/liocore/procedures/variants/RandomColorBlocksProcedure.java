package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.util.Random;

public class RandomColorBlocksProcedure extends Procedure {

    private int sections;
    private int modulo;
    private LEDDataBundle bundle;

    private long startTime = 0;
    private int duration = 50;

    private LIOColor[] colors = new LIOColor[] {
            LIOColor.fromRGB(ColorRGB.RED),
            LIOColor.fromRGB(ColorRGB.BLUE),
            LIOColor.fromRGB(ColorRGB.GREEN),
            LIOColor.fromRGB(ColorRGB.YELLOW),
            LIOColor.fromRGB(ColorRGB.PURPLE),
            LIOColor.fromRGB(ColorRGB.WHITE50)
    };

    public RandomColorBlocksProcedure(LEDDataBundle ledDataBundle) {
        super(ledDataBundle);

        //TODO New Parameter instead of Duration for Section Count
        sections = ledDataBundle.duration;
        modulo = ledDataBundle.modulo;
        duration = ledDataBundle.bpm;

        bundle = ledDataBundle;
    }

    @Override
    public void update() {
        if(startTime == 0) {
            startTime = System.currentTimeMillis();

            int ledsPerSection = LEDStripManager.ledCount / sections;

            int offset = Math.abs(new Random().nextInt());

            for (int i = 0; i < sections; i++) {
                strip.setArea(ledsPerSection * i, ledsPerSection * (i + 1), colors[(i + offset) % 6]);

                //Last Row
                if(i == sections - 1) {
                    strip.setArea(LEDStripManager.ledCount - ledsPerSection, LEDStripManager.ledCount, colors[(i + offset) % 6]);
                }
            }
        }

        if(System.currentTimeMillis() - startTime > duration) {
            finishProcedure();
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle bundle) {

    }

    @Override
    protected void finishProcedure() {
        super.finishProcedure();
        bundle.ledStrip.procContainer.queueProcedure(new RandomColorBlocksProcedure(bundle));
    }
}
