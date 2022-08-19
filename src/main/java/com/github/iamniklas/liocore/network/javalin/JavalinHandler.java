package com.github.iamniklas.liocore.network.javalin;

import com.github.iamniklas.liocore.config.ProgramConfiguration;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.network.javalin.controllers.*;
import io.javalin.Javalin;

import java.util.ArrayList;

public class JavalinHandler {
    public static int JAVALIN_PORT = 5700;
    private Javalin app;

    private final ArrayList<ControllerBase> controllers = new ArrayList<>();

    public JavalinHandler(LEDStripManager _ledStripManager, ProgramConfiguration _programConfiguration) {
        app = Javalin.create().start(JAVALIN_PORT);

        controllers.add(new DeviceController(app, _programConfiguration));
        controllers.add(new LEDController(app, _ledStripManager));
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
