package com.github.iamniklas.liocore.led.colorspace;

import com.github.iamniklas.colorspaces.*;

public class LIOColor {
    public int r;
    public int g;
    public int b;

    public LIOColor(int _r, int _g, int _b) {
        r = _r;
        g = _g;
        b = _b;
    }

    public static LIOColor fromRGB(ColorRGB rgb) {
        return new LIOColor(rgb.r, rgb.g, rgb.b);
    }

    public static LIOColor fromRGBA(ColorRGBA rgba) {
        return LIOColor.fromRGB(rgba.toRGB());
    }

    public static LIOColor fromHSV(ColorHSV hsv) {
        return LIOColor.fromRGB(hsv.toRGB());
    }

    public static ColorHSV toHSV(LIOColor color) {
        return new ColorRGB(color.r, color.g, color.b).toHSV();
    }

    public ColorRGB toRGB() {
        return new ColorRGB(r, g, b);
    }
}
