package com.github.iamniklas.liocorekotlin.led.json.interpreter

import com.github.iamniklas.liocorekotlin.led.ColorRGB
import com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure
import com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.V0_0_0InterpreterStrategy
import com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.V0_0_1InterpreterStrategy
import com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.V0_0_2InterpreterStrategy

object LEDInterpreter {
    //@Throws(NotAvailableException::class)
    fun interpretLine(_fileVersion: com.github.iamniklas.liocorekotlin.led.json.interpreter.FileVersions?, _line: String?): Array<com.github.iamniklas.liocorekotlin.led.ColorRGB>? {
        return when (_fileVersion) {
            com.github.iamniklas.liocorekotlin.led.json.interpreter.FileVersions.V0_0_0 -> throw com.github.iamniklas.liocorekotlin.led.json.interpreter.NotAvailableException(
                "V0_0_0Interpreter cannot interpret lines"
            )
            com.github.iamniklas.liocorekotlin.led.json.interpreter.FileVersions.V0_0_1 -> com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.V0_0_1InterpreterStrategy()
                .interpretLine(_line)
            com.github.iamniklas.liocorekotlin.led.json.interpreter.FileVersions.V0_0_2 -> com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.V0_0_2InterpreterStrategy()
                .interpretLine(_line)
            else -> null
        }
    }

    fun interpretJson(_fileVersion: com.github.iamniklas.liocorekotlin.led.json.interpreter.FileVersions?, _json: String?): com.github.iamniklas.liocorekotlin.led.json.LEDJsonProcedure? {
        return when (_fileVersion) {
            com.github.iamniklas.liocorekotlin.led.json.interpreter.FileVersions.V0_0_0 -> com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.V0_0_0InterpreterStrategy()
                .interpretJson(_json)
            com.github.iamniklas.liocorekotlin.led.json.interpreter.FileVersions.V0_0_1 -> com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.V0_0_1InterpreterStrategy()
                .interpretJson(_json)
            com.github.iamniklas.liocorekotlin.led.json.interpreter.FileVersions.V0_0_2 -> com.github.iamniklas.liocorekotlin.led.json.interpreter.strategies.V0_0_2InterpreterStrategy()
                .interpretJson(_json)
            else -> null
        }
    }
}