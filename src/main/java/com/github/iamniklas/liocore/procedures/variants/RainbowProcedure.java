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
            if (i < LEDStripManager.ledCount / 2) {
                hueArrayCounter[i] = (i * (360.0f / (LEDStripManager.ledCount / 2))) % 360;
            } else {
                hueArrayCounter[i] = ((LEDStripManager.ledCount - i - 1) * (360.0f / (LEDStripManager.ledCount / 2))) % 360;
            }
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        switch (ledUpdateModel.bundle.direction) {
            case Center:
                for (int i = 0; i < LEDStripManager.ledCount / 2; i++) {
                    hueArrayCounter[i] = hueArrayCounter[i] < 0 ? 360.0f : hueArrayCounter[i] - ledUpdateModel.bundle.speed;
                    hueArrayCounter[LEDStripManager.ledCount - 1 - i] = hueArrayCounter[LEDStripManager.ledCount - 1 - i] < 0 ? 360.0f : hueArrayCounter[LEDStripManager.ledCount - 1 - i] - ledUpdateModel.bundle.speed;
                }
            break;
            case CenterInvert:
                for (int i = 0; i < LEDStripManager.ledCount / 2; i++) {
                    hueArrayCounter[i] = hueArrayCounter[i] > 360 ? 0.0f : hueArrayCounter[i] + ledUpdateModel.bundle.speed;
                    hueArrayCounter[LEDStripManager.ledCount - 1 - i] = hueArrayCounter[LEDStripManager.ledCount - 1 - i] > 360 ? 0.0f : hueArrayCounter[LEDStripManager.ledCount - 1 - i] + ledUpdateModel.bundle.speed;
                }
            break;

            case Left: hueCounter = hueCounter > 360 ? 0.0f : hueCounter + ledUpdateModel.bundle.speed; break;
            case Right: hueCounter = hueCounter < 0 ? 360.0f : hueCounter - ledUpdateModel.bundle.speed; break;
        }

        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            if (ledUpdateModel.bundle.direction == Direction.Center || ledUpdateModel.bundle.direction == Direction.CenterInvert) {
                colorHSV.setH((int) (((i * (ledUpdateModel.bundle.repetitions * (360.0f / LEDStripManager.ledCount))) + hueArrayCounter[i]) % 360));
            } else {
                colorHSV.setH((int) (((i * (ledUpdateModel.bundle.repetitions * (360.0f / LEDStripManager.ledCount))) + hueCounter) % 360));
            }
            strip.setPixel(i, LIOColor.fromHSV(colorHSV));
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {
        super.updateLEDDataBundle(ledDataBundle);
    }

    @Override
    public boolean validateBundleData() {
        return ledUpdateModel.bundle.repetitions != null && ledUpdateModel.bundle.speed != null && ledUpdateModel.bundle.direction != null;
    }
}
