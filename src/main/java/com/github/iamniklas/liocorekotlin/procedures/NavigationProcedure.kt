package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import procedures.models.navigation.PositionMarker
import procedures.models.navigation.Size
import java.util.*

class NavigationProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    var mMarkers = ArrayList<PositionMarker>()
    override fun start() {}
    public override fun update() {
        for (i in 0..mMarkers.size) {
            val marker = mMarkers[i]
            when (marker.mMarkerSize) {
                Size.Large -> {
                }
                Size.Medium -> {
                }
                Size.Small -> {
                }
                Size.Single -> mStrip!!.setPixel((marker.mXPosition * 180.0f).toInt(), marker.mColor.toSystemColor())
                else -> {
                }
            }
        }
        finishProcedure()
    }
}