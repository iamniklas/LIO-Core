package com.github.iamniklas.liocore.led.typeinterfaces;

import com.github.iamniklas.liocore.led.colorspace.ColorHSV;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.led.colorspace.ColorRGBA;

public interface IConvertibleColor {
    ColorRGB toRGB();
    ColorRGBA toRGBA();
    ColorHSV toHSV();
}
