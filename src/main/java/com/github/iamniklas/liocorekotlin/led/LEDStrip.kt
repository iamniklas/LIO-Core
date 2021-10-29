package com.github.iamniklas.liocorekotlin.led

import java.awt.Color
import java.util.*

class LEDStrip(_ledCount: Int) {
    var mStrip = ArrayList<Color?>(300)
    fun getColorPyPixel(_id: Int): Color? {
        return mStrip[_id]
    }

    init {
        mStrip = ArrayList(_ledCount)

        for (i in 0 ..com.github.iamniklas.liocorekotlin.led.LEDStripManager.Companion.LED_COUNT) {
            mStrip.add(Color.BLACK)
        }
    }
}