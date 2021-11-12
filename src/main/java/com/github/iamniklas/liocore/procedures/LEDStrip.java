package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.led.colorspace.LIOColor;

import java.awt.*;
import java.util.ArrayList;

public class LEDStrip {
    private ArrayList<LIOColor> strip;

    public LEDStrip(int _ledCount) {
        strip = new ArrayList<>();
        for (int i = 0; i < _ledCount; i++) {
            strip.add(new LIOColor(0, 0, 0));
        }
    }

    public LIOColor getColorByPixel(int _index) {
        return strip.get(_index);
    }
    public void setPixel(int _index, LIOColor _color) { strip.set(_index, _color); }
}