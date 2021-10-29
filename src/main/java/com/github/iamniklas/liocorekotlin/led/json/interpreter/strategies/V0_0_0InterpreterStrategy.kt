package com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure
import com.google.gson.Gson

class V0_0_0InterpreterStrategy :
    com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.IInterpreterStrategy {
    override fun interpretLine(_line: String?): Array<com.github.iamniklas.liocorekotlin.led.ColorRGB> {
        return arrayOf()
    }

    override fun interpretJson(_json: String?): com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure? {
        //TODO
        //return Gson().fromJson<LEDJsonProcedure>(_json, LEDJsonProcedure::class)
        return com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure()
    }
}