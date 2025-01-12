package com.github.iamniklas.liocore.network.javalin;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.network.javalin.controllers.*;
import com.google.gson.Gson;
import io.javalin.Javalin;

import java.util.ArrayList;

public class JavalinHandler {
    public static final int JAVALIN_PORT = 5700;

    public static final int ECHO_SUCCESS_CODE = 418;

    private Javalin app;

    private final ArrayList<ControllerBase> controllers = new ArrayList<>();

    public JavalinHandler(LEDStripManager _ledStripManager, ProgramConfiguration _programConfiguration) {
        app = Javalin.create().start(JAVALIN_PORT);

        controllers.add(new DeviceController(app, _programConfiguration));
        controllers.add(new LEDController(app, _ledStripManager));
        controllers.add(new SmartHomeController(app, _ledStripManager, new Gson()));
    }

    public JavalinHandler(int port, LEDStripManager _ledStripManager, ProgramConfiguration _programConfiguration) {
        app = Javalin.create().start(port);

        controllers.add(new DeviceController(app, _programConfiguration));
        controllers.add(new LEDController(app, _ledStripManager));
    }

    public void close() {
        app.close();
    }
}
