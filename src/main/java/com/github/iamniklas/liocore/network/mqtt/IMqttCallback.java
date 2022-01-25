package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.network.LEDUpdateModel;

public interface IMqttCallback {
    void onLEDUpdateModelReceive(LEDUpdateModel _updateModel);
    void onLEDUpdateModelReceiveAll(LEDUpdateModel _updateModel);
    void onLEDValueUpdateModelReceive(LEDUpdateModel _valueUpdateModel);
    void onLEDValueUpdateModelReceiveAll(LEDUpdateModel _valueUpdateModel);
}