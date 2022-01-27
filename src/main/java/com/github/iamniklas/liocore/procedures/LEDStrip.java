package com.github.iamniklas.liocore.procedures;

import com.github.iamniklas.liocore.led.colorspace.LIOColor;

import java.util.ArrayList;

public class LEDStrip {
    private ArrayList<LIOColor> stripData;

    public LEDStrip(int _ledCount) {
        stripData = new ArrayList<>();
        for (int i = 0; i < _ledCount; i++) {
            stripData.add(new LIOColor(0, 0, 0));
        }
    }

    public ArrayList<LIOColor> getStripData() { return stripData; }
    public LIOColor getColorByPixel(int _index) {
        return stripData.get(_index);
    }
    public void setPixel(int _index, LIOColor _color) { stripData.set(_index, _color); }
}