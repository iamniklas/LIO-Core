package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.variants.*;

public class ProcedureFactory {
    public static Procedure getProcedure(LEDUpdateModel _updateModel) {
        switch (_updateModel.procedure) {
            case Ready: return new ReadyProcedure(_updateModel);
            case MonoColor: return new MonoColorProcedure(_updateModel);
            case Glitter: return new GlitterProcedure(_updateModel);
            case Rainbow: return new RainbowProcedure(_updateModel);
            case RainbowMono: return new RainbowMonoProcedure(_updateModel);
            case MultiColor: return new MultiColorProcedure(_updateModel);
            case Lightning: return new LightningProcedure(_updateModel);
            case Javascript: return new JavascriptProcedure(_updateModel);
            case LightToggle: return new LightToggleProcedure(_updateModel);
            case LightToggleCineAdjusted: return new LightToggleCineAdjustedProcedure(_updateModel);
            case Error: return new ErrorProcedure(_updateModel);
            default: return null;
        }
    }
}
