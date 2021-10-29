package com.github.iamniklas.liocorekotlin.led.json

import com.google.gson.annotations.SerializedName

class LEDJsonProcedure {
    @SerializedName("meta")
    var mMetaInfo: com.github.iamniklas.liocorekotlin.led.json.MetaInfo? = null

    @SerializedName("led_state")
    lateinit var mLEDStates: Array<com.github.iamniklas.liocorekotlin.led.json.LEDStateArray?>
}