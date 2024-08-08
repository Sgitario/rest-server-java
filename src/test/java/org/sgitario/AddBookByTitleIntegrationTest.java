package org.sgitario;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

class AddBookByTitleIntegrationTest extends BaseIntegrationTest {

    @Test
    void testAddBookWhenEmptyRequestShouldReturn400() {
        RestAssured.given()
                .put("/api/books/Quijote")
                .then()
                .statusCode(400);
    }

    @Test
    void testAddBookShouldReturn201() {
        RestAssured.given().body("Cervantes")
                .put("/api/books/Quijote")
                .then()
                .statusCode(201);
    }
}
