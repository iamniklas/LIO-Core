package com.github.iamniklas.liocore.led;

import java.awt.*;

public abstract class LEDRenderer {

    private final Color[] colorData;

    public LEDRenderer(int _stripSize) {
        colorData = new Color[_stripSize];
    }

    public void setColorData(int _index, Color _colorData) {
        colorData[_index] = _colorData;
    }

    abstract void render();
}
