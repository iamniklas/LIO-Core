package com.github.iamniklas.liocore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProgramConfiguration {
    public final String mqttUser;
    public final String mqttPassword;
    public final String mqttBrokerAddress;
    public final String mqttDeviceName;

    private ProgramConfiguration(String _user, String _psw, String _brokerAddress, String _deviceName) {
        mqttUser = _user;
        mqttPassword = _psw;
        mqttBrokerAddress = _brokerAddress;
        mqttDeviceName = _deviceName;
    }

    public static final ProgramConfiguration configuration = ProgramInfo.info.runningOnAndroid ? null : readConfigFromFile();

    private static ProgramConfiguration readConfigFromFile() {
        Properties prop = new Properties();
        String fileName = "config.txt";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

        return new ProgramConfiguration(
                prop.getProperty("mqtt.user"),
                prop.getProperty("mqtt.password"),
                prop.getProperty("mqtt.brokeraddress"),
                prop.getProperty("mqtt.devicename")
        );
    }
}
