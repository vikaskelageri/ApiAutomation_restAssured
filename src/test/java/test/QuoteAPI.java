package test;


import listeners.ExtentTestListener;
import org.testng.Reporter;
import org.testng.annotations.*;
import io.restassured.response.Response;
import base.BaseTest;
import utils.APIEndpoints;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners(ExtentTestListener.class)
public class QuoteAPI extends BaseTest{

    String accesskey=config.getProperty("accessToken");
    @Test(groups = {"Get","Quote"})
    public void getQuotes(){

        Response res=given().queryParam("quote_status", "ALL")
                .header("Authorization", "Bearer " + accesskey)
                .get(APIEndpoints.getQuoteList())
                .then().log().all().extract().response();
        System.out.println(res.asString());
    }

    @Test(groups = {"post"})
    public void CreateQuote()
    {
        String payload1 = config.getProperty("quote_create_json");
        String payload2 = config.getProperty("create_location_json");



        /// ///////    Add Location       //////////////
        Response rs=given()
                .header("Authorization", "Bearer " + accesskey)
                .contentType("application/json")
                .body(payload2)
                .post(APIEndpoints.getLocation())
                .then().extract().response();
        System.out.println("Response: "+rs.asString());

        Response rs3=given()
                .header("Authorization", "Bearer " + accesskey)
                .contentType("application/json")
                .body(payload2)
                .post(APIEndpoints.getLocation())
                .then().extract().response();

        System.out.println("Response: "+rs3.asString());


         /// ///////    Create Quote       //////////////
        Response rs2=given()
                .header("Authorization", "Bearer " + accesskey)
                .contentType("application/json")
                .body(payload1)
                .post(APIEndpoints.getQuoteList())
                .then().extract().response();

        System.out.println("Response: "+rs2.asString());

    }

    @Test(groups = {"delete","quote"})
    public void DeleteQuote()
    {

    }
}
