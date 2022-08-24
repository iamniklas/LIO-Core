package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.models.Direction;
import org.checkerframework.checker.units.qual.C;

public class FillStripProcedure extends Procedure {

    private LEDDataBundle bundle;

    private LIOColor fillColor = new LIOColor(0, 0, 0);
    private int speed = 1;
    private Direction direction = Direction.Left;

    public FillStripProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        fillColor = LIOColor.fromRGB(bundle.colorPrimary);
        speed = Math.round(bundle.speed);
        direction = bundle.direction;
        steps = LEDStripManager.ledCount;

        if(direction == Direction.Center || direction == Direction.CenterInvert) {
            steps /= 2;
        }
    }

    @Override
    public void start() {
        super.start();
    }

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
                    strip.setPixel(LEDStripManager.ledCount - 1 - step - i, fillColor);
                    i++;
                }
                break;
            case Center:
                while (i < speed) {
                    strip.setPixel(LEDStripManager.ledCount / 2 - 1 + step + i, fillColor);
                    strip.setPixel(LEDStripManager.ledCount / 2 - 1 - step - i, fillColor);
                    i++;
                }
                break;
            case CenterInvert:
                while (i < speed) {
                    strip.setPixel(step + i, fillColor);
                    strip.setPixel(LEDStripManager.ledCount - 1 - step - i, fillColor);
                    i++;
                }
                break;
        }

        step += speed;
        if(step >= steps) {
            finishProcedure();
        }
    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }
}
