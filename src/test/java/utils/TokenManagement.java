package utils;

import io.restassured.RestAssured; // Need RestAssured for the call
import io.restassured.response.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class TokenManagement {

    private static String accessToken;
    private static Properties config;
    private static boolean isInitialized = false; // Flag to prevent multiple initializations

    // Static initializer to load config early
    static {
        try {
            System.out.println("TokenManagement static block: Loading config...");
            config = ConfigManager.getConfig();
            if (config == null) {
                System.err.println("TokenManagement static block: Config is NULL after loading!");
                // Throwing here ensures the class fails to load if config is missing
                throw new RuntimeException("Failed to load config in TokenManagement static initializer");
            }
            System.out.println("TokenManagement static block: Config loaded.");
            // Set baseURI here as well, just in case it's needed before BaseTest runs
            // or if TokenManagement is used independently.
            if (config.getProperty("baseURI") != null) {
                RestAssured.baseURI = config.getProperty("baseURI");
                System.out.println("TokenManagement static block: BaseURI set to " + RestAssured.baseURI);
            } else {
                System.err.println("TokenManagement static block: baseURI not found in config!");
            }

        } catch (Exception e) {
            System.err.println("Exception in TokenManagement static block: " + e.getMessage());
            // Throw exception to prevent using the class in an invalid state
            throw new RuntimeException("Failed during TokenManagement static initialization", e);
        }
    }

    /**
     * Initializes the access token by calling the authentication endpoint.
     * Should be called once, typically from @BeforeSuite.
     * Throws RuntimeException on failure.
     */
    public static synchronized void initializeToken() { // Added synchronized
        if (isInitialized) {
            System.out.println("Token already initialized. Skipping authentication call.");
            return; // Don't re-authenticate if already done
        }
        System.out.println("Initializing token...");
        if (config == null) {
            System.err.println("Cannot initialize token: config is null.");
            throw new IllegalStateException("Configuration is not loaded in TokenManagement.");
        }

        try {
            accessToken = authenticateAndGetToken();
            if (accessToken == null || accessToken.isEmpty()) {
                System.err.println("Authentication succeeded but token is null or empty.");
                throw new RuntimeException("Received null or empty token after authentication.");
            }
            System.out.println("Token successfully retrieved and stored.");
            isInitialized = true; // Mark as initialized
        } catch (Exception e) {
            // Log the error and re-throw a runtime exception to signal failure
            System.err.println("Failed to authenticate and get token: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
            isInitialized = false; // Ensure it's marked as not initialized on failure
            throw new RuntimeException("Authentication failed during initialization.", e);
        }
    }

    /**
     * Returns the stored access token.
     * Throws IllegalStateException if initializeToken() was not called successfully first.
     * @return The access token string.
     */
    public static String getAccessToken() {
        if (!isInitialized || accessToken == null || accessToken.isEmpty()) {
            System.err.println("Attempted to get token before successful initialization.");
            // This indicates a flaw in the test setup flow
            throw new IllegalStateException("Access Token is not available. Ensure initializeToken() was called successfully in @BeforeSuite.");
        }
        return accessToken;
    }

    /**
     * Performs the actual authentication API call.
     * @return The extracted access token.
     * @throws IOException If payload file reading fails.
     * @throws RuntimeException If API call or token extraction fails.
     */
    private static String authenticateAndGetToken() throws IOException {
        System.out.println("Executing authenticateAndGetToken...");
        String username = config.getProperty("username");
        String password = config.getProperty("password");
        String payloadPath = config.getProperty("loginJson");
        String baseUri = config.getProperty("baseURI"); // Get base URI for logging/verification

        if (username == null || password == null || payloadPath == null) {
            throw new RuntimeException("Missing username, password, or loginJson path in configuration.");
        }
        if (baseUri == null) {
            throw new RuntimeException("Missing baseURI in configuration.");
        }
        // Ensure RestAssured's baseURI is set (might be redundant if static block worked, but safe)
        if (RestAssured.baseURI == null || !RestAssured.baseURI.equals(baseUri)) {
            System.out.println("Setting RestAssured.baseURI in authenticateAndGetToken to: " + baseUri);
            RestAssured.baseURI = baseUri;
        }


        String payload = new String(Files.readAllBytes(Paths.get(payloadPath)));
        String loginEndpoint = APIEndpoints.login(); // Assuming this returns the correct path like "/api/login"

        System.out.println("Authenticating user '" + username + "' at endpoint: " + baseUri + loginEndpoint);

        Response response = null;
        try {
            response = given()
                    .log().uri() // Log the request URI
                    .auth().preemptive().basic(username, password)
                    .contentType("application/json")
                    .body(payload)
                    .post(loginEndpoint) // Use the relative path from APIEndpoints
                    .then()
                    .log().status() // Log the response status
                    // .log().body() // Temporarily log response body for debugging
                    .extract().response();
        } catch (Exception e) {
            System.err.println("Exception during authentication request: " + e.getMessage());
            throw new RuntimeException("Failed to execute authentication POST request", e);
        }


        System.out.println("Authentication Raw Response: " + response.asString()); // Log raw response

        // *** IMPORTANT: Verify the JSON Path ***
        // Double-check if "detail.access" is EXACTLY how the token is nested in the response JSON.
        // If the response is {"access": "xyz...", "refresh": "abc..."}, the path should be "access".
        // If the response is {"detail": {"access": "xyz..."}}, then "detail.access" is correct.
        String token = null;
        try {
            token = response.jsonPath().getString("detail.access"); // Adjust this path if needed!
        } catch (Exception e) {
            System.err.println("Failed to extract token using JsonPath 'detail.access'. Response was: " + response.asString());
            throw new RuntimeException("Failed to parse token from authentication response", e);
        }


        if (token == null || token.isEmpty()) {
            System.err.println("Extracted token is null or empty using path 'detail.access'. Check the JSON path and response structure.");
            System.err.println("Response Body: " + response.asString());
            // Don't throw here yet, let the caller (initializeToken) handle null/empty check
        } else {
            System.out.println("Successfully extracted token (first few chars): " + token.substring(0, Math.min(token.length(), 10)) + "...");
        }

        return token;
    }
}