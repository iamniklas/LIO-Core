package com.github.iamniklas.liocore.network.javalin;

import io.javalin.Javalin;

import java.nio.charset.StandardCharsets;

public class JavalinHandler {
    public static int JAVALIN_PORT = 5700;
    private Javalin app;

    public JavalinHandler(int port) {
        app = Javalin.create().start(JAVALIN_PORT);

        app.put("/led/update", ctx -> {
            String failParam = ctx.queryParam("fail");
            if(failParam != null && failParam.equals("1")) {
                ctx.res.setStatus(500);
                ctx.result("Failed");
                return;
            }
            ctx.result(ctx.body());
        });

        app.get("/led/echo", ctx -> {
            ctx.res.setStatus(418);
            ctx.result("echo");
        } );
    }

    public void close() {
        app.close();
    }
}
