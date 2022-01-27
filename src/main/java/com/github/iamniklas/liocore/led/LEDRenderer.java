package com.github.iamniklas.liocore.led;

import com.github.iamniklas.liocore.led.colorspace.LIOColor;

import java.awt.*;
import java.util.ArrayList;

public abstract class LEDRenderer {
    public abstract void render(ArrayList<LIOColor> _colorData);
}
