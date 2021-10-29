package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.LEDStripManager
import java.util.*

class ProcContainer(var mLedStrip: com.github.iamniklas.liocorekotlin.led.LEDStripManager) {
    var mProcedures = ArrayList<com.github.iamniklas.liocorekotlin.procedures.Procedure>()
    fun queueProcedure(_proc: com.github.iamniklas.liocorekotlin.procedures.Procedure) {
        mProcedures.add(_proc)
        _proc.mCallbacks!!.onProcedureQueued()
    }

    val activeProcedure: com.github.iamniklas.liocorekotlin.procedures.Procedure?
        get() = if (mProcedures.size > 0) mProcedures[0] else null

    fun removeCurrentProcedure() {
        if (mProcedures.size > 0) {
            mProcedures.removeAt(0)
        }
        if (mProcedures.size > 0) {
            mProcedures[0].mCallbacks!!.onProcedureStart(mProcedures[0])
        }
    }

    fun update() {
        if (mProcedures.size < 1) return
        mProcedures[0].update()
    }

    fun postUpdate() {
        if (mProcedures.size < 1) return
        mProcedures[0].postUpdate()
    }
}