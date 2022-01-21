import com.github.iamniklas.liocore.config.ProgramConfiguration;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigAndInfoTest {

    @Test
    public void testConfig() {
        ProgramConfiguration.configuration = ProgramConfiguration.readConfigFromFile();

        assertEquals("lio", ProgramConfiguration.configuration.mqttUser);
        assertEquals("8kNhtmUG6kmUm3djdEE7MXmvAg4662", ProgramConfiguration.configuration.mqttPassword);
        assertEquals("192.168.178.10", ProgramConfiguration.configuration.mqttBrokerAddress);
        assertEquals("devicename", ProgramConfiguration.configuration.mqttDeviceName);
    }
}
