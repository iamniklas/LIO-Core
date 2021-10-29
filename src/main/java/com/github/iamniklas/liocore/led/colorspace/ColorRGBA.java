package com.github.iamniklas.liocore.led.colorspace;

import com.github.iamniklas.liocore.led.typeinterfaces.*;

import java.awt.*;

public class ColorRGBA implements IRGBTypesModifier<ColorRGBA>, IConvertibleColor {
    public static final ColorRGBA black =     new ColorRGBA(0,     0,  0,   255);
    public static final ColorRGBA white =     new ColorRGBA(255,   255,255, 255);
    public static final ColorRGBA white50 =   new ColorRGBA(128,   128,128, 255);
    public static final ColorRGBA red =       new ColorRGBA(255,   0,  0,   255);
    public static final ColorRGBA green =     new ColorRGBA(0,     255,0,   255);
    public static final ColorRGBA blue =      new ColorRGBA(0,     0,  255, 255);
    public static final ColorRGBA orange =    new ColorRGBA(255,   255,0,   255);
    public static final ColorRGBA magenta =   new ColorRGBA(255,   0,  255, 255);
    public static final ColorRGBA turquoise = new ColorRGBA(0,     255,255, 255);

    public static ColorRGBA fromSystemColor(Color _systemColor) {
        return ColorRGB.fromSystemColor(_systemColor).toRGBA();
    }

    public int r;
    public int g;
    public int b;
    public int a;

    public ColorRGBA(int _r, int _g, int _b, int _a) {
        r = _r;
        g = _g;
        b = _b;
        a = _a;
    }

    public ColorRGBA dim(float _percentage) {
        return new ColorRGBA(
                (int)(r * _percentage),
                (int)(g * _percentage),
                (int)(b * _percentage),
                (int)(a * _percentage)
        );
    }
    public ColorRGBA dim(ColorRGBA _c) {
        return new ColorRGBA(
                (r * _c.r) / 255,
                (g * _c.g) / 255,
                (b * _c.b) / 255,
                (a * _c.a) / 255
        );
    }

    @Override
    public ColorRGB toRGB() {
        return new ColorRGB(
                (255-a) * ColorRGB.black.r + (a/255) * r,
                (255-a) * ColorRGB.black.g + (a/255) * g,
                (255-a) * ColorRGB.black.b + (a/255) * b
        );
    }
    @Override
    public ColorRGBA toRGBA() { return this; }
    @Override
    public ColorHSV toHSV() {
        //TODO
        return null;
    }

    @Override
    public Color toSystemColor() {
        ColorRGB rgb = toRGB();
        return new Color(rgb.r, rgb.g, rgb.b);
    }

    @Override
    public String toString() {
        return "ColorRGBA{" + "r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + '}';
    }
}
