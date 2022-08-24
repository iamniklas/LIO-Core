package com.github.iamniklas.liocore.network;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.ProcedureType;

public class LEDUpdateModel {
    public ProcedureType procedure;

    public LEDDataBundle bundle;

    public LEDUpdateModel() { }

    public LEDUpdateModel(ProcedureType _procedureType, LEDDataBundle _ledDataBundle) {
        procedure = _procedureType;
        bundle = _ledDataBundle;
    }
}
