package com.github.iamniklas.liocorekotlin.procedures

interface ProcedureCalls {
    fun onProcedureStart(_procedure: com.github.iamniklas.liocorekotlin.procedures.Procedure?)
    fun onProcedureQueued()
    fun onProcedureFinish()
}