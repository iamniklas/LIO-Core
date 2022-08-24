package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.interpolation.*;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

/**
 *
 * @deprecated This procedure will be removed in a future core update and will be replaced with the existing FillStripProcedure
 */
@Deprecated
public class FillStripInterpolatedProcedure extends Procedure {

    private LEDDataBundle bundle;

    private int litLeds = 0;
    private float pPercentage = 0.0f;
    private LIOColor fillColor = new LIOColor(0, 0, 0);
    private InterpolationType interpolationType = InterpolationType.EaseInExpo;

    public FillStripInterpolatedProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        fillColor = LIOColor.fromRGB(bundle.colorPrimary);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        pPercentage = step / (float) LEDStripManager.ledCount;

        step++;
        litLeds = (int) Math.min(300, Interpolation.getInterpolationValue(pPercentage, interpolationType) * 300.0f);

        strip.setAllPixels(new LIOColor(0, 0, 0));
        strip.setArea(0, litLeds, fillColor);
        if(step > LEDStripManager.ledCount) {
            finishProcedure();
        }
    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }
}
