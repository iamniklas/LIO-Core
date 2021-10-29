package com.github.iamniklas.liocore.procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.json.LEDJsonProcedure
import com.github.iamniklas.liocore.led.json.interpreter.FileVersions
import com.github.iamniklas.liocore.led.json.interpreter.LEDInterpreter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class JsonProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mLEDJsonProcedure: LEDJsonProcedure?
    override fun start() {}
    public override fun update() {
        for (i in 0 .. mLEDJsonProcedure!!.mMetaInfo!!.mLedCount) {
            mStrip!!.setPixel(i, mLEDJsonProcedure!!.mLEDStates[mStep]!!.mLEDState[i].toSystemColor())
        }
        mStep += 5
        if (mStep >= mSteps) {
            finishProcedure()
        }
    }

    private fun loadFromFile(_path: String?): LEDJsonProcedure? {
        val encoded: ByteArray
        return try {
            encoded = Files.readAllBytes(Paths.get(_path))
            LEDInterpreter.interpretJson(FileVersions.V0_0_1, "")//String(encoded, StandardCharsets.US_ASCII))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    init {
        mLEDJsonProcedure = loadFromFile(_bundle.path)
        mSteps = mLEDJsonProcedure!!.mLEDStates.size - 1

        //mIsSubProcedure = (boolean) _bundle.get(ProcedureBundleFields.IS_SUB_PROCEDURE);
    }
}