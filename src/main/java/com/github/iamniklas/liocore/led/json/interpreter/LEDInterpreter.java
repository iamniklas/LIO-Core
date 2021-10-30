package com.github.iamniklas.liocore.led.json.interpreter;

import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.led.json.LEDJsonProcedure;
import com.github.iamniklas.liocore.led.json.interpreter.strategies.LatestInterpreterStrategy;

public class LEDInterpreter {
    public static ColorRGB[] interpretLine(FileVersions _fileVersions, String _line) {
        switch (_fileVersions) {
            default: return new LatestInterpreterStrategy().interpretLine(_line);
        }
    }

    public static LEDJsonProcedure interpretJson(FileVersions _fileVersion, String _json) {
        switch (_fileVersion) {
            default: return new LatestInterpreterStrategy().interpretJson(_json);
        }
    }
}
