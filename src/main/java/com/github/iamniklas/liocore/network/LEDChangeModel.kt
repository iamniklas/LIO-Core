package com.github.iamniklas.liocore.network

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.procedures.ProcedureType
import com.google.gson.annotations.SerializedName

class LEDChangeModel {
    @SerializedName(value = "procedure")
    var mProcedure: ProcedureType? = null

    @SerializedName(value = "bundle")
    var mBundle: LEDDataBundle? = null
}