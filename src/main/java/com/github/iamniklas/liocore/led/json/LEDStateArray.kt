package com.github.iamniklas.liocore.led.json

import com.github.iamniklas.liocore.led.ColorRGB
import com.google.gson.annotations.SerializedName

class LEDStateArray {
    @SerializedName("led_state")
    lateinit var mLEDState: Array<ColorRGB>
}