package com.github.iamniklas.liocore.led;

import com.github.iamniklas.liocore.led.colorspace.LIOColor;

import java.awt.*;

public abstract class LEDRenderer {

    protected final LIOColor[] colorData;

    public LEDRenderer(int _stripSize) {
        colorData = new LIOColor[_stripSize];
    }

    public void setColorData(int _index, LIOColor _colorData) {
        colorData[_index] = _colorData;
    }

    public abstract void render();
}
