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

    public static ColorRGBA fromSystemColor(LIOColor _systemColor) {
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
    public ColorRGB toRGB(ColorRGB _back) {
        ColorRGB result = new ColorRGB(
                (int)((1 - a / 255.0f) * _back.r + (a/255.0f) * r),
                (int)((1 - a / 255.0f) * _back.g + (a/255.0f) * g),
                (int)((1 - a / 255.0f) * _back.b + (a/255.0f) * b)
        );

        result.r = Math.max(0, Math.min(result.r, 255));
        result.g = Math.max(0, Math.min(result.g, 255));
        result.b = Math.max(0, Math.min(result.b, 255));

        return result;
    }
    @Override
    public ColorRGBA toRGBA() { return this; }
    @Override
    public ColorHSV toHSV() {
        //TODO
        return null;
    }

    @Override
    public LIOColor toSystemColor() {
        ColorRGB rgb = toRGB();
        return new LIOColor(rgb.r, rgb.g, rgb.b);
    }

    @Override
    public String toString() {
        return "ColorRGBA{" + "r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + '}';
    }
}
