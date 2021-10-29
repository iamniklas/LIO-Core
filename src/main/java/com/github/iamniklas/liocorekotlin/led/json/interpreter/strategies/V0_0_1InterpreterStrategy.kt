package com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure

class V0_0_1InterpreterStrategy :
    com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.IInterpreterStrategy {
    override fun interpretLine(_line: String?): Array<com.github.iamniklas.liocorekotlin.led.ColorRGB> {
        //TODO
        /*val colorData = _line!!.split(" ".toRegex()).toTypedArray()
        val colors = Array<ColorRGB>(1)
        for (i in colorData.indices) {
            val ledData = colorData[i].split("-".toRegex()).toTypedArray()
            val arr = Stream.of(*ledData).mapToInt { s: String -> s.toInt() }.toArray()
            colors.add(ColorRGB(arr[0], arr[1], arr[2]))
        }*/
        return arrayOf()
    }

    override fun interpretJson(_json: String?): com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure? {
        //TODO
        /*val jsonProc = Gson().fromJson(_json, LEDJsonProcedure001Uncompiled::class.java)
        val result = LEDJsonProcedure()
        result.mMetaInfo = jsonProc.mMetaInfo
        val l: MutableList<LEDStateArray> = ArrayList()
        for (i in 0..299) {
            l.add(LEDStateArray())
            l[i].mLEDState = interpretLine(jsonProc.mLEDStates[i])
        }
        result.mLEDStates = arrayOfNulls(300)
        println(result.mLEDStates.size)
        return result*/
        return com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure()
    }
}