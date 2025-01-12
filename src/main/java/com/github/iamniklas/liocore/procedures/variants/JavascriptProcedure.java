package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

//TODO Complete/Optimize
public class JavascriptProcedure extends Procedure {

    private LEDDataBundle bundle;

    private String jsStart = "";
    private String jsUpdate = "";
    private ScriptEngine runtime = null;
    private Invocable invocable = null;
    private Object eval = null;

    public JavascriptProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        bundle = _ledUpdateModel.bundle;

        runtime = null;
        eval = null;
        runtime = new ScriptEngineManager().getEngineByName("javascript");
        invocable = (Invocable) runtime;
    }

    @Override
    public void start() {
        System.out.println("Starting Javascript Procedure");
        try {
            eval = runtime.eval(bundle.data);
        }
        catch (ScriptException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            invocable.invokeFunction("update", strip, this, step, 720);
            step++;
        }
        catch (NoSuchMethodException | ScriptException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle ledDataBundle) {

    }

    @Override
    public boolean validateBundleData() {
        return bundle.data != null;
    }

    public void finish() {
        strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));
    }
}
