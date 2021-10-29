package com.github.iamniklas.liocore.procedures;

import java.awt.*;
import java.util.ArrayList;

public class LEDStrip {
    private ArrayList<Color> strip;

    public LEDStrip(int _ledCount) {
        strip = new ArrayList<>();
        for (int i = 0; i < _ledCount; i++) {
            strip.add(Color.BLACK);
        }
    }

    public Color getColorByPixel(int _index) {
        return strip.get(_index);
    }
    public void setPixel(int _index, Color _color) { strip.set(_index, _color); }
}