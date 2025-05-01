package test;


import io.restassured.specification.RequestSpecification;
import listeners.ExtentTestListener;
import org.testng.Reporter;
import org.testng.annotations.*;
import io.restassured.response.Response;
import base.BaseTest;
import utils.APIEndpoints;
import utils.ResponseSpecs;
import utils.RequestSpecs;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners(ExtentTestListener.class)
public class QuoteAPI extends BaseTest{


    @Test(groups = {"Get","Quote"})
    public void getQuotes(){

        Response res=given().queryParam("quote_status", "ALL")
                .header("Authorization", "Bearer " + accessToken)
                .get(APIEndpoints.getQuoteList())
                .then().log().all().extract().response();
        System.out.println(res.asString());
    }

    @Test(groups = {"post"})
    public void CreateQuote() throws IOException {

        String payload1 = getPayloadFromConfig("quote_create_json");
        String payload2 = getPayloadFromConfig("create_location_json");


        /// ///////    Add Location       //////////////
        given().spec(RequestSpecs.getSpecWithAuth()).body(payload2)
                .when().post(APIEndpoints.getLocation())
                .then().spec(ResponseSpecs.defaultResponseSpec());

        /// ///////    Create Quote       //////////////
        given().spec(RequestSpecs.getSpecWithAuth()).body(payload1)
                .when().post(APIEndpoints.getQuoteList())
                .then().spec(ResponseSpecs.createdSpec());


    }

    @Test(groups = {"delete","quote"})
    public void DeleteQuote()
    {
          given().spec(RequestSpecs.getSpecWithAuth())
                  .when().delete(APIEndpoints.deleteQuote(1108))
                  .then()
                  .log().body()
                  .spec(ResponseSpecs.defaultResponseSpec())
                  .body("detail",equalTo("Quote Deleted Successfully"));

    }
}
