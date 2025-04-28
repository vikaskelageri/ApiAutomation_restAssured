package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testGetRequest() {
        // Base URL
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Sending GET request to endpoint "/posts/1"
        Response response = RestAssured
                .given()
                .when()
                .get("/posts/1");

        // Print response body
        System.out.println("Response: " + response.getBody().asString());

        // Validate status code
        assertEquals(200, response.getStatusCode(), "Expected status code 200");

        // Validate response content
        assertEquals("1", response.jsonPath().getString("id"), "Expected ID 1");
    }
}
