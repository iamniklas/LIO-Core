import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.network.mqtt.Topics;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigAndInfoTest {

    @Test
    public void testConfig() throws MqttException {
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
    }
}
