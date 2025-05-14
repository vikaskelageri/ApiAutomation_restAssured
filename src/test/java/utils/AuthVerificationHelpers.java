package utils; // Use the same package as TokenManagement or an appropriate utils package

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo; // For assertions if needed

public class AuthVerificationHelpers {

    // You can define a static invalid token for testing the 401 scenario.
    // This should be a token that you know is invalid or malformed for your API.
    private static final String INVALID_ACCESS_TOKEN = "invalid_token_string_12345";
    // If testing specifically expired tokens requires a different format or endpoint setup,
    // you might need a more complex approach or separate methods.

    /**
     * Makes an authenticated GET request to the specified endpoint using a valid token
     * obtained from TokenManagement and asserts that the status code is 200 (OK).
     * This verifies successful authentication for GET access.
     *
     * @param endpointPath The path of the API endpoint (e.g., "/users/profile").
     * @return The Response object for further assertions on the body, etc.
     */
    public static Response assertGetRequestIsAuthenticatedAndAllowed(String endpointPath) {
        String validToken = TokenManagement.getAccessToken(); // Get the current valid token

        Response response = given()
                .header("Authorization", "Bearer " + validToken) // Assuming Bearer token format
                .when()
                .get(endpointPath);

        response.then()
                .statusCode(200); // Assert that access is granted

        System.out.println("Verified: GET " + endpointPath + " with valid token returned 200 OK.");
        return response;
    }

    /**
     * Makes an authenticated POST request to the specified endpoint using a valid token
     */
    public static Response assertPostRequestIsAuthenticatedAndAllowed(String endpointPath, Object requestBody) {
        String validToken = TokenManagement.getAccessToken(); // Get the current valid token

        Response response = given()
                .header("Authorization", "Bearer " + validToken) // Assuming Bearer token format
                .contentType("application/json") // Assuming JSON body
                .body(requestBody)
                .when()
                .post(endpointPath);

        response.then()
                .statusCode(anyOf(equalTo(200), equalTo(201))); // Assert 200 or 201

        System.out.println("Verified: POST " + endpointPath + " with valid token returned 200/201.");
        return response;
    }

    /**
     * Makes a GET request to the specified endpoint without any token and
     */
    public static Response assertGetRequestRequiresAuthentication(String endpointPath) {
        Response response = given()
                // No Authorization header
                .when()
                .get(endpointPath);

        response.then()
                .statusCode(401); // Assert Unauthorized

        System.out.println("Verified: GET " + endpointPath + " without token returned 401 Unauthorized.");
        return response;
    }

    /**
     * Makes a POST request to the specified endpoint without any token and
     */
    public static Response assertPostRequestRequiresAuthentication(String endpointPath, Object requestBody) {
        Response response = given()
                .contentType("application/json") // Still send content type if body might be expected
                .body(requestBody)
                .when()
                .post(endpointPath);

        response.then()
                .statusCode(401); // Assert Unauthorized

        System.out.println("Verified: POST " + endpointPath + " without token returned 401 Unauthorized.");
        return response;
    }


    /**
     * Makes a GET request to the specified endpoint using a known invalid token and
     * asserts that the status code is 401 (Unauthorized).
     * This verifies that the API correctly rejects invalid credentials.
     *
     * @param endpointPath The path of the API endpoint.
     * @return The Response object.
     */
    public static Response assertGetRequestWithInvalidTokenIsUnauthorized(String endpointPath) {
        Response response = given()
                .header("Authorization", "Bearer " + INVALID_ACCESS_TOKEN) // Use a clearly invalid token
                .when()
                .get(endpointPath);

        response.then()
                .statusCode(401); // Assert Unauthorized

        System.out.println("Verified: GET " + endpointPath + " with invalid token returned 401 Unauthorized.");
        return response;
    }

    /**
     * Makes a POST request to the specified endpoint using a known invalid token and
     * asserts that the status code is 401 (Unauthorized).
     *
     * @param endpointPath The path of the API endpoint.
     * @param requestBody The body for the POST request.
     * @return The Response object.
     */
    public static Response assertPostRequestWithInvalidTokenIsUnauthorized(String endpointPath, Object requestBody) {
        Response response = given()
                .header("Authorization", "Bearer " + INVALID_ACCESS_TOKEN) // Use a clearly invalid token
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(endpointPath);

        response.then()
                .statusCode(401); // Assert Unauthorized

        System.out.println("Verified: POST " + endpointPath + " with invalid token returned 401 Unauthorized.");
        return response;
    }


    // You can add similar methods for PUT, DELETE, PATCH if needed

}