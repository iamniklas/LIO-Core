package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.ColorRGBA
import com.github.iamniklas.liocorekotlin.led.LEDDataBundle

//Fade every pixel from a base color to a target color
class FadeToUniformColorProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    var mBaseColor: com.github.iamniklas.liocorekotlin.led.ColorRGB?
    var mTargetColor: com.github.iamniklas.liocorekotlin.led.ColorRGB?
    var mDuration = 0.0f
    var mCounter = 0
    override var mSteps = 0
    var mAlphaStep = 0.0f
    private var mAlphaAddValue = 0.0f
    override fun start() {}
    public override fun update() {
        mCounter++
        mAlphaStep += mAlphaAddValue
        val outputColor = com.github.iamniklas.liocorekotlin.led.ColorRGBA(
            mBaseColor!!.r,
            mBaseColor!!.g,
            mBaseColor!!.b,
            (mAlphaStep * 255).toInt()
        )
        mStrip!!.setAllPixels(outputColor.toRGB(mTargetColor!!).toSystemColor())
        if (mCounter > mSteps) {
            mStrip!!.setAllPixels(mBaseColor!!.toSystemColor())
            finishProcedure()
        }
    }

    init {
        mTargetColor = _bundle.colorPrimary
        mBaseColor = _bundle.colorSecondary
        mDuration = _bundle.duration!!.toFloat()
        mSteps = Math.ceil((mDuration / (mStrip!!.frametime / 1000.0f)).toDouble()).toInt()
        mAlphaAddValue = 1 / mSteps.toFloat()
    }
}