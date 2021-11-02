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
    private float[] hueArrayCounter = new float[LEDStripManager.LED_COUNT];

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
    public void start() { }

    @Override
    public void update() {

    }
}
