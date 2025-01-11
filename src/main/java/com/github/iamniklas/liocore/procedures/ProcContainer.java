package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.led.LEDStripManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ProcContainer {
    private static final Logger log = Logger.getLogger(ProcContainer.class);
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

    public void replaceActiveProcedure(Procedure _procedure) {
        if(!_procedure.validateBundleData()) {
            log.error("Procedure data validation failed. Some required fields for this procedure has not been set. Procedure will not be queued.");
            return;
        }

        removeAllCurrentProcedures();
        procedures.add(_procedure);
        _procedure.procCalls.onProcedureQueued();
        _procedure.start();
    }

    public void queueProcedure(Procedure _procedure) {
        procedures.add(_procedure);
        _procedure.procCalls.onProcedureQueued();
    }

    public void removeCurrentProcedure() {
        if(procedures.size() > 0) {
            procedures.remove(0);
        }
        if(procedures.size() > 0) {
            procedures.get(0).procCalls.onProcedureStart(procedures.get(0));
        }
    }

    public void removeAllCurrentProcedures() {
        procedures.clear();
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
