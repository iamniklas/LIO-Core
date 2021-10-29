package com.github.iamniklas.liocore.led.json.interpreter.strategies

import com.github.iamniklas.liocore.led.json.MetaInfo
import com.google.gson.annotations.SerializedName

class LEDJsonProcedure001Uncompiled {
    @SerializedName("meta")
    var mMetaInfo: MetaInfo? = null

    @SerializedName("led_state")
    lateinit var mLEDStates: Array<String?>
}