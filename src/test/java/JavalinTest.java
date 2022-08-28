import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.javalin.JavalinHandler;
import com.github.iamniklas.liocore.network.javalin.JavalinScan;
import com.github.iamniklas.liocore.network.javalin.models.JavalinScanResult;
import com.github.iamniklas.liocore.procedures.ProcedureFactory;
import com.github.iamniklas.liocore.procedures.ProcedureType;
import com.github.iamniklas.liocore.procedures.models.Direction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class JavalinTest {

    JavalinHandler javalinHandler;

    LEDStripManager ledStripManager;

    @BeforeEach
    public void initJavalin() {
        ledStripManager = new LEDStripManager(new LEDRenderer() {
            @Override
            public void render(ArrayList<LIOColor> _colorData) {

            }
        }, 300);
        ProgramConfiguration.configuration = new ProgramConfiguration(null, null, null, "Test Device", 0, false, false, 300, 18, 80000, 1, 255, 0, false, true, 25);
        javalinHandler = new JavalinHandler(ledStripManager, ProgramConfiguration.configuration);
        resetLEDStatus();
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


    /******************************************************************************************************************/
    /*****************************************TEST CASES FOR LED CONTROLLER********************************************/
    /******************************************************************************************************************/

    // POST /led/procedure/
    @Test
    public void testPostNewProcedure() {
        resetLEDStatus();
        throw new NotImplementedException();
    }

    // GET /led/procedure/
    @Test
    public void testGetActiveProcedure() {
        resetLEDStatus();
        Assertions.assertEquals(
                "Rainbow",
                executeGetRequest("localhost:5700/led/procedure/").message
        );
    }

    // GET /led/procedure/all
    @Test
    public void testGetActiveProcedureComplete() {
        resetLEDStatus();
        Assertions.assertEquals(
                "{\"procedure\": \"Rainbow\", \"bundle\":{\"speed\": 1, \"pu_modulo\": 5, \"pu_modulo_invert\": true, \"direction\": \"Left\", \"repetitions\": 1.6}}",
                executeGetRequest("http://localhost:5700/led/procedure/all/").message
        );
    }

    // GET /led/variables/
    @Test
    public void testGetAllVariables() {
        resetLEDStatus();
        Assertions.assertEquals(
                "{\"speed\": 1, \"pu_modulo\": 5, \"pu_modulo_invert\": true, \"direction\": \"Left\", \"repetitions\": 1.6}",
                executeGetRequest("http://localhost:5700/led/variables/").message
        );
    }

    // GET /led/procedure/variables/{variable_name}/
    @Test
    public void testGetSingleVariable() {
        resetLEDStatus();
        throw new NotImplementedException();
    }

    // PUT /led/variables/all/
    @Test
    public void testUpdateVariablesOfActiveProcedure() {
        resetLEDStatus();
        throw new NotImplementedException();
    }


    /******************************************************************************************************************/
    /****************************************TEST CASES FOR DEVICE CONTROLLER******************************************/
    /******************************************************************************************************************/

    // GET /device/name
    @Test
    public void testGetDeviceName() {
        resetLEDStatus();
        HttpResult request = executeGetRequest("http://localhost:5700/device/name/");
        Assertions.assertNotNull(request);
        Assertions.assertEquals(
                ProgramConfiguration.configuration.deviceName,
                request.message
        );
    }

    // GET /device/info/{field_name}/
    @Test
    public void testGetSingleConfigurationVariable() {
        resetLEDStatus();
        throw new NotImplementedException();
    }

    // GET /device/info/
    @Test
    public void testGetCompleteDeviceConfiguration() {
        resetLEDStatus();
        throw new NotImplementedException();
    }

    // GET /device/echo/
    @Test
    public void testGetEcho() {
        resetLEDStatus();
        HttpResult request = executeGetRequest("http://localhost:5700/device/echo/");
        Assertions.assertNotNull(request);
        Assertions.assertEquals(
                JavalinHandler.ECHO_SUCCESS_CODE,
                request.statusCode
        );
    }


    /******************************************************************************************************************/
    /****************************************HELPER METHODS FOR HTTP REQUESTS******************************************/
    /******************************************************************************************************************/

    private HttpResult executeGetRequest(String _url) {
        try {
            URL url = new URL(_url);
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(150);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            return new HttpResult(con.getResponseCode(), content.toString());
        } catch (IOException eof) {
            return null;
        }
    }

    private HttpResult executePostRequest() {
        throw new NotImplementedException();
    }

    private HttpResult executePutRequest() {
        throw new NotImplementedException();
    }


    private void resetLEDStatus() {
        LEDDataBundle ledDataBundle = new LEDDataBundle();
        ledDataBundle.speed = 1.0f;
        ledDataBundle.puModulo = 5;
        ledDataBundle.puModuloInvert = true;
        ledDataBundle.direction = Direction.Left;
        ledDataBundle.repetitions = 1.6f;
        ledDataBundle.ledStrip = ledStripManager;
        ledDataBundle.procedureCalls = ledStripManager;
        LEDUpdateModel ledUpdateModel = new LEDUpdateModel(ProcedureType.Rainbow, ledDataBundle);
        ledStripManager.procContainer.queueProcedure(ProcedureFactory.getProcedure(ledUpdateModel));
    }

    private class HttpResult {
        public int statusCode;
        public String message;

        public HttpResult() {

        }

        public HttpResult(int statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }
    }
}