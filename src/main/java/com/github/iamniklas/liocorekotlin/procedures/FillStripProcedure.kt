package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import com.github.iamniklas.liocorekotlin.led.LEDStripManager
import procedures.models.Direction
import java.awt.Color

//Animation to fill the strip from a given direction (left, right, center, bounds) with a given color 
class FillStripProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    private var mFillColor = Color.BLACK
    var mSpeed = 1
    var mDirection: Direction? = Direction.Left
    override fun start() {}
    public override fun update() {
        when (mDirection) {
            Direction.Center -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT / 2 - 1 + mStep + i, mFillColor)
                    mStrip!!.setPixel(com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT / 2 - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            Direction.CenterInvert -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(mStep + i, mFillColor)
                    mStrip!!.setPixel(com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            Direction.Left -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(mStep + i, mFillColor)
                    i++
                }
            }
            Direction.Right -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            else -> {
            }
        }
        mStep += mSpeed
        if (mStep >= mSteps) {
            if (!mIsSubProcedure) {
                finishProcedure()
            }
        }
    }

    init {
        mFillColor = _bundle.colorPrimary!!.toSystemColor()
        mSpeed = Math.round(_bundle.speed!!)
        mDirection = _bundle.direction
        mSteps = com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT
        if (mDirection == Direction.Center || mDirection == Direction.CenterInvert) {
            mSteps /= 2
        }
    }
}