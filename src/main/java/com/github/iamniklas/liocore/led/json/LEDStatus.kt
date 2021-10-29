package com.github.iamniklas.liocore.led.json

import com.google.gson.annotations.SerializedName

class LEDStatus {
    @SerializedName(value = "active")
    var mActive = false
}