package test; // Assuming your test package is 'test'

import base.BaseTest; // Import your BaseTest class
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*; // Import TestNG annotations

import utils.TokenManagement; // Your TokenManagement class
import utils.APIEndpoints;     // Your APIEndpoints class
import utils.AuthVerificationHelpers; // Your helper class
import utils.RequestSpecs; // Your RequestSpecs class (assuming it provides base specs)
import utils.ResponseSpecs; // Your ResponseSpecs class (if helpers or tests use it)

import static io.restassured.RestAssured.given; // Keep for starting a spec if needed
// Import Hamcrest matchers you might use for body assertions in functional tests
import static org.hamcrest.Matchers.*;


@Listeners(listeners.ExtentTestListener.class) // Assuming you use this listener
public class AuthenticationSecurityTests extends BaseTest { // Extend your BaseTest


    // You can define base request/response specs if needed for this class,
    // similar to your InvoicesAPI, although the helpers mostly handle the chaining.
    // private RequestSpecification requestSpec;
    // private ResponseSpecification responseSpec;


    // Use @BeforeClass for setup that runs once per test class
    @BeforeClass
    public void setupAuthenticationTests() {
        // Initialize TokenManagement once before any tests in this class run
        TokenManagement.initializeToken();
        System.out.println("AuthenticationSecurityTests: @BeforeClass setup completed.");
        // You could potentially initialize request/response specs here if they are static or instance variables
        // requestSpec = RequestSpecs.getBaseSpec(); // Example if you have a base spec without auth
        // responseSpec = ResponseSpecs.defaultResponseSpec();
    }

    // You might use @BeforeMethod if you needed a fresh spec for *each* test method,
    // but for these authentication checks, using given() directly in the helper call is often sufficient
    // if RequestSpecs.getBaseSpec() returns a new spec instance or you just start with given().

    @Test(description = "Verify User Profile endpoint requires authentication (GET) No Authorization header")
    public void testUserProfileEndpointRequiresAuthentication() {
        // Get a base spec (without authentication added yet)


        // Call the helper, passing the base spec and the endpoint
        AuthVerificationHelpers.assertGetRequestRequiresAuthentication( APIEndpoints.updateAgent(23)); // Use any valid ID for path
    }

    @Test(description = "Verify User Profile endpoint rejects invalid token (GET)")
    public void testUserProfileEndpointRejectsInvalidToken() {

        // Call the helper, passing the base spec and the endpoint
        AuthVerificationHelpers.assertGetRequestWithInvalidTokenIsUnauthorized(APIEndpoints.updateAgent(1011)); // Use any valid ID for path
    }

//    @Test(description = "Verify Orders endpoint requires authentication (GET)")
//    public void testOrdersEndpointRequiresAuthentication() {
//        // Get a base spec (without authentication added yet)
//        RequestSpecification baseSpec = given(); // Or RequestSpecs.getBaseSpec();
//
//        // Call the helper
//        AuthVerificationHelpers.assertGetRequestRequiresAuthentication(baseSpec, APIEndpoints.getOrders()); // Assuming this endpoint exists
//    }
//
//    @Test(description = "Verify Create Product endpoint requires authentication (POST)")
//    public void testCreateProductEndpointRequiresAuthentication() {
//        // Get a base spec (without authentication added yet)
//        RequestSpecification baseSpec = given(); // Or RequestSpecs.getBaseSpec();
//
//        // Need a minimal valid-like body for the 401 check, even if it won't be processed
//        Object dummyBody = new Object() { // Simple anonymous object as dummy body
//            public String getName() { return "dummy"; }
//            // Add other required fields if the API rejects based on missing body fields even before auth
//        };
//        // Call the helper
//        AuthVerificationHelpers.assertPostRequestRequiresAuthentication(baseSpec, APIEndpoints.createProduct(), dummyBody); // Assuming this endpoint exists
//    }
//
//    // Example test for successful access (requires valid token)
//    @Test(description = "Verify User Profile endpoint allows access with valid token (GET)")
//    public void testUserProfileEndpointAllowsValidAuthentication() {
//        // Get a base spec (without authentication added yet)
//        RequestSpecification baseSpec = given(); // Or RequestSpecs.getBaseSpec();
//        // Note: The helper internally gets the valid token from TokenManagement
//
//        // Call the helper, which will add the valid token header and assert 200
//        Response response = AuthVerificationHelpers.assertGetRequestIsAuthenticatedAndAllowed(baseSpec, APIEndpoints.getUserProfile("authenticated_user_id")); // Replace with an ID you can access
//
//        // You can add further assertions on the response body if needed for this specific test
//        response.then()
//                .body("id", equalTo("authenticated_user_id")); // Example body assertion
//    }

}