package com.github.iamniklas.liocore.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProgramConfiguration {
    //Device Configuration
    public final String deviceName; //former mqttDeviceName

    //MQTT Configuration
    public final String mqttUser;
    public final String mqttPassword;
    public final String mqttBrokerAddress;
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

    //Android Specific Configuration
    public final boolean useSpotifyIntegration;

    public ProgramConfiguration(String _user,
                                String _psw,
                                String _brokerAddress) {
        mqttUser = _user;
        mqttPassword = _psw;
        mqttBrokerAddress = _brokerAddress;
        deviceName = null;
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

        useSpotifyIntegration = false;
    }

    public ProgramConfiguration(String _user,
                                String _psw,
                                String _brokerAddress,
                                int _connectionTimeout,
                                boolean _useSpotifyIntegration) {
        mqttUser = _user;
        mqttPassword = _psw;
        mqttBrokerAddress = _brokerAddress;
        deviceName = null;
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

        useSpotifyIntegration = _useSpotifyIntegration;
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
        deviceName = _deviceName;
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

        useSpotifyIntegration = false;
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
                getConfigurationField(prop, "mqtt.user"),
                getConfigurationField(prop, "mqtt.password"),
                getConfigurationField(prop, "mqtt.brokeraddress"),

                getConfigurationField(prop, "device.devicename"),

                Integer.parseInt(getConfigurationField(prop, "mqtt.connectiontimeout")),
                Boolean.parseBoolean(getConfigurationField(prop, "mqtt.automaticreconnect")),
                Boolean.parseBoolean(getConfigurationField(prop, "mqtt.cleansession")),

                Integer.parseInt(getConfigurationField(prop, "led.ledcount")),
                Integer.parseInt(getConfigurationField(prop, "led.gpiopin")),
                Integer.parseInt(getConfigurationField(prop, "led.frequency")),
                Integer.parseInt(getConfigurationField(prop, "led.dma")),
                Integer.parseInt(getConfigurationField(prop, "led.brightness")),
                Integer.parseInt(getConfigurationField(prop, "led.pwmChannel")),
                Boolean.parseBoolean(getConfigurationField(prop, "led.invert")),
                Boolean.parseBoolean(getConfigurationField(prop, "led.clearonexit")),
                Integer.parseInt(getConfigurationField(prop, "led.frametime"))
        );
    }

    private static String getConfigurationField(Properties _properties, String _propertyKey) {
        String propertyValue = _properties.getProperty(_propertyKey, null);

        if(propertyValue == null) {
            System.err.println("WARNING: Property Field Value of " + _propertyKey + " is null and so not set in the program configuration file");
            System.err.println("The program might not run as expected");
            System.err.println();
        }

        return propertyValue;
    }
}
