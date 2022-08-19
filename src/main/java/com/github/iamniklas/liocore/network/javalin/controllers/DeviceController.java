package com.github.iamniklas.liocore.network.javalin.controllers;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.google.gson.Gson;
import io.javalin.Javalin;

public class DeviceController extends ControllerBase {
    ProgramConfiguration programConfiguration;

    public DeviceController(Javalin _app, ProgramConfiguration _programConfiguration) {
        programConfiguration = _programConfiguration;

        _app.get("/device/info", ctx -> {
            ctx.result(new Gson().toJson(ProgramConfiguration.configuration));
        });

        _app.get("device/name", ctx -> {
           ctx.result(ProgramConfiguration.configuration.mqttDeviceName);
        });

        _app.get("/device/info/{field}", ctx -> {
           ctx.res.setStatus(501);
        });

        _app.get("device/echo", ctx -> {
            ctx.res.setStatus(418);
        });
    }
}
