package com.github.iamniklas.liocorekotlin.network

import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import com.github.iamniklas.liocorekotlin.procedures.ProcedureType
import com.google.gson.annotations.SerializedName

class LEDChangeModel {
    @SerializedName(value = "procedure")
    var mProcedure: com.github.iamniklas.liocorekotlin.procedures.ProcedureType? = null

    @SerializedName(value = "bundle")
    var mBundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle? = null
}