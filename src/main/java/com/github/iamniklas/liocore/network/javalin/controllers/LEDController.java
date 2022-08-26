package com.github.iamniklas.liocore.network.javalin.controllers;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.ProcedureFactory;
import com.google.gson.Gson;
import io.javalin.Javalin;

import java.io.*;
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
            try {
                LEDUpdateModel updateModel = new Gson().fromJson(ctx.body(), LEDUpdateModel.class);
                updateModel.bundle.ledStrip = ledStripManager;
                updateModel.bundle.procedureCalls = ledStripManager;
                Procedure p = ProcedureFactory.getProcedure(updateModel);
                ledStripManager.procContainer.replaceActiveProcedure(p);
            }
            catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String sStackTrace = sw.toString(); // stack trace as a string

                ctx.result(sStackTrace);
            }
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
            LEDDataBundle ledDataBundle = new Gson().fromJson(ctx.body(), LEDDataBundle.class);
            ledStripManager.procContainer.getActiveProcedure().updateLEDDataBundle(ledDataBundle);
        });
    }
}
