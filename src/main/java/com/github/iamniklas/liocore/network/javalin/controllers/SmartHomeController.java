package com.github.iamniklas.liocore.network.javalin.controllers;

import com.github.iamniklas.colorspaces.ColorHSV;
import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.javalin.models.SmartHomeModel;
import com.google.gson.Gson;
import io.javalin.Javalin;

public class SmartHomeController extends ControllerBase {

    SmartHomeModel smartHomeModel;

    LEDStripManager ledStripManager;

    public SmartHomeController(Javalin _app, LEDStripManager _ledStripManager, Gson gson) {
        ledStripManager = _ledStripManager;
        smartHomeModel = new SmartHomeModel(new ColorRGB(255, 255, 255), 255, true);

        _app.get("/smart", ctx -> ctx.result(gson.toJson(smartHomeModel)));

        _app.get("/smart/brightness", ctx -> ctx.result(gson.toJson(smartHomeModel.getBrightness())));
        _app.post("/smart/brightness", ctx -> smartHomeModel.setBrightness(Float.parseFloat(ctx.body())));

        _app.get("/smart/temperature_k", ctx -> { });
        _app.post("/smart/temperature_k", ctx -> { });

        _app.get("/smart/color/rgb", ctx -> ctx.result(gson.toJson(smartHomeModel.getColor())));
        _app.post("/smart/color/rgb", ctx -> smartHomeModel.setColor(gson.fromJson(ctx.body(), ColorRGB.class)));

        _app.get("/smart/color/hsv", ctx -> ctx.result(gson.toJson(smartHomeModel.getColor().toHSV())));
        _app.post("/smart/color/hsv", ctx -> smartHomeModel.setColor(gson.fromJson(ctx.body(), ColorHSV.class).toRGB()));

        _app.get("/smart/state", ctx -> ctx.result(gson.toJson(smartHomeModel.getPowerSwitch())));
        _app.post("/smart/state", ctx -> smartHomeModel.setPowerSwitch(Boolean.parseBoolean(ctx.body())));

        _app.after("/smart/*", ctx -> { if(ctx.method().equals("POST")) { updateLEDStrip(); } });
    }

    void updateLEDStrip() {
        if (smartHomeModel.getPowerSwitch()) {
            LIOColor adjustedColor = new LIOColor(
                    (int) (smartHomeModel.getColor().r * (smartHomeModel.getBrightness() / 100.0f)),
                    (int) (smartHomeModel.getColor().g * (smartHomeModel.getBrightness() / 100.0f)),
                    (int) (smartHomeModel.getColor().b * (smartHomeModel.getBrightness() / 100.0f))
            );
            ledStripManager.setAllPixels(adjustedColor);
        } else {
            ledStripManager.setAllPixels(new LIOColor(0, 0, 0));
        }
    }
}
