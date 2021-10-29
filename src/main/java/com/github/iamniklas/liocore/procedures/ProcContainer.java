package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.led.LEDStripManager;

import java.util.ArrayList;

public class ProcContainer {
    private LEDStripManager ledStripManager;
    private ArrayList<Procedure> procedures = new ArrayList<>();

    public ProcContainer(LEDStripManager _ledStripManager) {
        ledStripManager = _ledStripManager;
    }

    public Procedure getActiveProcedure() {
        if(procedures.size() > 0)
            return procedures.get(0);
        else
            return null;
    }

    public void queueProcedure(Procedure _procedure) {
        procedures.add(_procedure);
        _procedure.onProcedureQueued();
    }

    public void removeCurrentProcedure() {
        if(procedures.size() > 0) {
            procedures.remove(0);
        }
        if(procedures.size() > 0) {
            procedures.get(0).procCalls.onProcedureStart(procedures.get(0));
        }
    }

    public void update() {
        if(procedures.size() < 1)
            return;

        procedures.get(0).update();
    }

    public void postUpdate() {
        if(procedures.size() < 1)
            return;

        procedures.get(0).postUpdate();
    }
}
