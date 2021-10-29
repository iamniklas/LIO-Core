package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.ColorHSV
import com.github.iamniklas.liocorekotlin.led.LEDDataBundle

class RainbowMonoProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    var mColorHSV = com.github.iamniklas.liocorekotlin.led.ColorHSV(0, 1.0f, 1.0f)
    var mHueCounter = 0
    var mRepetitions = 1f
    var mSpeed = 1f
    override fun start() {}
    public override fun update() {
        mColorHSV.h = (if (mColorHSV.h > 360.0f) 0 else mColorHSV.h + mSpeed).toInt()
        mStrip!!.setAllPixels(mColorHSV.ToRGB().toSystemColor())
    }

    init {
        mSpeed = _bundle.speed!!
    }
}