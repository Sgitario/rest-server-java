package org.sgitario;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sgitario.data.BookRepository;
import org.sgitario.model.Book;

import java.io.IOException;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;
import static org.sgitario.Application.SERVER_PORT;

abstract class BaseIntegrationTest {

    private final Application application = new Application();
    private final BookRepository repository = new BookRepository();

    @BeforeEach
    void setUp() throws IOException {
        repository.deleteAll();
        application.start();
        RestAssured.port = SERVER_PORT;
    }

    @AfterEach
    void tearDown() {
        application.stop();
    }

    protected void givenExistingBook(String title, String author) {
        repository.addBook(new Book(title, author));
    }
}
