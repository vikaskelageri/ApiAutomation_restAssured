package test;

import base.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import listeners.ExtentTestListener;
import org.testng.annotations.*;
import utils.APIEndpoints;
import utils.POJOs.InvoiceCreditpay;

import static io.restassured.RestAssured.given;

@Listeners(ExtentTestListener.class)
public class InvoicesAPI extends BaseTest {


    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private String accessToken;

    @Test(groups = {"post"})
    public void AddCredits() {

        InvoiceCreditpay payload = new InvoiceCreditpay(
                208,                      // policy_id
                "2025-04-25",             // received_on
                "WIRE_TRANSFER",          // payment_source
                1000.84,                  // amount
                "Cert_Issue",             // payer_name
                "certissue@gmail.com",    // payer_email
                "dsde2s",                 // remarks
                2334212                   // epay_transaction_id
        );

        Response rs = given().contentType("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(payload)
                .post(APIEndpoints.createCreditPay(208))
                .then().extract().response();

        System.out.println("Response: " + rs.asString());


    }







//    package test;
//
//import base.BaseTest;
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//import listeners.ExtentTestListener;
//import org.testng.annotations.*;
//import utils.APIEndpoints;
//
//import static io.restassured.RestAssured.given;
//
//    @Listeners(ExtentTestListener.class)
//    public class QuoteAPI extends BaseTest {
//
//        private RequestSpecification requestSpec;
//        private ResponseSpecification responseSpec;
//        private String accessToken;
//
//        @BeforeClass
//        public void setUp() {
//            accessToken = config.getProperty("accessToken");
//
//            requestSpec = new RequestSpecBuilder()
//                    .setContentType(ContentType.JSON)
//                    .addHeader("Authorization", "Bearer " + accessToken)
//                    .build();
//
//            responseSpec = new ResponseSpecBuilder()
//                    .expectStatusCode(200)
//                    .expectContentType(ContentType.JSON)
//                    .build();
//        }
//
//        @Test(groups = {"Get", "Quote"})
//        public void getQuotes() {
//            given()
//                    .spec(requestSpec)
//                    .queryParam("quote_status", "ALL")
//                    .when()
//                    .get(APIEndpoints.getQuoteList())
//                    .then()
//                    .spec(responseSpec)
//                    .log().body(); // Optional
//        }
//
//        @Test(groups = {"post"})
//        public void CreateQuote() {
//            String payload1 = config.getProperty("quote_create_json");
//            String payload2 = config.getProperty("create_location_json");
//
//            // Add Location
//            for (int i = 1; i <= 2; i++) {
//                given()
//                        .spec(requestSpec)
//                        .body(payload2)
//                        .when()
//                        .post(APIEndpoints.getLocation())
//                        .then()
//                        .spec(responseSpec)
//                        .log().body(); // Optional
//            }
//
//            // Create Quote
//            given()
//                    .spec(requestSpec)
//                    .body(payload1)
//                    .when()
//                    .post(APIEndpoints.getQuoteList())
//                    .then()
//                    .spec(responseSpec)
//                    .log().body(); // Optional
//        }
//
//        @Test(groups = {"delete", "quote"})
//        public void DeleteQuote() {
//            // Pending implementation
//            // Could use: .delete(APIEndpoints.deleteQuoteById(quoteId))
//        }
  //  }

}