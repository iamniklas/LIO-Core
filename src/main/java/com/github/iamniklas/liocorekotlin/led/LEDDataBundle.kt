package com.github.iamniklas.liocorekotlin.led

import com.github.iamniklas.liocore.interpolation.InterpolationType
import com.github.iamniklas.liocorekotlin.procedures.ProcedureCalls
import procedures.models.Direction

class LEDDataBundle {
    var colorPrimary: com.github.iamniklas.liocorekotlin.led.ColorRGB? = null
    var colorSecondary: com.github.iamniklas.liocorekotlin.led.ColorRGB? = null
    var colorTertiary: com.github.iamniklas.liocorekotlin.led.ColorRGB? = null
    var data: String? = null
    var value1: Float? = null
    var value2: Float? = null
    var value3: Float? = null
    var modulo: Int? = null
    var interpolation: InterpolationType? = null
    var direction: Direction? = null
    var bpm: Int? = null
    var repetitions: Float? = null
    var speed: Float? = null
    var duration: Int? = null
    var pulsating: Boolean? = null
    var path: String? = null

    //subBundle
    var isSubProcedure: Boolean? = null
    var indeterminate: Boolean? = null
    var puModulo: Int? = null
    var puModuloInvert: Boolean? = null
    var ledStrip: com.github.iamniklas.liocorekotlin.led.LEDStripManager? = null
    var procedureCalls: com.github.iamniklas.liocorekotlin.procedures.ProcedureCalls? = null
}