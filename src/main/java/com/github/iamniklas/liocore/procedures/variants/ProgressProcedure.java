package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.procedures.Procedure;

public class ProgressProcedure extends Procedure {

    private LEDDataBundle bundle;

    public ProgressProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        bundle = _bundle;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }
}
