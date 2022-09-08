package com.github.iamniklas.liocore.network.javalin.scanner;

import com.github.iamniklas.liocore.network.javalin.models.JavalinScanResult;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class JavalinProScanner {
    public JavalinScanResult scanForDevices() {
        String scanNetworkName;
        String scanClientIp;
        String networkScanRange;
        int foundDevices = 0;

        long measureStart;
        long measureFinish;

        ArrayList<String> deviceIps = new ArrayList<String>();
        ArrayList<String> deviceNames = new ArrayList<String>();

        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            scanClientIp = socket.getLocalAddress().getHostAddress();

            int lastIndexOf = scanClientIp.lastIndexOf('.');
            networkScanRange = scanClientIp.substring(0, lastIndexOf+1);

            measureStart = System.currentTimeMillis();
            ArrayList<RangeScanThread> scannerThreads = new ArrayList<>();
            int threadCount = 8;
            for (int i = 0; i < threadCount; i++) {
                RangeScanThread scanThread = new RangeScanThread(
                        i,
                        1 + (threadCount * (i * (threadCount / 2))),
                        Math.min((threadCount * (i + 1) * (threadCount / 2)), 255),
                        networkScanRange,
                        deviceNames,
                        deviceIps);
                scannerThreads.add(scanThread);
                scanThread.start();
            }
            for (int i = 0; i < threadCount; i++) {
                scannerThreads.get(i).join();
            }
            measureFinish = System.currentTimeMillis();

            return new JavalinScanResult(scanClientIp, deviceNames.toArray(new String[0]), deviceIps.toArray(new String[0]), networkScanRange, measureStart, measureFinish, measureFinish - measureStart);
        } catch (SocketException | UnknownHostException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String deviceWithIp(String ip) {
        URL url = null;
        try {
            url = new URL("http://" + ip + ":5700/device/name");
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(150);
            return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        }
        catch (EOFException eof) {
            System.err.println("Device with ip " + ip +" found, but error reading stream");
            eof.printStackTrace();
            return null;
        }
        catch (IOException e) {
            //System.err.println("No Device found with ip " + ip);
            return null;
        }
    }

    private class RangeScanThread extends Thread {

        private final int threadId;
        private final int from;
        private final int to;
        private final String networkScanRange;
        private final ArrayList<String> deviceNames;
        private final ArrayList<String> deviceIps;

        public RangeScanThread(int _threadId, int _from, int _to, String _networkScanRange, ArrayList<String> _deviceNames, ArrayList<String> _deviceIps) {
            from = _from;
            to = _to;
            networkScanRange = _networkScanRange;
            deviceNames = _deviceNames;
            deviceIps = _deviceIps;
            threadId = _threadId;
        }

        @Override
        public void run() {
            super.run();

            for (int i = from; i < to; i++) {
                String ipToTest = networkScanRange + i;
                String deviceWithIp = deviceWithIp(ipToTest);
                if(deviceWithIp != null) {
                    deviceNames.add(deviceWithIp);
                    deviceIps.add(ipToTest);
                }
            }
        }
    }
}
