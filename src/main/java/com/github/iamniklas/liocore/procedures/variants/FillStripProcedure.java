package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.models.Direction;

import java.awt.*;

public class FillStripProcedure extends Procedure {

    private LEDDataBundle bundle;

    private Color fillColor = Color.black;
    private int speed = 1;
    private Direction direction = Direction.Left;

    public FillStripProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        fillColor = bundle.colorPrimary.toSystemColor();
        speed = Math.round(bundle.speed);
        direction = bundle.direction;
        steps = LEDStripManager.LED_COUNT;

        if(direction == Direction.Center || direction == Direction.CenterInvert) {
            steps /= 2;
        }
    }

    @Override
    public void start() { }

    @Override
    public void update() {
        int i = 0;
        switch (direction) {
            case Left:
                while (i < speed) {
                    strip.setPixel(step + i, fillColor);
                    i++;
                }
                break;
            case Right:
                while (i < speed) {
                    strip.setPixel(LEDStripManager.LED_COUNT - 1 - step - i, fillColor);
                    i++;
                }
                break;
            case Center:
                while (i < speed) {
                    strip.setPixel(LEDStripManager.LED_COUNT / 2 - 1 + step + i, fillColor);
                    strip.setPixel(LEDStripManager.LED_COUNT / 2 - 1 - step - i, fillColor);
                    i++;
                }
                break;
            case CenterInvert:
                while (i < speed) {
                    strip.setPixel(step + i, fillColor);
                    strip.setPixel(LEDStripManager.LED_COUNT - 1 - step - i, fillColor);
                    i++;
                }
                break;
        }

        step += speed;
        if(step >= steps) {
            finishProcedure();
        }
    }
}
