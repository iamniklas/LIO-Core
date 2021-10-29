package com.github.iamniklas.liocorekotlin.led.json

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.google.gson.annotations.SerializedName

class LEDStateArray {
    @SerializedName("led_state")
    lateinit var mLEDState: Array<com.github.iamniklas.liocorekotlin.led.ColorRGB>
}