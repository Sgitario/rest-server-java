package org.sgitario;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

class DeleteBookByTitleIntegrationTest extends BaseIntegrationTest {

    @Test
    void testWhenBookDoesNotExistThenReturns404() {
        RestAssured.given()
                .delete("/api/books/Quijote")
                .then()
                .statusCode(404);
    }

    @Test
    void testWhenBookExistsThenReturn204() {
        givenExistingBook("Quijote", "Cervantes");
        RestAssured.given()
                .delete("/api/books/Quijote")
                .then()
                .statusCode(204);
    }
}
