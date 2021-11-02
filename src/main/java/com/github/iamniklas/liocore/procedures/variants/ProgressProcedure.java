package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.models.IndeterminateState;

public class ProgressProcedure extends Procedure {

    private LEDDataBundle bundle;

    private float start = 0.0f;
    private float progress = 0.0f;
    private ColorRGB colorRGB;
    private boolean isPulsating = false;
    private boolean isIndeterminate = false;
    private IndeterminateState indeterminateState = IndeterminateState.Fill;
    private float sinX = 0.0f;
    private float speed = 0.2f;

    public ProgressProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        colorRGB = bundle.colorPrimary;
        start = bundle.value1;
        progress = bundle.value2;
        isIndeterminate = bundle.indeterminate;
        isPulsating = bundle.pulsating;
        speed = bundle.speed;
        steps = 1;
        start();
    }

    @Override
    public void start() {
        super.start();
        strip.setAllPixels(ColorRGB.black.toSystemColor());
    }

    @Override
    public void update() {
        strip.setAllPixels(ColorRGB.black.toSystemColor());
        if(isPulsating) {
            sinX += speed;
            if(sinX > Math.PI * 2)
                sinX = 0f;

            strip.setArea(
                    (int)(LEDStripManager.LED_COUNT * start),
                    (int)(LEDStripManager.LED_COUNT * progress),
                    colorRGB.dim((float) Math.abs(Math.sin(sinX))).toSystemColor()
            );
        }
        else if(isIndeterminate) {
            switch (indeterminateState) {
                case Fill:
                    progress += speed;
                    if(progress >= 1.0f) {
                        indeterminateState = IndeterminateState.FillDone;
                    }
                    break;
                case FillDone:
                    start = 0.0f;
                    progress = 1.0f;
                    indeterminateState = IndeterminateState.Clear;
                    break;
                case Clear:
                    start += speed;
                    if(start >= 1.0f) {
                        indeterminateState = IndeterminateState.ClearDone;
                    }
                    break;
                case ClearDone:
                    start = 0.0f;
                    progress = 0.0f;
                    indeterminateState = IndeterminateState.Fill;
                    break;
            }

            strip.setArea(
                    Math.min(Math.max((int)(LEDStripManager.LED_COUNT * start), 0), 300),
                    Math.min(Math.max((int)(LEDStripManager.LED_COUNT * progress), 0), 300),
                    colorRGB.toSystemColor()
            );
        } else {
          strip.setArea(
                  (int)(LEDStripManager.LED_COUNT * start),
                  (int)(LEDStripManager.LED_COUNT * progress),
                  colorRGB.toSystemColor()
          );
        }
    }
}
