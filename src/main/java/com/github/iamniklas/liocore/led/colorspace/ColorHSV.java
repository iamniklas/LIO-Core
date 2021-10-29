package com.github.iamniklas.liocore.led.colorspace;

import com.github.iamniklas.liocore.led.typeinterfaces.IConvertibleColor;
import com.github.iamniklas.liocore.led.typeinterfaces.IHSVTypesModifier;
import org.checkerframework.checker.units.qual.C;

import java.awt.*;

public class ColorHSV implements IHSVTypesModifier<ColorHSV>, IConvertibleColor {
    //TODO Default colors (black, red, green, blue, ...)

    public int h;
    public float s;
    public float v;

    public ColorHSV(int _h, float _s, float _v) {
        h = _h;
        s = _s;
        v = _v;
    }

    @Override
    public ColorRGB toRGB() {
        int v255 = (int)(v * 255.0f);
        if(s == 0.0f) {
            return new ColorRGB(v255, v255, v255);
        }

        int hueInterval = (int)Math.floor(h / 60.0);
        float f = h / 60.0f - hueInterval;
        int p = (int)(v * (1 - s) * 255.0f);
        int q = (int)(v * (1 - s * f) * 255.0f);
        int t = (int)(v * (1 - s * (1 - f)) * 255.0f);

        switch (hueInterval) {
            case 0:
            case 6: return new ColorRGB(v255, t, p);
            case 1: return new ColorRGB(q, v255, p);
            case 2: return new ColorRGB(p, v255, t);
            case 3: return new ColorRGB(p, q, v255);
            case 4: return new ColorRGB(t, p, v255);
            case 5: return new ColorRGB(v255, p, q);
            default: return ColorRGB.black;
        }
    }
    @Override
    public ColorRGBA toRGBA() { return toRGB().toRGBA(); }
    @Override
    public ColorHSV toHSV() { return this; }
    @Override
    public Color toSystemColor() {
        ColorRGB rgb = toRGB();
        return new Color(rgb.r, rgb.g, rgb.b);
    }

    @Override
    public String toString() {
        return "ColorHSV{" + "h=" + h + ", s=" + s + ", v=" + v + '}';
    }
}