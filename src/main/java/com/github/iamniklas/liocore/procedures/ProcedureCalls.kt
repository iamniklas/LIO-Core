package com.github.iamniklas.liocore.procedures

interface ProcedureCalls {
    fun onProcedureStart(_procedure: Procedure?)
    fun onProcedureQueued()
    fun onProcedureFinish()
}