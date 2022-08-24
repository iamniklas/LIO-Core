package com.github.iamniklas.liocore.network.javalin.controllers;

import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.google.gson.Gson;
import io.javalin.Javalin;

import java.lang.reflect.Field;

public class LEDController extends ControllerBase {
    LEDStripManager ledStripManager;

    public LEDController(Javalin _app, LEDStripManager _ledStripManager) {
        ledStripManager = _ledStripManager;

        _app.get("/led/procedure", ctx -> {
            if(ledStripManager.procContainer.getActiveProcedure() != null) {
                ctx.result(new Gson().toJson(ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.procedure));
            }
            else {
                ctx.result("null");
            }
        });

        _app.post("/led/procedure", ctx -> {
            ctx.res.setStatus(501);
        });

        _app.put("led/variables/{variable}", ctx -> {
            ctx.res.setStatus(501);
        });

        _app.get("/led/procedure/all", ctx -> {
            if(ledStripManager.procContainer.getActiveProcedure() != null) {
                ctx.result(new Gson().toJson(ledStripManager.procContainer.getActiveProcedure().ledUpdateModel));
            }
            else {
                ctx.result("null");
            }
        });

        _app.get("/led/variables", ctx -> {
            if(ledStripManager.procContainer.getActiveProcedure() != null) {
                ctx.result(new Gson().toJson(ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle));
            }
            else {
                ctx.result("null");
            }
        });

        _app.get("/led/variables/{variable}", ctx -> {
            try {
                Field field = ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle.getClass().getDeclaredField(ctx.pathParam("variable"));
                field.setAccessible(true);
                Object value = field.get(ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.bundle);
                ctx.result(value.toString());
            }
            catch (Exception e) {
                ctx.result("null");
            }
        });

        _app.put("/led/variables/all", ctx -> {
            LEDUpdateModel ledUpdateModel = ctx.bodyAsClass(LEDUpdateModel.class);
            ledStripManager.procContainer.getActiveProcedure().updateLEDUpdateModel(ledUpdateModel);
            ctx.res.setStatus(501);
        });
    }
}
