package com.github.iamniklas.liocore.network;

public interface IMqttCallback {
    void onLEDUpdateModelReceive(LEDUpdateModel _updateModel);
    void onLEDValueUpdateModelReceive(LEDValueUpdateModel _valueUpdateModel);
}