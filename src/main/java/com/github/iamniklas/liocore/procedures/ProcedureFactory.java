package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.procedures.variants.*;

public class ProcedureFactory {
    public static Procedure getProcedure(ProcedureType _type, LEDDataBundle _bundle) {
        switch (_type) {
            case BootComplete: return new BootCompleteProcedure(_bundle);
            case ColorInstantSet: return new ColorInstantSetProcedure(_bundle);
            case FadeInFadeOut: return new FadeInFadeOutProcedure(_bundle);
            case FadeToMultiColor: return new FadeToMultiColorProcedure(_bundle);
            case FadeToUniformColor: return new FadeToUniformColorProcedure(_bundle);
            case Blink: return new BlinkProcedure(_bundle);
            case Glitter: return new GlitterProcedure(_bundle);
            case Fill: return new FillStripProcedure(_bundle);
            case FillInterpolated: return new FillStripInterpolatedProcedure(_bundle);
            case Rainbow: return new RainbowProcedure(_bundle);
            case RainbowMono: return new RainbowMonoProcedure(_bundle);
            case JsonProcedure: return new JsonProcedure(_bundle);
            case Progress: return new ProgressProcedure(_bundle);
            case Lightning: return new LightningProcedure(_bundle);
            case Javascript: return new JavascriptProcedure(_bundle);
            case NoLongerReady: return new NoLongerReadyProcedure(_bundle);
            default: return null;
        }
    }
}
