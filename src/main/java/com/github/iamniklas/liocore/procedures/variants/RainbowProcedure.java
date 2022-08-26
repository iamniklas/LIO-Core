package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorHSV;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.models.Direction;

public class RainbowProcedure extends Procedure {

    public ColorHSV colorHSV = new ColorHSV(0, 1.0f, 1.0f);
    public float hueCounter = 0.0f;
    public float[] hueArrayCounter = new float[LEDStripManager.ledCount];

    public RainbowProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);

        for (int i = 0; i < hueArrayCounter.length; i++) {
            hueArrayCounter[i] = Math.abs(i - 150);
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        switch (ledUpdateModel.bundle.direction) {
            case Center:
                int i = 0;
                while (i < hueArrayCounter.length) {
                    hueArrayCounter[i] = hueArrayCounter[i] < 0 ? 360.0f : hueArrayCounter[i] - ledUpdateModel.bundle.speed;
                    i++;
                }
                break;
            case CenterInvert:
                int j = 0;
                while (j < hueArrayCounter.length) {
                    hueArrayCounter[j] = hueArrayCounter[j] > 360 ? 0.0f : hueArrayCounter[j] + ledUpdateModel.bundle.speed;
                    j++;
                }
                break;

            case Left: hueCounter = hueCounter > 360 ? 0.0f : hueCounter + ledUpdateModel.bundle.speed; break;
            case Right: hueCounter = hueCounter < 0 ? 360.0f : hueCounter - ledUpdateModel.bundle.speed; break;
        }

        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            if(ledUpdateModel.bundle.direction == Direction.Center || ledUpdateModel.bundle.direction == Direction.CenterInvert) {
                colorHSV.h = (int) (((i * (ledUpdateModel.bundle.repetitions * (360.0f / LEDStripManager.ledCount))) + hueArrayCounter[i]) % 360);
                strip.setPixel(i, LIOColor.fromHSV(colorHSV));
                continue;
            }
            colorHSV.h = (int) (((i * (ledUpdateModel.bundle.repetitions * (360.0f / LEDStripManager.ledCount))) + hueCounter) % 360);
            strip.setPixel(i, LIOColor.fromHSV(colorHSV));
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {
        super.updateLEDDataBundle(ledDataBundle);
    }
}
