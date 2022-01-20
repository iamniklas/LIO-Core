import com.github.iamniklas.liocore.ProgramConfiguration;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void testConfig() {
        assertEquals("lio", ProgramConfiguration.instance.mqttUser);
        assertEquals("8kNhtmUG6kmUm3djdEE7MXmvAg4662", ProgramConfiguration.instance.mqttPassword);
        assertEquals("192.168.178.50", ProgramConfiguration.instance.mqttBrokerAddress);
        assertEquals("devicename", ProgramConfiguration.instance.mqttDeviceName);
    }
}
