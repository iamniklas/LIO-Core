package com.github.iamniklas.liocorekotlin.led

import com.github.iamniklas.liocorekotlin.led.json.LEDStatus
import com.github.iamniklas.liocorekotlin.network.LEDChangeModel
import java.awt.Color
import javax.swing.JLabel

class LEDStripManager(_leds: Array<JLabel?>, _clearOnExit: Boolean) :
    com.github.iamniklas.liocorekotlin.procedures.ProcedureCalls {
    private val mLedStripConfig = com.github.iamniklas.liocorekotlin.led.LEDStripConfig()

    var mStripData =
        com.github.iamniklas.liocorekotlin.led.LEDStrip(com.github.iamniklas.liocorekotlin.led.LEDStripManager.Companion.LED_COUNT)

    var mProcContainer = com.github.iamniklas.liocorekotlin.procedures.ProcContainer(this)
    var frametime = 16
    var mStrip = com.github.iamniklas.liocorekotlin.led.LEDStripRenderer()
    fun update() {
        mProcContainer.update()
        mProcContainer.postUpdate()
        for (i in 0 .. mStripData.mStrip.size) {
            mStrip.setPixel(i, mStripData.mStrip[i])
        }
        mStrip.render()
        try {
            Thread.sleep(frametime.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    /**
     * @param _pxl The pixel index
     * @param _color The color to set the pixel
     */
    fun setPixel(_pxl: Int, _color: Color) {
        var _color = _color
        _color = Color(Math.max(0, _color.red), Math.max(0, _color.green), Math.max(0, _color.blue))
        mStripData.mStrip[_pxl] = _color
    }

    fun setPixel(_pxl: Int, _r: Int, _g: Int, _b: Int) {
        val c = Color(Math.max(0, _r), Math.max(0, _g), Math.max(0, _b))
        mStripData.mStrip[_pxl] = c
    }

    /**
     * @param _startPxl The start index for the area
     * @param _endPxl The end index for the area
     * @param _color The color to fill the strip area with
     */
    fun setArea(_startPxl: Int, _endPxl: Int, _color: Color?) {
        for (i in _startPxl .. _endPxl) {
            mStripData.mStrip[i] = _color
        }
    }

    /**
     * @param _color The color to fill the strip with
     */
    fun setAllPixels(_color: Color?) {
        for (i in 0 ..com.github.iamniklas.liocorekotlin.led.LEDStripManager.Companion.LED_COUNT) {
            mStripData.mStrip[i] = _color
        }
    }

    override fun onProcedureStart(_proc: com.github.iamniklas.liocorekotlin.procedures.Procedure?) {
        _proc!!.start()
    }

    override fun onProcedureQueued() {}
    override fun onProcedureFinish() {}

    companion object {
        const val LED_COUNT = 300
        var mLEDStatus = com.github.iamniklas.liocorekotlin.led.json.LEDStatus()
    }

    init {
        System.out.println("LED Strip \tINIT \tSTART")
        frametime = 16
        com.github.iamniklas.liocorekotlin.led.LEDStripRenderer.Companion.leds = _leds
        System.out.println("LED Strip \tINIT \tDONE")
        val changeModel = LEDChangeModel()
        val bundle = com.github.iamniklas.liocorekotlin.led.LEDDataBundle()
        bundle.ledStrip = this
        bundle.procedureCalls = this
        bundle.data =
            "indices = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 299]; function update(strip, procedure, step, totalSteps) {d = Math.abs(Math.sin(step * Math.PI/180)); for(i = 0; i < indices.length; i++) { strip.setPixel(indices[i], 0, parseInt(d * 255), 0); } if(step >= totalSteps) { procedure.finish(); } }"
        changeModel.mProcedure = com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Javascript
        changeModel.mBundle = bundle
        val proc = com.github.iamniklas.liocorekotlin.procedures.ProcedureFactory.getProcedure(changeModel.mProcedure, changeModel.mBundle!!)!! as com.github.iamniklas.liocorekotlin.procedures.JavascriptProcedure
        mProcContainer.queueProcedure(proc)
    }
}