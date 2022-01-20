package com.github.iamniklas.liocore;

public class ProgramInfo {
    public static ProgramInfo info = fetchInfo();

    public final boolean runningOnAndroid;
    public final String vendorUrl;

    private ProgramInfo(boolean _runningOnAndroid, String _vendorUrl) {
        runningOnAndroid = _runningOnAndroid;
        vendorUrl = _vendorUrl;
    }

    private static ProgramInfo fetchInfo() {
        boolean runningOnAndroid = System.getProperties().get("java.vendor.url").equals("http://www.android.com");
        String vendorUrl = (String) System.getProperties().get("java.vendor.url");
        return new ProgramInfo(runningOnAndroid, vendorUrl);
    }
}
