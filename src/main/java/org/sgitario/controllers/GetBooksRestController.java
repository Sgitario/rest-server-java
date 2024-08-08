package org.sgitario.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.sgitario.model.Book;
import org.sgitario.services.BookService;

import java.io.IOException;

public class GetBooksRestController implements RestController {

    private final BookService bookService;

    public GetBooksRestController() {
        this(new BookService());
    }

    public GetBooksRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String path() {
        return "/api/books$";
    }

    @Override
    public Method method() {
        return Method.GET;
    }

    @Override
    public String handle(HttpExchange exchange) throws IOException {
        String response = bookService.getBooks().stream().map(Book::title).toList().toString();
        exchange.sendResponseHeaders(200, response.getBytes().length);
        return response;
    }
}
