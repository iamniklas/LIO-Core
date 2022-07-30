package com.github.iamniklas.liocore.network.javalin.controllers;

import io.javalin.Javalin;

public class LEDController extends ControllerBase {
    public LEDController(Javalin _app) {
        _app.get("/led/procedure", ctx -> {
            ctx.res.setStatus(501);
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
