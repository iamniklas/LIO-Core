package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import java.awt.Color

//Set every strip's pixel to a uniform color in a instant 1 frame animation
class ColorInstantSetProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    var mTargetColor: Color
    override fun start() {}
    public override fun update() {
        mStrip!!.setAllPixels(mTargetColor)
        postUpdate()
        finishProcedure()
    }

    init {
        mTargetColor = _bundle.colorPrimary!!.toSystemColor()
    }
}