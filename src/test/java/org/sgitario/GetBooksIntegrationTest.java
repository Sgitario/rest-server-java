package org.sgitario;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;

class GetBooksIntegrationTest extends BaseIntegrationTest {

    @Test
    void testGetBooksWhenNoBooksThenItShouldReturnEmptyList() {
        get("/api/books")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }
}
