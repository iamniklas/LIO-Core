import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.network.javalin.JavalinHandler;
import com.github.iamniklas.liocore.network.javalin.JavalinScan;
import com.github.iamniklas.liocore.network.javalin.models.JavalinScanResult;
import io.javalin.Javalin;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class JavalinTest {

    JavalinHandler javalinHandler;

    @BeforeEach
    public void initJavalin() {
        LEDStripManager ledStripManager = new LEDStripManager(null, 300);
        ProgramConfiguration.configuration = new ProgramConfiguration(null, null, null, "Test Device", 0, false, false, 300, 18, 80000, 1, 255, 0, false, true, 25);
        javalinHandler = new JavalinHandler(ledStripManager, ProgramConfiguration.configuration);
    }

    @AfterEach
    public void closeConnection() {
        javalinHandler.close();
    }

    @Test
    public void testDeviceScanner() {
        System.out.println("Starting scan...");
        JavalinScanResult result = new JavalinScan().scanForDevices();
        System.out.println("Completed scan");
        System.out.println("Scan Time: " + (result.scanDuration / 1000) + "s" + (result.scanDuration % 1000) + "ms");
        System.out.println("Found " + result.detectedIps.length + " device(s)");
        System.out.println("Network Address: " + result.scanRange + "255");
        if(result.detectedIps.length > 0) {
            System.out.println("Device IPs: " + Arrays.toString(result.detectedIps));
        }

        Assertions.assertNull(result.networkSSID);
        System.out.println(result.scanClientIp);
        //Scan Client Ip: 192.168.178.71
        Assertions.assertTrue(result.detectedIps.length > 0);
        Assertions.assertEquals(result.scanRange, "192.168.178.");
        Assertions.assertEquals(result.scanDuration, result.scanFinish - result.scanStart);
    }
}
