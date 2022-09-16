package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.variants.*;

public class ProcedureFactory {
    public static Procedure getProcedure(LEDUpdateModel _updateModel) {
        switch (_updateModel.procedure) {
            case BootComplete: return new BootCompleteProcedure(_updateModel);
            case ColorInstantSet: return new ColorInstantSetProcedure(_updateModel);
            case FadeInFadeOut: return new FadeInFadeOutProcedure(_updateModel);
            case FadeToMultiColor: return new FadeToMultiColorProcedure(_updateModel);
            case FadeToUniformColor: return new FadeToUniformColorProcedure(_updateModel);
            case Blink: return new BlinkProcedure(_updateModel);
            case Glitter: return new GlitterProcedure(_updateModel);
            case Fill: return new FillStripProcedure(_updateModel);
            case FillInterpolated: return new FillStripInterpolatedProcedure(_updateModel);
            case Rainbow: return new RainbowProcedure(_updateModel);
            case RainbowMono: return new RainbowMonoProcedure(_updateModel);
            case JsonProcedure: return new JsonProcedure(_updateModel);
            case Progress: return new ProgressProcedure(_updateModel);
            case Lightning: return new LightningProcedure(_updateModel);
            case Javascript: return new JavascriptProcedure(_updateModel);
            case LightToggle: return new LightToggleProcedure(_updateModel);
            case LightToggleCineAdjusted: return new LightToggleCineAdjustedProcedure(_updateModel);
            case NoLongerReady: return new NoLongerReadyProcedure(_updateModel);
            default: return null;
        }
    }
}
