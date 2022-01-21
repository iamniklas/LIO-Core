package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.config.ProgramConfiguration;

public class Topics {
    public final static String UPDATE = "led/update/" + ProgramConfiguration.configuration.mqttDeviceName;
    public final static String UPDATE_ALL = "led/update/all";

    public final static String VARIABLE_UPDATE = "led/update/variable/" + ProgramConfiguration.configuration.mqttDeviceName;
    public final static String VARIABLE_UPDATE_ALL = "led/update/variable/all";
}
