import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.network.mqtt.Topics;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurationTest {

    @Test
    public void testConfig() {
        ProgramConfiguration.configuration = ProgramConfiguration.readConfigFromFile();

        assertEquals("lio", ProgramConfiguration.configuration.mqttUser);
        assertEquals("8kNhtmUG6kmUm3djdEE7MXmvAg4662", ProgramConfiguration.configuration.mqttPassword);
        assertEquals("tcp://192.168.178.10:1883", ProgramConfiguration.configuration.mqttBrokerAddress);
        assertEquals("devicename", ProgramConfiguration.configuration.mqttDeviceName);

        assertEquals("/led/update/devicename", Topics.UPDATE_LISTEN);
        assertEquals("/led/update", Topics.UPDATE_PUBLISH);
        assertEquals("/led/update/all", Topics.UPDATE_ALL_LISTEN_PUBLISH);

        assertEquals("/led/update/variable/devicename", Topics.VARIABLE_UPDATE_LISTEN);
        assertEquals("/led/update/variable", Topics.VARIABLE_UPDATE_PUBLISH);
        assertEquals("/led/update/variable/all", Topics.VARIABLE_UPDATE_ALL_LISTEN_PUBLISH);

        assertEquals(15, ProgramConfiguration.configuration.mqttConnectionTimeout);
        assertTrue(ProgramConfiguration.configuration.mqttAutomaticReconnect);
        assertTrue(ProgramConfiguration.configuration.mqttCleanSession);

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
