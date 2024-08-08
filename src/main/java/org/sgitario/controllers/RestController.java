package org.sgitario.controllers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface RestController {

    String path();

    Method method();

    String handle(HttpExchange exchange) throws IOException;
}
