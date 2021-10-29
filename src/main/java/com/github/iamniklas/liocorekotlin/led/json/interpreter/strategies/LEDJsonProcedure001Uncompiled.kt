package com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies

import com.github.iamniklas.liocorekotlin.led.json.MetaInfo
import com.google.gson.annotations.SerializedName

class LEDJsonProcedure001Uncompiled {
    @SerializedName("meta")
    var mMetaInfo: com.github.iamniklas.liocorekotlin.led.json.MetaInfo? = null

    @SerializedName("led_state")
    lateinit var mLEDStates: Array<String?>
}