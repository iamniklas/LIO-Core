package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
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

    public JavascriptProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;

        runtime = null;
        invocable = null;
        eval = null;
        runtime = new ScriptEngineManager().getEngineByName("javascript");
        jsUpdate = bundle.data;
        try {
            eval = runtime.eval(jsUpdate);
            invocable = (Invocable) runtime;
        }
        catch (ScriptException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void start() {
        super.start();
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
    public void updateLEDDataBundle(LEDDataBundle bundle) {

    }

    public void finish() {
        finishProcedure();
    }
}
