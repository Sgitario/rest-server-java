package org.sgitario;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;

class GetAuthorByBookIntegrationTest extends BaseIntegrationTest {

    @Test
    void testWhenBookDoesNotExistThenReturns404() {
        get("/api/books/Quijote")
                .then()
                .statusCode(404);
    }

    @Test
    void testWhenBookExistsThenReturnsAuthor() {
        givenExistingBook("Quijote", "Cervantes");
        get("/api/books/Quijote")
                .then()
                .statusCode(200)
                .body(is("Cervantes"));
    }
}
