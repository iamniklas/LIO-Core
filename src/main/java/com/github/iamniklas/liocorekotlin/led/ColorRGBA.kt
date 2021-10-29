package com.github.iamniklas.liocorekotlin.led

class ColorRGBA(_r: Int, _g: Int, _b: Int, _a: Int) {
    var r = 255
    var g = 255
    var b = 255
    var a = 255
    fun toRGB(): com.github.iamniklas.liocorekotlin.led.ColorRGB {
        return com.github.iamniklas.liocorekotlin.led.ColorRGB(
            (255 - a) * com.github.iamniklas.liocorekotlin.led.ColorRGB.Companion.black.r + a / 255 * r,
            (255 - a) * com.github.iamniklas.liocorekotlin.led.ColorRGB.Companion.black.g + a / 255 * g,
            (255 - a) * com.github.iamniklas.liocorekotlin.led.ColorRGB.Companion.black.b + a / 255 * b
        )
    }

    fun toRGB(_baseColor: com.github.iamniklas.liocorekotlin.led.ColorRGB): com.github.iamniklas.liocorekotlin.led.ColorRGB {
        val rgb = com.github.iamniklas.liocorekotlin.led.ColorRGB(
            ((1 - a / 255.0f) * _baseColor.r + a / 255.0f * r).toInt(),
            ((1 - a / 255.0f) * _baseColor.g + a / 255.0f * g).toInt(),
            ((1 - a / 255.0f) * _baseColor.b + a / 255.0f * b).toInt()
        )
        rgb.r = Math.max(0, Math.min(rgb.r, 255))
        rgb.g = Math.max(0, Math.min(rgb.g, 255))
        rgb.b = Math.max(0, Math.min(rgb.b, 255))
        return rgb
    }

    fun dim(_percentage: Float): com.github.iamniklas.liocorekotlin.led.ColorRGBA {
        return com.github.iamniklas.liocorekotlin.led.ColorRGBA(
            (r * _percentage).toInt(),
            (g * _percentage).toInt(),
            (b * _percentage).toInt(),
            (a * _percentage).toInt()
        )
    }

    fun dim(_percentageR: Float, _percentageG: Float, _percentageB: Float, _percentageA: Float): com.github.iamniklas.liocorekotlin.led.ColorRGBA {
        return com.github.iamniklas.liocorekotlin.led.ColorRGBA(
            (r * _percentageR).toInt(),
            (g * _percentageG).toInt(),
            (b * _percentageB).toInt(),
            (a * _percentageA).toInt()
        )
    }

    override fun toString(): String {
        return "$r $g $b $a"
    }

    init {
        r = _r
        g = _g
        b = _b
        a = _a
    }
}