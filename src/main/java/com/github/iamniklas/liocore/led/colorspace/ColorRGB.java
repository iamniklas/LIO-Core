package com.github.iamniklas.liocore.led.colorspace;

import com.github.iamniklas.liocore.led.ColorChannel;
import com.github.iamniklas.liocore.led.typeinterfaces.IConvertibleColor;
import com.github.iamniklas.liocore.led.typeinterfaces.IRGBTypesModifier;

import java.awt.*;

public class ColorRGB implements IRGBTypesModifier<ColorRGB>, IConvertibleColor {
    public static final ColorRGB black =     new ColorRGB(0,     0,  0);
    public static final ColorRGB white =     new ColorRGB(255,   255,255);
    public static final ColorRGB white50 =   new ColorRGB(128,   128,128);
    public static final ColorRGB red =       new ColorRGB(255,   0,  0);
    public static final ColorRGB green =     new ColorRGB(0,     255,0);
    public static final ColorRGB blue =      new ColorRGB(0,     0,  255);
    public static final ColorRGB orange =    new ColorRGB(255,   255,0);
    public static final ColorRGB magenta =   new ColorRGB(255,   0,  255);
    public static final ColorRGB turquoise = new ColorRGB(0,     255,255);

    public static ColorRGB fromSystemColor(Color _systemColor) {
        return new ColorRGB(_systemColor.getRed(), _systemColor.getGreen(), _systemColor.getBlue());
    }

    int r = 255;
    int g = 255;
    int b = 255;

    public ColorRGB(int _r, int _g, int _b) {
        r = _r;
        g = _g;
        b = _b;
    }

    public ColorRGB(ColorRGB _color) {
        r = _color.r;
        g = _color.g;
        b = _color.b;
    }

    public ColorRGB filter(ColorChannel _channel) {
        switch (_channel) {
            case Red: return new ColorRGB(0, g, b);
            case Green: return new ColorRGB(r, 0, b);
            case Blue: return new ColorRGB(r, g, 0);
            case Alpha: return new ColorRGB(0, 0, 0);
            default: return this;
        }
    }

    public ColorRGB overrideChannel(ColorChannel _channel, int _value) {
        switch (_channel) {
            case Red: return new ColorRGB(_value, g, b);
            case Green: return new ColorRGB(r, _value, b);
            case Blue: return new ColorRGB(r, g, _value);
            default: return this;
        }
    }

    public ColorRGB cutHigh(ColorChannel _channel, int _value) {
        switch (_channel) {
            case Red: return new ColorRGB(cutHigh(r, _value), g, b);
            case Green: return new ColorRGB(r, cutHigh(g, _value), b);
            case Blue: return new ColorRGB(r, g, cutHigh(b, _value));
            default: return this;
        }
    }
    public ColorRGB cutLow(ColorChannel _channel, int _value) {
        switch (_channel) {
            case Red: return new ColorRGB(cutLow(r, _value), g, b);
            case Green: return new ColorRGB(r, cutLow(g, _value), b);
            case Blue: return new ColorRGB(r, g, cutLow(b, _value));
            default: return this;
        }
    }

    int cutHigh(int _value, int _cutter) {
        return Math.min(_value, _cutter);
    }
    int cutLow(int _value, int _cutter) {
        return Math.max(_value, _cutter);
    }

    public ColorRGB dim(float _percentage) {
        return new ColorRGB((int)(r * _percentage), (int)(g * _percentage), (int)(b * _percentage));
    }
    public ColorRGB dim(float _r, float _g, float _b) {
        return new ColorRGB((int)(r * _r), (int)(g * _g), (int)(b * _b));
    }

    @Override
    public ColorRGB toRGB() { return this; }
    @Override
    public ColorRGBA toRGBA() { return new ColorRGBA(r, g, b, 255); }
    @Override
    public ColorHSV toHSV() {
        //TODO
        return new ColorHSV(0, 0, 0);
    }

    @Override
    public String toString() {
        return "ColorRGB{" + "r=" + r + ", g=" + g + ", b=" + b + '}';
    }
}
