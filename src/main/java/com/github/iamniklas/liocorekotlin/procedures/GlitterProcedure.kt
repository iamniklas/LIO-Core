package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import com.github.iamniklas.liocorekotlin.led.LEDStripManager
import java.util.*

class GlitterProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    var mBaseColor: com.github.iamniklas.liocorekotlin.led.ColorRGB?
    var mLedX = ArrayList<Float>()
    var someDumbCounter = 0.0f
    var mSpeed = 0.1f
    var tcount = 0.0f
    override fun start() {
        val random = Random()
        for (i in 0 .. com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT) {
            mLedX[i] = (random.nextFloat() * (Math.PI * 2 - 0.0f) + 0.0f).toFloat()
        }
    }

    public override fun update() {
        tcount += mSpeed
        if (tcount > Math.PI * 2) tcount = 0f
        for (i in 0 .. com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT) {
            mStrip!!.setPixel(
                i, mBaseColor!!.dim(
                    Math.abs(Math.sin((tcount + mLedX[i]).toDouble())).toFloat()
                ).toSystemColor()
            )
        }
    }

    init {
        mBaseColor = _bundle.colorPrimary
        mSpeed = _bundle.speed!!
        for (i in 0 .. com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT) {
            mLedX.add(0.0f)
        }
        start()
    }
}