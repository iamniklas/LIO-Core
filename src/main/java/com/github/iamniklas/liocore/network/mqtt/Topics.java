package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.config.ProgramConfiguration;

public class Topics {
    public final static String UPDATE_LISTEN = "/led/update/" + ProgramConfiguration.configuration.deviceName;
    public final static String UPDATE_PUBLISH = "/led/update";
    public final static String UPDATE_ALL_LISTEN_PUBLISH = "/led/update/all";
    
    public final static String VARIABLE_UPDATE_LISTEN = "/led/update/variable/" + ProgramConfiguration.configuration.deviceName;
    public final static String VARIABLE_UPDATE_PUBLISH = "/led/update/variable";
    public final static String VARIABLE_UPDATE_ALL_LISTEN_PUBLISH = "/led/update/variable/all";
}
