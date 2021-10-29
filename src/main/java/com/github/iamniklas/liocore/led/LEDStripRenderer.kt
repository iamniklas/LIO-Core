package com.github.iamniklas.liocore.led

import java.awt.Color
import javax.swing.JLabel

class LEDStripRenderer {
    private val colorData = arrayOfNulls<Color>(300)
    fun setPixel(_index: Int, _color: Color?) {
        colorData[_index] = _color
    }

    fun render() {
        for (i in 0..leds.size) {
            leds[i]!!.foreground = colorData[i]
        }
    }

    companion object {
        lateinit var leds: Array<JLabel?>
    }
}