package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.network.LEDUpdateModel;

@Deprecated
public interface IMqttCallback {
    void onLEDUpdateModelReceive(LEDUpdateModel _updateModel, boolean _callForAllDevices);
    void onLEDValueUpdateModelReceive(LEDUpdateModel _valueUpdateModel, boolean _callForAllDevices);
}