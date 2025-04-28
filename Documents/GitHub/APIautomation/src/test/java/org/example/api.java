
        package org.example;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class api{

    @Test
    void getUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()  // Log request and response
                .statusCode(200)
                .body("data.id", equalTo(2))  // Corrected JSON path
                .body("data.email", containsString("@reqres.in"))  // Additional validation
                .body("support.url", notNullValue());  // Check for non-null value
    }
}