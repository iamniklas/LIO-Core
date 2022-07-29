package com.github.iamniklas.liocore.network.javalin.models;

public class JavalinScanResult {
    public String scanClientIp;
    public final String[] detectedIps;
    public final String scanRange;
    public final long scanStart;
    public final long scanFinish;
    public final long scanDuration;

    public JavalinScanResult(String _scanClientIp, String[] _detIps, String _scanRange, long _scanStart, long _scanFinish, long _scanDuration) {
        scanClientIp = _scanClientIp;
        detectedIps = _detIps;
        scanRange = _scanRange;
        scanStart = _scanStart;
        scanFinish = _scanFinish;
        scanDuration = _scanDuration;
    }
}
