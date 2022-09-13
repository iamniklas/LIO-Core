import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.javalin.JavalinHandler;
import com.github.iamniklas.liocore.network.javalin.scanner.JavalinProScanner;
import com.github.iamniklas.nettools.models.DeviceResult;
import com.github.iamniklas.nettools.models.TestResult;
import com.github.iamniklas.nettools.scanner.ScanResultCallback;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JavalinProScannerTests {
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
    }

    @AfterEach
    public void closeConnection() {
        javalinHandler.close();
    }

    @Test
    public void testJavalinProScanner() {
        JavalinProScanner javalinProScanner = new JavalinProScanner();
        TestResult result = javalinProScanner.scanForDevices(new ScanResultCallback() {
            @Override
            public void onSuccessResult(DeviceResult result) {
                System.out.println("Found Device With IP " + result.ip);
            }

            @Override
            public void onScanComplete(int progress, int maxValue) {
                if (progress == maxValue) {
                    System.out.println("Scan completed");
                }
            }

            @Override
            public void onErrorResult(Exception exception) {

            }
        });

        System.out.println("Scanning complete");
        //System.out.println("Scan Time: " + (result.scanDuration / 1000) + "s" + (result.scanDuration % 1000) + "ms");
        //System.out.println("Found " + result.detectedIps.length + " devices");
        //System.out.println(new Gson().toJson(result.detectedIps));
    }
}
