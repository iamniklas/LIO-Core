package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import com.github.iamniklas.liocorekotlin.led.LEDStripManager
import java.awt.Color
import java.awt.geom.Area
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SimpleBPMProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    var mAreas = ArrayList<Area>()
    var mBpm = 60
    var mBeatStep = 0.0f
    var mCounter = 0.0f
    override fun start() {}
    public override fun update() {
        mCounter = if (mCounter > mBeatStep) 0.0f else mCounter + 20.0f
        mStrip!!.setArea(0, com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT, Color.BLACK)
        if (mCounter == 0.0f) {
            val dtf = DateTimeFormatter.ofPattern("mm:ss:SSS")
            val now = LocalDateTime.now()
            System.out.println(dtf.format(now))
            mStrip!!.setArea(0, com.github.iamniklas.liocorekotlin.led.LEDStripManager.LED_COUNT, Color.RED)
        }
    }

    init {
        mBeatStep = 60.0f / mBpm.toFloat() * 1000.0f
        System.out.println("BEATSTEP: $mBeatStep")
    }
}