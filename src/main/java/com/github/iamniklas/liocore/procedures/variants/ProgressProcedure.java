package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
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

    public ProgressProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

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
        //super.start();
        strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
    }

    @Override
    public void update() {
        strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
        if(isPulsating) {
            sinX += speed;
            if(sinX > Math.PI * 2)
                sinX = 0f;

            strip.setArea(
                    (int)(LEDStripManager.ledCount * start),
                    (int)(LEDStripManager.ledCount * progress),
                    LIOColor.fromRGB(colorRGB.dim((float) Math.abs(Math.sin(sinX))))
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
                    Math.min(Math.max((int)(LEDStripManager.ledCount * start), 0), 300),
                    Math.min(Math.max((int)(LEDStripManager.ledCount * progress), 0), 300),
                    LIOColor.fromRGB(colorRGB)
            );
        } else {
          strip.setArea(
                  (int)(LEDStripManager.ledCount * start),
                  (int)(LEDStripManager.ledCount * progress),
                  LIOColor.fromRGB(colorRGB)
          );
        }
    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }
}
