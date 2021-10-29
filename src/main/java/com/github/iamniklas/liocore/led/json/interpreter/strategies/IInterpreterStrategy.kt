package com.github.iamniklas.liocore.led.json.interpreter.strategies

import com.github.iamniklas.liocore.led.ColorRGB
import com.github.iamniklas.liocore.led.json.LEDJsonProcedure

interface IInterpreterStrategy {
    fun interpretLine(_line: String?): Array<ColorRGB>
    fun interpretJson(_json: String?): LEDJsonProcedure?
}