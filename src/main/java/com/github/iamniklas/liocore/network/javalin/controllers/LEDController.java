package com.github.iamniklas.liocore.network.javalin.controllers;

import com.github.iamniklas.liocore.led.LEDStripManager;
import com.google.gson.Gson;
import io.javalin.Javalin;

public class LEDController extends ControllerBase {
    LEDStripManager ledStripManager;

    public LEDController(Javalin _app, LEDStripManager _ledStripManager) {
        ledStripManager = _ledStripManager;

        _app.get("/led/procedure", ctx -> {
            String msg = new Gson().toJson(ledStripManager.procContainer.getActiveProcedure());
            System.out.println(msg);
            ctx.result("A");
        });

        _app.post("/led/procedure", ctx -> {
            ctx.res.setStatus(501);
        });

        _app.put("led/variables/{variable}", ctx -> {
            ctx.res.setStatus(501);
        });

        _app.get("/led/procedure/all", ctx -> {
            ctx.res.setStatus(501);
        });

        _app.get("/led/variables", ctx -> {
            ctx.res.setStatus(501);
        });

        _app.get("/led/variables/{variable}", ctx -> {
            ctx.res.setStatus(501);
        });

        _app.put("/led/variables/all", ctx -> {
            ctx.res.setStatus(501);
        });
    }
}
