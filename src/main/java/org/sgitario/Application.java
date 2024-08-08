package org.sgitario;

import com.sun.net.httpserver.HttpServer;
import org.sgitario.controllers.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ServiceLoader;
import java.util.concurrent.Executors;

public class Application {

    public static final int SERVER_PORT = 8000;

    private HttpServer server;

    public void start() throws IOException {
        var controllers = ServiceLoader.load(RestController.class);
        server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
        server.createContext("/", (exchange -> {
            boolean handled = false;
            for (var controller : controllers) {
                if (exchange.getRequestURI().getPath().matches(controller.path())
                        && exchange.getRequestMethod().equalsIgnoreCase(controller.method().name())) {
                    String response = controller.handle(exchange);
                    if (response != null) {
                        OutputStream output = exchange.getResponseBody();
                        output.write(response.getBytes());
                        output.flush();
                    }

                    exchange.close();
                    handled = true;
                    break;
                }
            }

            if (!handled) {
                exchange.sendResponseHeaders(404, -1);
            }
        }));

        server.setExecutor(Executors.newCachedThreadPool()); // creates a default executor
        server.start();
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
        }
    }

    public static void main(String[] args) throws IOException {
        new Application().start();
    }
}
