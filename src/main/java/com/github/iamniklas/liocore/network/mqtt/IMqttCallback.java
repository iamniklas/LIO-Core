package com.github.iamniklas.liocore.network.mqtt;

import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.network.LEDValueUpdateModel;

public interface IMqttCallback {
    void onLEDUpdateModelReceive(LEDUpdateModel _updateModel);
    void onLEDUpdateModelReceiveAll(LEDUpdateModel _updateModel);
    void onLEDValueUpdateModelReceive(LEDValueUpdateModel _valueUpdateModel);
    void onLEDValueUpdateModelReceiveAll(LEDValueUpdateModel _valueUpdateModel);
}