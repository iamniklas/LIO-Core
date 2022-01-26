package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.ColorHSV;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.models.Direction;

public class RainbowProcedure extends Procedure {

    private LEDDataBundle bundle;

    private ColorHSV colorHSV = new ColorHSV(0, 1.0f, 1.0f);
    private float hueCounter = 0.0f;
    private float[] hueArrayCounter = new float[LEDStripManager.ledCount];

    private float repetitions = 0.75f;
    private float speed = 3.0f;
    private Direction direction = Direction.Left;

    public RainbowProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        repetitions = bundle.repetitions;
        speed = bundle.speed;
        direction = bundle.direction;

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
        switch (direction) {
            case Center:
                int i = 0;
                while (i < hueArrayCounter.length) {
                    hueArrayCounter[i] = hueArrayCounter[i] < 0 ? 360.0f : hueArrayCounter[i] - speed;
                    i++;
                }
                break;
            case CenterInvert:
                int j = 0;
                while (j < hueArrayCounter.length) {
                    hueArrayCounter[j] = hueArrayCounter[j] > 360 ? 0.0f : hueArrayCounter[j] + speed;
                    j++;
                }
                break;

            case Left: hueCounter = hueCounter > 360 ? 0.0f : hueCounter + speed; break;
            case Right: hueCounter = hueCounter < 0 ? 360.0f : hueCounter - speed; break;
        }

        for (int i = 0; i < LEDStripManager.ledCount; i++) {
            if(direction == Direction.Center || direction == Direction.CenterInvert) {
                colorHSV.h = (int) (((i * (repetitions * (360.0f / LEDStripManager.ledCount))) + hueArrayCounter[i]) % 360);
                strip.setPixel(i, colorHSV.toRGB().toSystemColor());
                continue;
            }
            colorHSV.h = (int) (((i * (repetitions * (360.0f / LEDStripManager.ledCount))) + hueCounter) % 360);
            strip.setPixel(i, colorHSV.toRGB().toSystemColor());
        }
    }
}
