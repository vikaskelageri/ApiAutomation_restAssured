package test;

import base.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import listeners.ExtentTestListener;
import org.testng.annotations.*;
import utils.APIEndpoints;
import utils.POJOs.InvoiceCreditpay;
import utils.RequestSpecs;
import utils.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners(ExtentTestListener.class)
public class InvoicesAPI extends BaseTest {


    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;


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
        given().spec(RequestSpecs.getSpecWithAuth())
                .body(payload)
                .post(APIEndpoints.createCreditPay(208))
                .then().spec(ResponseSpecs.defaultResponseSpec()).body("status",equalTo("success"));

    }










}