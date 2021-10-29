package com.github.iamniklas.liocorekotlin.procedures

import com.github.iamniklas.liocorekotlin.led.LEDDataBundle

object ProcedureFactory {
    fun getProcedure(_types: com.github.iamniklas.liocorekotlin.procedures.ProcedureType?, _bundle: com.github.iamniklas.liocorekotlin.led.LEDDataBundle): com.github.iamniklas.liocorekotlin.procedures.Procedure? {
        return when (_types) {
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.BootComplete -> com.github.iamniklas.liocorekotlin.procedures.BootCompleteProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.ColorInstantSet -> com.github.iamniklas.liocorekotlin.procedures.ColorInstantSetProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.FadeInFadeOut -> com.github.iamniklas.liocorekotlin.procedures.FadeInFadeOutProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.FadeToMultiColor -> com.github.iamniklas.liocorekotlin.procedures.FadeToMultiColorProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.FadeToUniformColor -> com.github.iamniklas.liocorekotlin.procedures.FadeToUniformColorProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Fill -> com.github.iamniklas.liocorekotlin.procedures.FillStripProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.FillInterpolated -> com.github.iamniklas.liocorekotlin.procedures.FillStripInterpolatedProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Rainbow -> com.github.iamniklas.liocorekotlin.procedures.RainbowProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.RainbowMono -> com.github.iamniklas.liocorekotlin.procedures.RainbowMonoProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.SimpleBPM -> com.github.iamniklas.liocorekotlin.procedures.SimpleBPMProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.MultiProcedure -> com.github.iamniklas.liocorekotlin.procedures.MultiProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Blink -> com.github.iamniklas.liocorekotlin.procedures.BlinkProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Glitter -> com.github.iamniklas.liocorekotlin.procedures.GlitterProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Navigation -> com.github.iamniklas.liocorekotlin.procedures.NavigationProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Progress -> com.github.iamniklas.liocorekotlin.procedures.ProgressProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Lightning -> com.github.iamniklas.liocorekotlin.procedures.LightningProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.NoLongerReady -> com.github.iamniklas.liocorekotlin.procedures.NoLongerReadyProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.JsonProcedure -> com.github.iamniklas.liocorekotlin.procedures.JsonProcedure(
                _bundle
            )
            com.github.iamniklas.liocorekotlin.procedures.ProcedureType.Javascript -> com.github.iamniklas.liocorekotlin.procedures.JavascriptProcedure(
                _bundle
            )
            else -> null
        }
    }
}