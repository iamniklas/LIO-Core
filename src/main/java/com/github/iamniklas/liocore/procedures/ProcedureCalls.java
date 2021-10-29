package com.github.iamniklas.liocore.procedures;

public interface ProcedureCalls {
    void onProcedureStart(Procedure _procedure);
    void onProcedureQueued();
    void onProcedureFinish();
}
