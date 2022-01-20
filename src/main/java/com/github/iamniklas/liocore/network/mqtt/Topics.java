package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.ProgramConfiguration;

public class Topics {
    public final static String UPDATE = "led/update/" + ProgramConfiguration.instance.mqttDeviceName;
    public final static String UPDATE_ALL = "led/update/all";

    public final static String VARIABLE_UPDATE = "led/update/variable/" + ProgramConfiguration.instance.mqttDeviceName;
    public final static String VARIABLE_UPDATE_ALL = "led/update/variable/all";
}
