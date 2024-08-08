package org.sgitario;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.put;
import static org.hamcrest.Matchers.is;

class AddBooksIntegrationTest extends BaseIntegrationTest {

    @Test
    void testAddBookWhenEmptyRequestShouldReturn400() {
        RestAssured.given()
                .post("/api/books")
                .then()
                .statusCode(400);
    }

    @Test
    void testAddBookWhenWrongRequestShouldReturn400() {
        RestAssured.given().body("Quijote")
                .post("/api/books")
                .then()
                .statusCode(400);
    }

    @Test
    void testAddBookShouldReturn201() {
        RestAssured.given().body("Quijote-Cervantes")
                .post("/api/books")
                .then()
                .statusCode(201);
    }
}
