package com.github.iamniklas.liocore.led.json.interpreter.strategies

import com.github.iamniklas.liocore.led.ColorRGB
import com.github.iamniklas.liocore.led.json.LEDJsonProcedure
import com.google.gson.Gson

class V0_0_0InterpreterStrategy : IInterpreterStrategy {
    override fun interpretLine(_line: String?): Array<ColorRGB> {
        return arrayOf()
    }

    override fun interpretJson(_json: String?): LEDJsonProcedure? {
        //TODO
        //return Gson().fromJson<LEDJsonProcedure>(_json, LEDJsonProcedure::class)
        return LEDJsonProcedure()
    }
}