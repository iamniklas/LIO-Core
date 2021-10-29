package com.github.iamniklas.liocore.led;

public class LEDStripConfiguration {
    int ledCount = 300;

    int gpioPin = 18;

    int frequency = 800000;

    int dma = 10;

    int brightness = 255;

    int mPwmChannel = 18;

    boolean invert = false;

    boolean clearOnExit = true;
}
