package org.sgitario.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.sgitario.model.Book;
import org.sgitario.services.BookService;

import java.io.IOException;

public class AddBookByTitleRestController implements RestController {

    private final BookService bookService;

    public AddBookByTitleRestController() {
        this(new BookService());
    }

    public AddBookByTitleRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String path() {
        return "/api/books/(\\w)+$";
    }

    @Override
    public Method method() {
        return Method.PUT;
    }

    @Override
    public String handle(HttpExchange exchange) throws IOException {
        var parts = exchange.getRequestURI().getPath().split("/");
        String title = parts[parts.length - 1];
        String author = new String(exchange.getRequestBody().readAllBytes());
        if (author.isEmpty()) {
            exchange.sendResponseHeaders(400, 0);
            return null;
        }

        bookService.addBook(new Book(title, author));
        exchange.sendResponseHeaders(201, 0);
        return null;
    }
}
