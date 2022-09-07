import com.github.iamniklas.liocore.config.ProgramConfiguration;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurationTest {

    @Test
    public void testConfig() {
        ProgramConfiguration.configuration = ProgramConfiguration.readConfigFromFile();

        assertEquals("devicename", ProgramConfiguration.configuration.deviceName);

        assertEquals(300, ProgramConfiguration.configuration.ledCount);
        assertEquals(18, ProgramConfiguration.configuration.gpioPin);
        assertEquals(800000, ProgramConfiguration.configuration.frequency);
        assertEquals(10, ProgramConfiguration.configuration.dma);
        assertEquals(255, ProgramConfiguration.configuration.brightness);
        assertEquals(18, ProgramConfiguration.configuration.pwmChannel);
        assertFalse(ProgramConfiguration.configuration.invert);
        assertTrue(ProgramConfiguration.configuration.clearOnExit);
        assertEquals(16, ProgramConfiguration.configuration.frametime);
    }
}
