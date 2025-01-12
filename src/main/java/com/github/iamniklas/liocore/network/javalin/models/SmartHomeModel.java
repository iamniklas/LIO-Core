package com.github.iamniklas.liocore.network.javalin.models;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;

public class SmartHomeModel {
    private ColorRGB color;
    private float brightness;
    private boolean powerSwitch;

    public SmartHomeModel(ColorRGB _color, float _brightness, boolean _powerSwitch) {
        color = _color;
        brightness = _brightness;
        powerSwitch = _powerSwitch;
    }

    public ColorRGB getColor() { return color; }
    public void setColor(ColorRGB _color) { color = _color; }

    public float getBrightness() { return brightness; }
    public void setBrightness(float _brightness) { brightness = _brightness; }

    public boolean getPowerSwitch() { return powerSwitch; }
    public void setPowerSwitch(boolean _powerSwitch) { powerSwitch = _powerSwitch; }

    public String toString() {
        return "SmartHomeModel{color=" + color + ", brightness=" + brightness + ", powerSwitch=" + powerSwitch + "}";
    }
}
