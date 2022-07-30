package com.github.iamniklas.liocore.network.javalin;

import com.github.iamniklas.liocore.network.javalin.controllers.*;
import io.javalin.Javalin;

import java.util.ArrayList;

public class JavalinHandler {
    public static int JAVALIN_PORT = 5700;
    private Javalin app;

    private final ArrayList<ControllerBase> controllers = new ArrayList<>();

    public JavalinHandler() {
        app = Javalin.create().start(JAVALIN_PORT);

        controllers.add(new DeviceController(app));
        controllers.add(new LEDController(app));
    }

    public JavalinHandler(int port) {
        app = Javalin.create().start(port);

        controllers.add(new DeviceController(app));
        controllers.add(new LEDController(app));
    }

    public void close() {
        app.close();
    }
}
