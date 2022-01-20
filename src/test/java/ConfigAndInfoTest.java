import com.github.iamniklas.liocore.ProgramConfiguration;
import com.github.iamniklas.liocore.ProgramInfo;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigAndInfoTest {

    @Test
    public void testConfig() {
        assertEquals("lio", ProgramConfiguration.configuration.mqttUser);
        assertEquals("8kNhtmUG6kmUm3djdEE7MXmvAg4662", ProgramConfiguration.configuration.mqttPassword);
        assertEquals("192.168.178.10", ProgramConfiguration.configuration.mqttBrokerAddress);
        assertEquals("devicename", ProgramConfiguration.configuration.mqttDeviceName);
    }

    @Test
    public void testInfo() {
        assertFalse(ProgramInfo.info.runningOnAndroid);
        System.out.println(ProgramInfo.info.vendorUrl);
    }
}
