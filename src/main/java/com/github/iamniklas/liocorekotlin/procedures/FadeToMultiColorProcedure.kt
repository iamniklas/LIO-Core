package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.ColorRGB.Companion.fromSystemColor
import com.github.iamniklas.liocorekotlin.led.ColorRGBA
import com.github.iamniklas.liocorekotlin.led.LEDDataBundle

//Action is like FadeToUniformColorProcedure, but individual for every pixel
class FadeToMultiColorProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    var mBaseColor = arrayOfNulls<com.github.iamniklas.liocorekotlin.led.ColorRGB>(300)
    var mTargetColor: com.github.iamniklas.liocorekotlin.led.ColorRGB?
    var mSpeed = 0.0f
    var mCounter = 0
    override var mSteps = 0
    var mAlphaStep = FloatArray(300)
    private val mAlphaAddValue = FloatArray(300)
    override fun start() {
        for (i in 0..mBaseColor.size) {
            mBaseColor[i] = fromSystemColor(mStrip!!.mStripData.getColorPyPixel(i)!!)
        }
    }

    public override fun update() {
        for (i in 0..mAlphaStep.size) {
            mAlphaStep[i] += mAlphaAddValue[i]
            val outputColor =
                com.github.iamniklas.liocorekotlin.led.ColorRGBA(
                    mBaseColor[i]!!.r,
                    mBaseColor[i]!!.g,
                    mBaseColor[i]!!.b,
                    255 - (mAlphaStep[i] * 255).toInt()
                )
            mStrip!!.setPixel(i, outputColor.toRGB(mTargetColor!!).toSystemColor())
        }
        mStep++
        if (mStep > mSteps) {
            finishProcedure()
        }
    }

    init {
        mTargetColor = _bundle.colorPrimary
        System.out.println(mTargetColor)
        mSpeed = _bundle.speed!!
        mSteps = Math.ceil((mSpeed / (mStrip!!.frametime / 1000.0f)).toDouble()).toInt()
        for (i in 0..mAlphaAddValue.size) {
            mAlphaAddValue[i] = 1 / mSteps.toFloat()
        }
        start()

        //System.out.println("Multi-Fade Steps " + mSteps);
    }
}