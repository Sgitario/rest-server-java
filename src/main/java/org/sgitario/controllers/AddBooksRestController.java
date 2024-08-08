package org.sgitario.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.sgitario.model.Book;
import org.sgitario.services.BookService;

import java.io.IOException;

public class AddBooksRestController implements RestController {

    private final BookService bookService;

    public AddBooksRestController() {
        this(new BookService());
    }

    public AddBooksRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String path() {
        return "/api/books$";
    }

    @Override
    public Method method() {
        return Method.POST;
    }

    @Override
    public String handle(HttpExchange exchange) throws IOException {
        String request = new String(exchange.getRequestBody().readAllBytes());
        String[] parts = request.split("-");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            exchange.sendResponseHeaders(400, 0);
            return null;
        }

        bookService.addBook(new Book(parts[0], parts[1]));
        exchange.sendResponseHeaders(201, 0);
        return null;
    }
}
