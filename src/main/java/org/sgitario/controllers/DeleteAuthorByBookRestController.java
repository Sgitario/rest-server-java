package org.sgitario.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.sgitario.services.BookService;

import java.io.IOException;

public class DeleteAuthorByBookRestController implements RestController {

    private final BookService bookService;

    public DeleteAuthorByBookRestController() {
        this(new BookService());
    }

    public DeleteAuthorByBookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String path() {
        return "/api/books/(\\w)+$";
    }

    @Override
    public Method method() {
        return Method.DELETE;
    }

    @Override
    public String handle(HttpExchange exchange) throws IOException {
        var parts = exchange.getRequestURI().getPath().split("/");
        String title = parts[parts.length - 1];
        boolean deleted = bookService.deleteBookByTitle(title);
        if (deleted) {
            exchange.sendResponseHeaders(204, -1);
        } else {
            exchange.sendResponseHeaders(404, -1);
        }

        return null;
    }
}
