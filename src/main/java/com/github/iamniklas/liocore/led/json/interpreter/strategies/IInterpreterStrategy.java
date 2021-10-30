package com.github.iamniklas.liocore.led.json.interpreter.strategies;

import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.led.json.LEDJsonProcedure;

public interface IInterpreterStrategy {
    ColorRGB[] interpretLine(String _line);
    LEDJsonProcedure interpretJson(String _json);
}
