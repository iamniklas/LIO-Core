package com.github.iamniklas.liocore.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProgramConfiguration {
    //MQTT Configuration
    public final String mqttUser;
    public final String mqttPassword;
    public final String mqttBrokerAddress;
    public final String mqttDeviceName;
    public final int mqttConnectionTimeout;
    public final boolean mqttAutomaticReconnect;
    public final boolean mqttCleanSession;

    //LED Configuration
    public final int ledCount;
    public final int gpioPin;
    public final int frequency;
    public final int dma;
    public final int brightness;
    public final int pwmChannel;
    public final boolean invert;
    public final boolean clearOnExit;
    public final int frametime;

    public ProgramConfiguration(String _user,
                                String _psw,
                                String _brokerAddress) {
        mqttUser = _user;
        mqttPassword = _psw;
        mqttBrokerAddress = _brokerAddress;
        mqttDeviceName = null;
        mqttConnectionTimeout = 5;
        mqttAutomaticReconnect = true;
        mqttCleanSession = true;

        ledCount = 0;
        gpioPin = 0;
        frequency = 0;
        dma = 0;
        brightness = 0;
        pwmChannel = 0;
        invert = false;
        clearOnExit = false;
        frametime = 0;
    }

    public ProgramConfiguration(String _user,
                                String _psw,
                                String _brokerAddress,
                                int _connectionTimeout) {
        mqttUser = _user;
        mqttPassword = _psw;
        mqttBrokerAddress = _brokerAddress;
        mqttDeviceName = null;
        mqttConnectionTimeout = _connectionTimeout;
        mqttAutomaticReconnect = true;
        mqttCleanSession = true;

        ledCount = 0;
        gpioPin = 0;
        frequency = 0;
        dma = 0;
        brightness = 0;
        pwmChannel = 0;
        invert = false;
        clearOnExit = false;
        frametime = 0;
    }

    public ProgramConfiguration(String _user,
                                String _psw,
                                String _brokerAddress,
                                String _deviceName,
                                int _connectionTimeout,
                                boolean _automaticReconnect,
                                boolean _cleanSession,
                                int _ledCount,
                                int _gpioPin,
                                int _frequency,
                                int _dma,
                                int _brightness,
                                int _pwmChannel,
                                boolean _invert,
                                boolean _clearOnExit,
                                int _frametime) {
        mqttUser = _user;
        mqttPassword = _psw;
        mqttBrokerAddress = _brokerAddress;
        mqttDeviceName = _deviceName;
        mqttConnectionTimeout = _connectionTimeout;
        mqttAutomaticReconnect = _automaticReconnect;
        mqttCleanSession = _cleanSession;

        ledCount = _ledCount;
        gpioPin = _gpioPin;
        frequency = _frequency;
        dma = _dma;
        brightness = _brightness;
        pwmChannel = _pwmChannel;
        invert = _invert;
        clearOnExit = _clearOnExit;
        frametime = _frametime;
    }

    public static ProgramConfiguration configuration;

    public static ProgramConfiguration readConfigFromFile() {
        Properties prop = new Properties();
        String fileName = "config.txt";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("Configuration file has not been found. Using default configuration.");
        } catch (IOException ex) {
            System.out.println("Configuration file is corrupted or not readable. Using default configuration.");
        }

        return new ProgramConfiguration(
                prop.getProperty("mqtt.user", "lio"),
                prop.getProperty("mqtt.password", null),
                prop.getProperty("mqtt.brokeraddress", null),
                prop.getProperty("mqtt.devicename", null),
                Integer.parseInt(prop.getProperty("mqtt.connectiontimeout", "10")),
                Boolean.parseBoolean(prop.getProperty("mqtt.automaticreconnect", "true")),
                Boolean.parseBoolean(prop.getProperty("mqtt.cleansession", "true")),

                Integer.parseInt(prop.getProperty("led.ledcount", null)),
                Integer.parseInt(prop.getProperty("led.gpiopin", "18")),
                Integer.parseInt(prop.getProperty("led.frequency", "800000")),
                Integer.parseInt(prop.getProperty("led.dma", "10")),
                Integer.parseInt(prop.getProperty("led.brightness", "255")),
                Integer.parseInt(prop.getProperty("led.pwmChannel", "18")),
                Boolean.parseBoolean(prop.getProperty("led.invert", "false")),
                Boolean.parseBoolean(prop.getProperty("led.clearonexit", "true")),
                Integer.parseInt(prop.getProperty("led.frametime", "16"))
        );
    }
}
