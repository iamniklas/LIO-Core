package com.github.iamniklas.liocore.procedures.variants;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.musicsync.AnimationData;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MusicSyncProcedure extends Procedure {

    private ScheduledExecutorService scheduler;

    public MusicSyncProcedure(LEDUpdateModel _ledUpdateModel) {
        super(_ledUpdateModel);
        scheduler = Executors.newScheduledThreadPool(1);
        for (AnimationData.Action action : _ledUpdateModel.bundle.animationData.actions) {
            if(action.recurring != null && action.recurring.recurring) {
                scheduler.scheduleAtFixedRate(new ColorInstantSetProcedure(_ledUpdateModel), action.timestamp, action.recurring.offset, TimeUnit.MILLISECONDS);
            }
            else {
                scheduler.schedule(new ColorInstantSetProcedure(_ledUpdateModel), action.timestamp, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void updateLEDUpdateModel(LEDUpdateModel _ledUpdateModel) {

    }

    @Override
    protected void finishProcedure(boolean _clearStrip) {
        super.finishProcedure(_clearStrip);
        scheduler.shutdown();
        scheduler = null;
    }
}
