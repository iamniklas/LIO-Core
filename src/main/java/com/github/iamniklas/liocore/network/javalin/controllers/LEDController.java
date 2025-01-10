package com.github.iamniklas.liocore.network.javalin.controllers;

import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.Procedure;
import com.github.iamniklas.liocore.procedures.ProcedureAction;
import com.github.iamniklas.liocore.procedures.ProcedureFactory;
import com.google.gson.Gson;
import io.javalin.Javalin;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;

public class LEDController extends ControllerBase {
    LEDStripManager ledStripManager;

    private static final Logger logger = Logger.getLogger(LEDController.class);

    private Procedure lastProcedure;

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

        //Endpoint to get the last procedure that was sent to the LED strip
        _app.get("/led/procedure/last", ctx -> {
            if(lastProcedure != null) {
                ctx.result(new Gson().toJson(lastProcedure.ledUpdateModel));
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
                lastProcedure = p;
                if(p != null) {
                    logger.info("Received new procedure: " + p.ledUpdateModel.procedure.name());
                    ledStripManager.procContainer.replaceActiveProcedure(p);
                }
            }
            catch (Exception e) {
                ctx.status(500);
                logger.error(e.getMessage());
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

        _app.put("/led/variables/", ctx -> {
            try {
                String body = ctx.body();
                LEDDataBundle ledDataBundle = new Gson().fromJson(body, LEDDataBundle.class);
                ledStripManager.procContainer.getActiveProcedure().updateLEDDataBundle(ledDataBundle);
            }
            catch(Exception e) {
                ctx.status(500);
                logger.error(e.getMessage());
            }
        });

        _app.put("/led/action/{action_name}", ctx -> {
            try {
                ProcedureAction action = Enum.valueOf(ProcedureAction.class, ctx.pathParam("action_name"));
                LEDUpdateModel ledUpdateModel = new Gson().fromJson(ctx.body(), LEDUpdateModel.class);
                ledUpdateModel.bundle.ledStrip = ledStripManager;
                ledUpdateModel.bundle.procedureCalls = ledStripManager;

                if(ledStripManager.procContainer.getActiveProcedure() == null ||
                        ledUpdateModel.procedure != ledStripManager.procContainer.getActiveProcedure().ledUpdateModel.procedure) {
                    Procedure p = ProcedureFactory.getProcedure(ledUpdateModel);
                    ledStripManager.procContainer.replaceActiveProcedure(p);
                    ctx.result(ledUpdateModel.procedure.name());
                } else {
                    ledStripManager.procContainer.getActiveProcedure().updateLEDDataBundle(ledUpdateModel.bundle);
                    ledStripManager.procContainer.getActiveProcedure().onActionReceived(action);
                    ctx.result(action.name());
                }
            }
            catch (Exception e) {
                ctx.status(500);
                logger.error(e.getMessage());
            }
        });
    }
}
