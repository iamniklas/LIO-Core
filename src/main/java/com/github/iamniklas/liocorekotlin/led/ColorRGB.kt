package com.github.iamniklas.liocorekotlin.led

import com.google.gson.annotations.SerializedName
import java.awt.Color

class ColorRGB {
    @SerializedName("R")
    var r = 255

    @SerializedName("G")
    var g = 255

    @SerializedName("B")
    var b = 255

    constructor(_r: Int, _g: Int, _b: Int) {
        r = _r
        g = _g
        b = _b
    }

    constructor(_color: com.github.iamniklas.liocorekotlin.led.ColorRGB) {
        r = _color.r
        g = _color.g
        b = _color.b
    }

    fun filter(_channel: com.github.iamniklas.liocorekotlin.led.ColorChannel?): com.github.iamniklas.liocorekotlin.led.ColorRGB {
        return when (_channel) {
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Red -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                0,
                g,
                b
            )
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Green -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                r,
                0,
                b
            )
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Blue -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                r,
                g,
                0
            )
            else -> com.github.iamniklas.liocorekotlin.led.ColorRGB(0, 0, 0)
        }
    }

    fun setChannel(_channel: com.github.iamniklas.liocorekotlin.led.ColorChannel?, _value: Int) {
        when (_channel) {
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Red -> r = _value
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Green -> g = _value
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Blue -> b = _value
            else -> {
            }
        }
    }

    //Cuts a channel from the lower side - if the channel value is higher than the filter it will be set to 0
    fun cutLow(_channel: com.github.iamniklas.liocorekotlin.led.ColorChannel?, _filter: Int): com.github.iamniklas.liocorekotlin.led.ColorRGB {
        var _filter = _filter
        _filter = Math.max(Math.min(_filter, 255), 0)
        return when (_channel) {
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Red -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                cutLow(r, _filter),
                g,
                b
            )
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Green -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                r,
                cutLow(g, _filter),
                b
            )
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Blue -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                r,
                g,
                cutLow(b, _filter)
            )
            else -> this
        }
    }

    //Cuts a channel from the higher side - if the channel value is lower than the filter it will be set to 0
    fun cutHigh(_channel: com.github.iamniklas.liocorekotlin.led.ColorChannel?, _filter: Int): com.github.iamniklas.liocorekotlin.led.ColorRGB {
        var _filter = _filter
        _filter = Math.max(Math.min(_filter, 255), 0)
        return when (_channel) {
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Red -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                cutHigh(r, _filter),
                g,
                b
            )
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Green -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                r,
                cutHigh(g, _filter),
                b
            )
            com.github.iamniklas.liocorekotlin.led.ColorChannel.Blue -> com.github.iamniklas.liocorekotlin.led.ColorRGB(
                r,
                g,
                cutHigh(b, _filter)
            )
            else -> this
        }
    }

    fun toRGBA(_alpha: Int): com.github.iamniklas.liocorekotlin.led.ColorRGBA {
        return com.github.iamniklas.liocorekotlin.led.ColorRGBA(r, g, b, _alpha)
    }

    fun toHSV(): com.github.iamniklas.liocorekotlin.led.ColorHSV {
        return com.github.iamniklas.liocorekotlin.led.ColorHSV(0, 0.0f, 0.0f)
    }

    private fun cutLow(_value: Int, _filter: Int): Int {
        return if (_value >= _filter) {
            0
        } else {
            _value
        }
    }

    private fun cutHigh(_value: Int, _filter: Int): Int {
        return if (_value <= _filter) {
            0
        } else {
            _value
        }
    }

    fun dim(_percentage: Float): com.github.iamniklas.liocorekotlin.led.ColorRGB {
        return com.github.iamniklas.liocorekotlin.led.ColorRGB(
            (r * _percentage).toInt(),
            (g * _percentage).toInt(),
            (b * _percentage).toInt()
        )
    }

    fun dim(_percentageR: Float, _percentageG: Float, _percentageB: Float): com.github.iamniklas.liocorekotlin.led.ColorRGB {
        return com.github.iamniklas.liocorekotlin.led.ColorRGB(
            (r * _percentageR).toInt(),
            (g * _percentageG).toInt(),
            (b * _percentageB).toInt()
        )
    }

    fun toSystemColor(): Color {
        return Color(r, g, b)
    }

    override fun toString(): String {
        return "$r $g $b"
    }

    companion object {
        val black = com.github.iamniklas.liocorekotlin.led.ColorRGB(0, 0, 0)
        val white = com.github.iamniklas.liocorekotlin.led.ColorRGB(255, 255, 255)
        val white50 = com.github.iamniklas.liocorekotlin.led.ColorRGB(128, 128, 128)
        val red = com.github.iamniklas.liocorekotlin.led.ColorRGB(255, 0, 0)
        val green = com.github.iamniklas.liocorekotlin.led.ColorRGB(0, 255, 0)
        val blue = com.github.iamniklas.liocorekotlin.led.ColorRGB(0, 0, 255)
        val orange = com.github.iamniklas.liocorekotlin.led.ColorRGB(255, 255, 0)
        val magenta = com.github.iamniklas.liocorekotlin.led.ColorRGB(255, 0, 255)
        val torquoise = com.github.iamniklas.liocorekotlin.led.ColorRGB(0, 255, 255)

        fun fromSystemColor(_systemColor: Color): com.github.iamniklas.liocorekotlin.led.ColorRGB {
            return com.github.iamniklas.liocorekotlin.led.ColorRGB(
                _systemColor.red,
                _systemColor.green,
                _systemColor.blue
            )
        }
    }
}