package com.github.iamniklas.liocore.led.json.interpreter.strategies;

import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.led.json.LEDJsonProcedure;

public class LatestInterpreterStrategy implements IInterpreterStrategy {
    @Override
    public ColorRGB[] interpretLine(String _line) {
        return new ColorRGB[0];
    }

    @Override
    public LEDJsonProcedure interpretJson(String _json) {
        return null;
    }
}
