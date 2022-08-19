package com.github.iamniklas.liocore.network.javalin.models;

public class JavalinScanResult {
    public final String networkSSID;
    public String scanClientIp;
    public final String[] detectedIps;
    public final String scanRange;
    public final long scanStart;
    public final long scanFinish;
    public final long scanDuration;

    public JavalinScanResult(String _scanClientIp, String[] _detIps, String _scanRange, long _scanStart, long _scanFinish, long _scanDuration) {
        networkSSID = null;
        scanClientIp = _scanClientIp;
        detectedIps = _detIps;
        scanRange = _scanRange;
        scanStart = _scanStart;
        scanFinish = _scanFinish;
        scanDuration = _scanDuration;
    }

    public JavalinScanResult(String _ssid, JavalinScanResult _result) {
        networkSSID = _ssid;
        scanClientIp = _result.scanClientIp;
        detectedIps = _result.detectedIps;
        scanRange = _result.scanRange;
        scanStart = _result.scanStart;
        scanFinish = _result.scanFinish;
        scanDuration = _result.scanDuration;
    }
}
