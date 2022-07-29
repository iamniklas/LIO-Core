package com.github.iamniklas.liocore.network.javalin;

import com.github.iamniklas.liocore.network.javalin.models.JavalinScanResult;
import org.junit.jupiter.api.Assertions;

import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class JavalinScan {
    public JavalinScanResult scanForDevices() {
        String scanClientIp;
        String networkScanRange;
        int foundDevices = 0;

        long measureStart;
        long measureFinish;

        ArrayList<String> deviceIps = new ArrayList<String>();

        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            scanClientIp = socket.getLocalAddress().getHostAddress();
            int lastIndexOf = scanClientIp.lastIndexOf('.');
            networkScanRange = scanClientIp.substring(0, lastIndexOf+1);

            measureStart = System.currentTimeMillis();
            for (int i = 1; i < 256; i++) {
                String ipToTest = networkScanRange + i;
                if(deviceWithIp(ipToTest)) {
                    deviceIps.add(ipToTest);
                    foundDevices++;
                }
            }
            measureFinish = System.currentTimeMillis();

            return new JavalinScanResult(scanClientIp, deviceIps.toArray(new String[0]), networkScanRange, measureStart, measureFinish, measureFinish - measureStart);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean deviceWithIp(String ip) {
        URL url = null;
        try {
            url = new URL("http://" + ip + ":5700/led/echo");
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(75);
            return con.getResponseCode() == 418;
        }
        catch (EOFException eof) {
            System.err.println("Device with ip " + ip +" found, but error reading stream");
            return false;
        }
        catch (IOException e) {
            //System.err.println("No Device found with ip " + ip);
            return false;
        }
    }
}
