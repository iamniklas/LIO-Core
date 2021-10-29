package com.github.iamniklas.liocore.led

import com.github.iamniklas.liocore.interpolation.InterpolationType
import com.github.iamniklas.liocore.procedures.ProcedureCalls
import procedures.models.Direction

class LEDDataBundle {
    var colorPrimary: ColorRGB? = null
    var colorSecondary: ColorRGB? = null
    var colorTertiary: ColorRGB? = null
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
    var ledStrip: LEDStripManager? = null
    var procedureCalls: ProcedureCalls? = null
}