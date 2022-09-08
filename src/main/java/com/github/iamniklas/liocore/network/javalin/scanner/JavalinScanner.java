package com.github.iamniklas.liocore.network.javalin.scanner;

import com.github.iamniklas.liocore.network.javalin.models.JavalinScanResult;

import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class JavalinScanner {
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
            for (int i = 1; i < 256; i++) {
                String ipToTest = networkScanRange + i;
                String deviceWithIp = deviceWithIp(ipToTest);
                if(deviceWithIp != null) {
                    deviceNames.add(deviceWithIp);
                    deviceIps.add(ipToTest);
                    foundDevices++;
                }
            }
            measureFinish = System.currentTimeMillis();

            return new JavalinScanResult(scanClientIp, deviceNames.toArray(new String[0]), deviceIps.toArray(new String[0]), networkScanRange, measureStart, measureFinish, measureFinish - measureStart);
        } catch (SocketException | UnknownHostException e) {
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
}
