package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.LEDDataBundle
import com.github.iamniklas.liocorekotlin.procedures.Procedure

class MultiProcedure(_bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle) : com.github.iamniklas.liocorekotlin.procedures.Procedure(_bundle) {
    lateinit var mProcedures: Array<com.github.iamniklas.liocorekotlin.procedures.Procedure>
    override fun start() {
        for (procedure in mProcedures) {
            procedure.start()
            //mSteps = procedure.mSteps > mSteps ? procedure.mSteps : mSteps; 
        }
        mSteps = 60
    }

    public override fun update() {
        mStep++
        for (procedure in mProcedures) {
            procedure.update()
        }
        if (mStep >= mSteps) {
            finishProcedure()
        }
    }
}