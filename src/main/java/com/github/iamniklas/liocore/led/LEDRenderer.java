package com.github.iamniklas.liocore.led;

import java.awt.*;

public abstract class LEDRenderer {

    protected final Color[] colorData;

    public LEDRenderer(int _stripSize) {
        colorData = new Color[_stripSize];
    }

    public void setColorData(int _index, Color _colorData) {
        colorData[_index] = _colorData;
    }

    public abstract void render();
}
