package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.led.json.LEDJsonProcedure;
import com.github.iamniklas.liocore.led.json.interpreter.FileVersions;
import com.github.iamniklas.liocore.led.json.interpreter.LEDInterpreter;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultiColorProcedure extends Procedure {

    public MultiColorProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        for (int i = 0; i < ledUpdateModel.bundle.colors.size(); i++) {
            strip.setPixel(i, LIOColor.fromRGB(ledUpdateModel.bundle.colors.get(step)));
        }
        postUpdate();
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }

    @Override
    public boolean validateBundleData() {
        return true;
    }
}
