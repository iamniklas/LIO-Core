package com.github.iamniklas.liocore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProgramConfiguration {
    public String mqttUser = "lio";
    public String mqttPassword = "8kNhtmUG6kmUm3djdEE7MXmvAg4662";
    public String mqttBrokerAddress = "192.168.178.50";
    public String mqttDeviceName = "devicename";

    public static final ProgramConfiguration instance = readConfig();

    public static ProgramConfiguration readConfig() {
        ProgramConfiguration config = new ProgramConfiguration();
        Properties prop = new Properties();
        String fileName = "config.txt";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

        config.mqttUser = (prop.getProperty("mqtt.user"));
        config.mqttPassword = (prop.getProperty("mqtt.password"));
        config.mqttBrokerAddress = (prop.getProperty("mqtt.brokeraddress"));
        config.mqttDeviceName = prop.getProperty("mqtt.devicename");

        return config;
    }
}
