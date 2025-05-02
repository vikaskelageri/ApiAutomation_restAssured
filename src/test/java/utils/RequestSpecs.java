package utils;

import base.BaseTest;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class RequestSpecs extends BaseTest{

    // 1. Authenticated requests with JSON
    public static RequestSpecification getSpecWithAuth() {
        return given()
                .baseUri(BaseTest.config.getProperty("baseURI"))
                .header("Authorization", "Bearer " + TokenManagement.getAccessToken())
                .header("Content-Type", "application/json");
    }

    // 2. Unauthenticated requests (for login or public APIs)
    public static RequestSpecification getSpecWithoutAuth() {
        return given()
                .baseUri(BaseTest.config.getProperty("baseURI"))
                .header("Content-Type", "application/json");
    }

    // 3. File upload spec (for multipart/form-data)
    public static RequestSpecification getFileUploadSpec() {
        return given()
                .baseUri(BaseTest.config.getProperty("baseURI"))
                .header("Authorization", "Bearer " + TokenManagement.getAccessToken())
                .multiPart("file", new java.io.File("path/to/your/file.png"));
    }

    // 4. Form data spec (e.g., x-www-form-urlencoded)
    public static RequestSpecification getFormDataSpec() {
        return given()
                .baseUri(BaseTest.config.getProperty("baseURI"))
                .header("Authorization", "Bearer " + TokenManagement.getAccessToken())
                .contentType("application/x-www-form-urlencoded");
    }
}
