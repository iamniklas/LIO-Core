package com.github.iamniklas.liocore.network.javalin.scanner;

import com.github.iamniklas.liocore.network.javalin.JavalinHandler;
import com.github.iamniklas.liocore.network.javalin.models.JavalinScanResult;
import com.github.iamniklas.nettools.models.RequestMethod;
import com.github.iamniklas.nettools.models.TestResult;
import com.github.iamniklas.nettools.scanner.AcceleratedNetworkScanner;
import com.github.iamniklas.nettools.scanner.NetworkScanner;
import com.github.iamniklas.nettools.scanner.ScanResultCallback;
import com.github.iamniklas.nettools.scanner.Scanner;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class JavalinProScanner {
    public TestResult scanForDevices(ScanResultCallback _callback) {
        try(final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String scanClientIp = socket.getLocalAddress().getHostAddress();

            int lastIndexOf = scanClientIp.lastIndexOf('.');
            String networkScanRange = scanClientIp.substring(0, lastIndexOf);

            AcceleratedNetworkScanner scanner = new AcceleratedNetworkScanner(
                    networkScanRange,
                    JavalinHandler.JAVALIN_PORT,
                    "device/name",
                    RequestMethod.GET,
                    Scanner.DEFAULT_TIMEOUT,
                    _callback
            );

            return scanner.scanFor(t ->
                    t.resultCode == HttpURLConnection.HTTP_OK &&
                            t.body != null
            );
        }
        catch (Exception e) {
            return null;
        }
    }
}
