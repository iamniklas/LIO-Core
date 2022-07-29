package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.musicsync.AnimationData;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MusicSyncProcedure extends Procedure {

    private ScheduledExecutorService scheduler;

    public MusicSyncProcedure(LEDDataBundle _bundle) {
        super(_bundle);
        scheduler = Executors.newScheduledThreadPool(1);
        for (AnimationData.Action action : _bundle.animationData.actions) {
            if(action.recurring != null && action.recurring.recurring) {
                scheduler.scheduleAtFixedRate(new ColorInstantSetProcedure(_bundle), action.timestamp, action.recurring.offset, TimeUnit.MILLISECONDS);
            }
            else {
                scheduler.schedule(new ColorInstantSetProcedure(_bundle), action.timestamp, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle bundle) {

    }

    @Override
    protected void finishProcedure(boolean _clearStrip) {
        super.finishProcedure(_clearStrip);
        scheduler.shutdown();
        scheduler = null;
    }
}
