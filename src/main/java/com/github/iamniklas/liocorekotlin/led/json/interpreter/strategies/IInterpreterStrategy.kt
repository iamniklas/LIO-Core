package com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure

interface IInterpreterStrategy {
    fun interpretLine(_line: String?): Array<com.github.iamniklas.liocorekotlin.led.ColorRGB>
    fun interpretJson(_json: String?): com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure?
}