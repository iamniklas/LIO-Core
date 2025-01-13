package com.github.iamniklas.liocore.network.javalin.controllers;

import com.github.iamniklas.colorspaces.ColorHSV;
import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.KelvinColor;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.javalin.models.SmartHomeModel;
import com.google.gson.Gson;
import io.javalin.Javalin;

public class SmartHomeController extends ControllerBase {

    SmartHomeModel smartHomeModel;

    LEDStripManager ledStripManager;

    public SmartHomeController(Javalin _app, LEDStripManager _ledStripManager, Gson gson) {
        ledStripManager = _ledStripManager;
        smartHomeModel = new SmartHomeModel(new ColorRGB(255, 255, 255), 100, true);

        _app.get("/smart", ctx -> ctx.result(gson.toJson(smartHomeModel)));

        _app.get("/smart/brightness", ctx -> ctx.result(gson.toJson(smartHomeModel.getBrightness())));
        _app.post("/smart/brightness", ctx -> smartHomeModel.setBrightness(Float.parseFloat(ctx.body())));

        _app.get("/smart/temperature_k", ctx -> { ctx.result(gson.toJson(getTemperatureKFromColorRGB(smartHomeModel.getColor()))); });
        _app.post("/smart/temperature_k", ctx -> smartHomeModel.setColor(KelvinColor.getRGBForKelvin(Integer.parseInt(ctx.body()))));

        _app.get("/smart/color/rgb", ctx -> ctx.result(gson.toJson(smartHomeModel.getColor())));
        _app.post("/smart/color/rgb", ctx -> smartHomeModel.setColor(gson.fromJson(ctx.body(), ColorRGB.class)));

        _app.get("/smart/color/hsv", ctx -> ctx.result(gson.toJson(smartHomeModel.getColor().toHSV())));
        _app.post("/smart/color/hsv", ctx -> smartHomeModel.setColor(gson.fromJson(ctx.body(), ColorHSV.class).toRGB()));

        _app.get("/smart/state", ctx -> ctx.result(gson.toJson(smartHomeModel.getPowerSwitch())));
        _app.post("/smart/state", ctx -> smartHomeModel.setPowerSwitch(Boolean.parseBoolean(ctx.body())));

        _app.after("/smart/*", ctx -> { if(ctx.method().equals("POST")) { updateLEDStrip(); } });
    }

    void updateLEDStrip() {
        ledStripManager.procContainer.removeAllCurrentProcedures();

        smartHomeModel.setBrightness(Math.max(0, Math.min(100, smartHomeModel.getBrightness())));

        smartHomeModel.setColor(new ColorRGB(
                Math.max(0, Math.min(255, smartHomeModel.getColor().getR())),
                Math.max(0, Math.min(255, smartHomeModel.getColor().getG())),
                Math.max(0, Math.min(255, smartHomeModel.getColor().getB()))
        ));

        if (smartHomeModel.getPowerSwitch()) {
            LIOColor adjustedColor = new LIOColor(
                    (int) (smartHomeModel.getColor().getR() * (smartHomeModel.getBrightness() / 100.0f)),
                    (int) (smartHomeModel.getColor().getG() * (smartHomeModel.getBrightness() / 100.0f)),
                    (int) (smartHomeModel.getColor().getB() * (smartHomeModel.getBrightness() / 100.0f))
            );
            ledStripManager.setAllPixels(adjustedColor);
        } else {
            ledStripManager.setAllPixels(new LIOColor(0, 0, 0));
        }
    }

    ColorRGB getRGBFromTemperatureK(float temperatureK) {
        float temp = temperatureK / 100.0f;

        float red = 0;
        float green = 0;
        float blue = 0;

        if(temp <= 66) {
            red = 255;
        } else {
            red = temp - 60;
            red = (float) (329.698727446 * Math.pow(red, -0.1332047592));
        }

        if(temp <= 66) {
            green = temp;
            green = (float) (99.4708025861 * Math.log(green) - 161.1195681661);
        } else {
            green = temp - 60;
            green = (float) (288.1221695283 * Math.pow(green, -0.0755148492));
        }

        if(temp >= 66) {
            blue = 255;
        } else if(temp <= 19) {
            blue = 0;
        } else {
            blue = temp - 10;
            blue = (float) (138.5177312231 * Math.log(blue) - 305.0447927307);
        }

        return new ColorRGB((int) red, (int) green, (int) blue);
    }
    float getTemperatureKFromColorRGB(ColorRGB colorRGB) {
        float red = colorRGB.getR();
        float green = colorRGB.getG();
        float blue = colorRGB.getB();

        float X = red * 0.4124564f + green * 0.3575761f + blue * 0.1804375f;
        float Y = red * 0.2126729f + green * 0.7151522f + blue * 0.0721750f;
        float Z = red * 0.0193339f + green * 0.1191920f + blue * 0.9503041f;

        float x = X / (X + Y + Z);
        float y = Y / (X + Y + Z);

        float n = (x - 0.3320f) / (0.1858f - y);
        float cct = (float) ((449.0f * Math.pow(n, 3)) + (3525.0f * Math.pow(n, 2)) + (6823.3f * n) + 5520.33f);

        return cct;
    }
}
