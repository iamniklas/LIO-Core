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
        javalinHandler = new JavalinHandler(JavalinHandler.JAVALIN_PORT);
    }

    @AfterEach
    public void closeConnection() {
        javalinHandler.close();
    }

    @Test
    public void testJavalinFeedback() {
        URL url = null;
        try {
            url = new URL("http://localhost:5700/led/update");
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeUTF("Hey");
            Assertions.assertEquals(200, con.getResponseCode());
            Assertions.assertEquals("Hey", new DataInputStream((InputStream) con.getContent()).readUTF());

            url = new URL("http://localhost:5700/led/update?fail=1");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            Assertions.assertEquals(500, con.getResponseCode());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeviceScanner() {
        JavalinScanResult result = new JavalinScan().scanForDevices();
        System.out.println("Scan Time: " + (result.scanDuration / 1000) + "s" + (result.scanDuration % 1000) + "ms");
        System.out.println("Found " + result.detectedIps.length + " device(s)");
        if(result.detectedIps.length > 0) {
            System.out.println("Device IPs: " + Arrays.toString(result.detectedIps));
        }
        Assertions.assertTrue(result.detectedIps.length > 0);
    }
}
