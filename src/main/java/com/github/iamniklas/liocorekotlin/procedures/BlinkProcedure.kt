package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import java.awt.Color

class BlinkProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    var mBlinkColor: Color
    var mFrames = 10
    override var mModulo = 2
    override fun start() {}
    public override fun update() {
        mStep++
        if (mStep % mModulo == 0) {
            mStrip!!.setAllPixels(mBlinkColor)
        } else {
            mStrip!!.setAllPixels(com.github.iamniklas.liocorekotlin.led.ColorRGB.black.toSystemColor())
        }
        if (mStep == mSteps) {
            mStrip!!.setAllPixels(com.github.iamniklas.liocorekotlin.led.ColorRGB.black.toSystemColor())
            finishProcedure()
        }
    }

    init {
        mBlinkColor = _bundle.colorPrimary!!.toSystemColor()
        mFrames = _bundle.duration!!
        mModulo = _bundle.modulo!!
        mSteps = mFrames
    }
}