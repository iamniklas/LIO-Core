import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.javalin.JavalinHandler;
import com.github.iamniklas.liocore.network.javalin.scanner.JavalinScanner;
import com.github.iamniklas.liocore.network.javalin.models.JavalinScanResult;
import com.github.iamniklas.liocore.procedures.ProcedureFactory;
import com.github.iamniklas.liocore.procedures.ProcedureType;
import com.github.iamniklas.liocore.procedures.models.Direction;
import com.github.iamniklas.nettools.models.DeviceResult;
import com.github.iamniklas.nettools.models.TestResult;
import com.github.iamniklas.nettools.scanner.ScanResultCallback;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JavalinTests {

    JavalinHandler javalinHandler;

    LEDStripManager ledStripManager;

    @BeforeEach
    public void initJavalin() {
        ledStripManager = new LEDStripManager(new LEDRenderer() {
            @Override
            public void render(ArrayList<LIOColor> _colorData) {

            }
        }, 300);
        ProgramConfiguration.configuration = new ProgramConfiguration("test_config", 300, 18, 80000, 1, 255, 0, false, true, 25, null);
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
        JavalinScanner scanner = new JavalinScanner();
        TestResult result = scanner.scanForDevices(new ScanResultCallback() {
            @Override
            public void onSuccessResult(DeviceResult result) {
                System.out.println("Found Device With IP " + result.ip);
            }

            @Override
            public void onErrorResult(Exception exception) {

            }
        });
        System.out.println("Completed scan");
        //System.out.println("Scan Time: " + (result.scanDuration / 1000) + "s" + (result.scanDuration % 1000) + "ms");
        //System.out.println("Found " + result.detectedIps.length + " device(s)");
        //System.out.println("Network Address: " + result.scanRange + "255");
        if(result.deviceResults.length > 0) {
            System.out.println("Device IPs: " + Arrays.toString(result.deviceResults));
        }

        //assertNull(result.networkSSID);
        //System.out.println(result.scanClientIp);
        //Scan Client Ip: 192.168.178.71
        //assertTrue(result.detectedIps.length > 0);
        //assertEquals(result.scanRange, "192.168.178.");
        //assertEquals(result.scanDuration, result.scanFinish - result.scanStart);
    }


    /******************************************************************************************************************
     *****************************************TEST CASES FOR LED CONTROLLER********************************************
     ******************************************************************************************************************/

    // POST /led/procedure/
    @Test
    public void testPostNewProcedure() {
        resetLEDStatus();

        LEDUpdateModel ledUpdateModel = new LEDUpdateModel();

        LEDDataBundle ledDataBundle = new LEDDataBundle();
        ledDataBundle.speed = 3.0f;
        ledDataBundle.puModulo = 2;
        ledDataBundle.puModuloInvert = true;

        ProcedureType procedureType = ProcedureType.RainbowMono;

        ledUpdateModel.bundle = ledDataBundle;
        ledUpdateModel.procedure = procedureType;

        HttpResult request = executePostRequest("http://localhost:5700/led/procedure/", new Gson().toJson(ledUpdateModel));

        assertNotNull(request);
        assertEquals(ledUpdateModel.procedure, ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.procedure);
        assertEquals(ledUpdateModel.bundle.speed, ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle.speed);
        assertEquals(ledUpdateModel.bundle.puModulo, ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle.puModulo);
        assertEquals(ledUpdateModel.bundle.puModuloInvert, ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle.puModuloInvert);
    }

    // GET /led/procedure/
    @Test
    public void testGetActiveProcedure() {
        resetLEDStatus();
        HttpResult request = executeGetRequest("http://localhost:5700/led/procedure/");
        assertNotNull(request);
        assertEquals(
                new Gson().toJson(ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.procedure),
                request.message
        );
        System.out.println("Test 'Get Active Procedure' passed");
    }

    // GET /led/procedure/all
    @Test
    public void testGetActiveProcedureComplete() {
        resetLEDStatus();
        HttpResult request = executeGetRequest("http://localhost:5700/led/procedure/all/");
        assertNotNull(request);
        assertEquals(
                new Gson().toJson(ledStripManager.procContainer.getActiveProcedure().ledUpdateModel),
                request.message
        );
        System.out.println("Test 'Get Complete Active Procedure' passed");
    }

    // GET /led/variables/
    @Test
    public void testGetAllVariables() {
        resetLEDStatus();
        HttpResult request = executeGetRequest("http://localhost:5700/led/variables/");
        assertNotNull(request);
        assertEquals(
                new Gson().toJson(ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle),
                request.message
        );
        System.out.println("Test 'Get All Variables' passed");
    }

    // PUT /led/variables/all/
    @Test
    public void testUpdateVariablesOfActiveProcedure() {
        resetLEDStatus();
        LEDDataBundle ledDataBundle = new LEDDataBundle();
        ledDataBundle.speed = 2f;
        ledDataBundle.puModulo = 30;
        ledDataBundle.puModuloInvert = true;
        ledDataBundle.direction = Direction.Left;
        ledDataBundle.repetitions = 3.2f;

        HttpResult result = executePutRequest("http://localhost:5700/led/variables/", new Gson().toJson(ledDataBundle));
        assertNotNull(result);
        assertEquals(ledDataBundle.speed, ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle.speed);
        assertEquals(ledDataBundle.puModulo, ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle.puModulo);
        assertEquals(ledDataBundle.repetitions, ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle.repetitions);
    }


    /******************************************************************************************************************
     ****************************************TEST CASES FOR DEVICE CONTROLLER******************************************
     ******************************************************************************************************************/

    // GET /device/name
    @Test
    public void testGetDeviceName() {
        resetLEDStatus();
        HttpResult request = executeGetRequest("http://localhost:5700/device/name/");
        assertNotNull(request);
        assertEquals(
                ProgramConfiguration.configuration.deviceName,
                request.message
        );
        System.out.println("Test 'Get Device Name' passed");
    }

    // GET /device/info/
    @Test
    public void testGetCompleteDeviceConfiguration() {
        resetLEDStatus();
        HttpResult request = executeGetRequest("http://localhost:5700/device/info/");
        assertNotNull(request);
        assertEquals(
                new Gson().toJson(ProgramConfiguration.configuration),
                request.message
        );
        System.out.println("Test 'Get Complete Device Configuration' passed");
    }

    // GET /device/echo/
    @Test
    public void testGetEcho() {
        resetLEDStatus();
        HttpResult request = executeGetRequest("http://localhost:5700/device/echo/");
        assertNotNull(request);
        assertEquals(
                JavalinHandler.ECHO_SUCCESS_CODE,
                request.statusCode
        );
        System.out.println("Test 'Get Echo' passed");
    }


    /******************************************************************************************************************
     ****************************************HELPER METHODS FOR HTTP REQUESTS******************************************
     ******************************************************************************************************************/

    private HttpResult executeGetRequest(String _url) {
        try {
            URL url = new URL(_url);
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(150);

            StringBuilder content = new StringBuilder();
            if(con.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
            }

            return new HttpResult(con.getResponseCode(), content.toString());
        } catch (IOException eof) {
            eof.printStackTrace();
            return null;
        }
    }

    private HttpResult executePostRequest(String _url, String _body) {
        URL url = null;
        int statusCode = 0;
        StringBuilder content = new StringBuilder();
        try {
            url = new URL(_url);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
            osw.write(_body);
            osw.flush();
            osw.close();

            statusCode = http.getResponseCode();

            if(http.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
            }
        } catch (IOException e) {
            statusCode = -1;
            e.printStackTrace();
        }

        return new HttpResult(statusCode, content.toString());
    }

    private HttpResult executePutRequest(String _url, String _body) {
        URL url = null;
        int statusCode = 0;
        StringBuilder content = new StringBuilder();
        try {
            url = new URL(_url);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("PUT");
            http.setDoOutput(true);
            OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
            osw.write(_body);
            osw.flush();
            osw.close();

            statusCode = http.getResponseCode();

            if(http.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
            }
        } catch (IOException e) {
            statusCode = -1;
            e.printStackTrace();
        }

        return new HttpResult(statusCode, content.toString());
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

        public HttpResult() { }

        public HttpResult(int statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }
    }
}