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

public class JsonProcedure extends Procedure {

    private LEDDataBundle bundle;

    private LEDJsonProcedure ledJsonProcedure;

    public JsonProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        ledJsonProcedure = loadFromFile(bundle.path);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void update() {
        for (int i = 0; i < ledJsonProcedure.metaInfo.ledCount; i++) {
            strip.setPixel(i, LIOColor.fromRGB(ledJsonProcedure.ledStates[step].ledState[i]));
        }
        step += 5;
        if(step >= steps) {
            finishProcedure();
        }
    }

    LEDJsonProcedure loadFromFile(String _path) {
        byte[] encoded;
        try {
            encoded = Files.readAllBytes(Paths.get(_path));
            return LEDInterpreter.interpretJson(FileVersions.Latest, new String(encoded, StandardCharsets.UTF_8));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }
}
