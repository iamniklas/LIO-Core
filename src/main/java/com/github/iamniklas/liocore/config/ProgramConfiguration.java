package com.github.iamniklas.liocore.config;

import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProgramConfiguration {
    //Device Configuration
    public final String deviceName;

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
    public final LEDUpdateModel startingProcedure;

    //Android Specific Configuration
    public final boolean useSpotifyIntegration;

    public ProgramConfiguration(boolean _useSpotifyIntegration) {
        deviceName = null;

        ledCount = 0;
        gpioPin = 0;
        frequency = 0;
        dma = 0;
        brightness = 0;
        pwmChannel = 0;
        invert = false;
        clearOnExit = false;
        frametime = 0;
        startingProcedure = null;

        useSpotifyIntegration = _useSpotifyIntegration;
    }

    public ProgramConfiguration(String _deviceName,
                                int _ledCount,
                                int _gpioPin,
                                int _frequency,
                                int _dma,
                                int _brightness,
                                int _pwmChannel,
                                boolean _invert,
                                boolean _clearOnExit,
                                int _frametime,
                                LEDUpdateModel _startingProcedure) {
        deviceName = _deviceName;

        ledCount = _ledCount;
        gpioPin = _gpioPin;
        frequency = _frequency;
        dma = _dma;
        brightness = _brightness;
        pwmChannel = _pwmChannel;
        invert = _invert;
        clearOnExit = _clearOnExit;
        frametime = _frametime;
        startingProcedure = _startingProcedure;

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
                getConfigurationField(prop, "device.devicename"),

                Integer.parseInt(getConfigurationField(prop, "led.ledcount")),
                Integer.parseInt(getConfigurationField(prop, "led.gpiopin")),
                Integer.parseInt(getConfigurationField(prop, "led.frequency")),
                Integer.parseInt(getConfigurationField(prop, "led.dma")),
                Integer.parseInt(getConfigurationField(prop, "led.brightness")),
                Integer.parseInt(getConfigurationField(prop, "led.pwmchannel")),
                Boolean.parseBoolean(getConfigurationField(prop, "led.invert")),
                Boolean.parseBoolean(getConfigurationField(prop, "led.clearonexit")),
                Integer.parseInt(getConfigurationField(prop, "led.frametime")),
                new Gson().fromJson(getConfigurationField(prop, "led.startingprocedure"), LEDUpdateModel.class)
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
