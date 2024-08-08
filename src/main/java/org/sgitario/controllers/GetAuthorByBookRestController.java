package org.sgitario.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.sgitario.model.Book;
import org.sgitario.services.BookService;

import java.io.IOException;

public class GetAuthorByBookRestController implements RestController {

    private final BookService bookService;

    public GetAuthorByBookRestController() {
        this(new BookService());
    }

    public GetAuthorByBookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String path() {
        return "/api/books/(\\w)+$";
    }

    @Override
    public Method method() {
        return Method.GET;
    }

    @Override
    public String handle(HttpExchange exchange) throws IOException {
        var parts = exchange.getRequestURI().getPath().split("/");
        String title = parts[parts.length - 1];
        String response = bookService.getAuthorByBook(title);
        if (response == null) {
            exchange.sendResponseHeaders(404, -1);
        } else {
            exchange.sendResponseHeaders(200, response.getBytes().length);
        }

        return response;
    }
}
