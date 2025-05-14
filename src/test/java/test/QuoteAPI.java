package test;


import com.beust.jcommander.Parameter;
import io.restassured.specification.RequestSpecification;
import listeners.ExtentTestListener;
import org.testng.Reporter;
import org.testng.annotations.*;
import io.restassured.response.Response;
import base.BaseTest;
import utils.APIEndpoints;
import utils.ResponseSpecs;
import utils.RequestSpecs;
import utils.TokenManagement;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utils.ExtentReportManager.extent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners(ExtentTestListener.class)
public class QuoteAPI extends BaseTest{


    @Test(groups = {"get", "Quote"})
    public void getQuotes() {
        String accessToken = TokenManagement.getAccessToken();
        System.out.println("Access Token in test: " + accessToken);

        Response res = given()
                .queryParam("quote_status", "ALL")
                .header("Authorization", "Bearer " + accessToken)
                .get(APIEndpoints.getQuoteList())
                .then().extract().response();

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
    @Test(groups = {"post"})
    public void ValidateQuoteCreate() throws IOException {

        String payload1 = getPayloadFromConfig("quote_create_json");
        String payload2 = getPayloadFromConfig("create_location_json");


        /// ///////    Add Location       //////////////
        Response rs=given().spec(RequestSpecs.getSpecWithAuth()).body(payload2)
                .when().post(APIEndpoints.getLocation())
                .then().body("detail[0].fees",equalTo(50))
                .body("detail[0].water_sewer_amt",equalTo(25.0F))
                .body("detail[0].taxes_details.sl_tax",equalTo(100))
                .spec(ResponseSpecs.defaultResponseSpec()).extract().response();
        System.out.println(rs.jsonPath().getString("detail.premium"));




        /// ///////    Create Quote       //////////////
        given().spec(RequestSpecs.getSpecWithAuth()).body(payload1)
                .when().post(APIEndpoints.getQuoteList())
                .then().spec(ResponseSpecs.createdSpec());


    }

    @DataProvider(name = "quoteIds")
    public Object[][] provideQuoteIds() {
        return new Object[][] {
                {1135},
                {2001},
                {3009}
        };
    }
    @Test @Parameters("deleteids")
    public void DeleteQuote(String id)
    {
        int quoteId = Integer.parseInt(id);
        Response res= given().spec(RequestSpecs.getSpecWithAuth())
                  .when().delete(APIEndpoints.deleteQuote(quoteId))
                  .then()
                  .extract().response();


                  if(res.getStatusCode()==200){
                      System.out.println("Quote found and deleted.");
                  }
                  else if(res.getStatusCode()==400){
                      System.out.println("No Quote found with id");
                  }
                  else{
                      System.out.println("Unexpected status code: "+res.statusCode());
                  }


    }

    @Test
    public void verifyFeesAfter15Location() throws IOException {
       String payload= getPayloadFromConfig("Location15verify");

        Response rs=given().spec(RequestSpecs.getSpecWithAuth())
                .body(payload)
                .when().post(APIEndpoints.getLocation())
                .then()
                .body("detail[14].fees",equalTo(50)).spec(ResponseSpecs.defaultResponseSpec())
                .extract().response();
        String output=rs.jsonPath().getString("detail[14].fees");
        String output2=rs.jsonPath().getString("detail[15].fees");
        if(output.equals("50")){
            System.out.println("fees before 15 location "+output);
            extent.setSystemInfo("Result", "fees before 15 location"+output);

        }
        else{
            System.out.println("failed! fees before 15 location "+output);
            extent.setSystemInfo("Result", "fees before 15 location"+output);

        }
        if(output2.equals("0")){
            System.out.println("fees after 15 location "+output2);
            extent.setSystemInfo("Result", "fees after 15 location"+output2);

        }
        else{
            System.out.println("failed! fees before 15 location "+output);
            extent.setSystemInfo("Result", "fees after 15 location"+output2);

        }




    }
    @Test
    public void deleteLocation(){

        Response rs=given().spec(RequestSpecs.getSpecWithAuth())
                .body("")
                .when().post(APIEndpoints.getLocation())
                .then().spec(ResponseSpecs.defaultResponseSpec())
                .extract().response();
        if(rs.jsonPath().getString("status").equalsIgnoreCase("success")){
            System.out.println("Location is removed");
        }


    }


    @Test(groups = {"post"})
    public void create15Quotes() throws IOException {

        String payload1 = getPayloadFromConfig("quote_create_json");
        String payload2 = getPayloadFromConfig("create_location_json");


        /// ///////    Add Location       //////////////

            Response rs = given().spec(RequestSpecs.getSpecWithAuth()).body(payload2)
                    .when().post(APIEndpoints.getLocation())
                    .then().body("detail[0].fees", equalTo(50))
                    .body("detail[0].water_sewer_amt", equalTo(25.0F))
                    .body("detail[0].taxes_details.sl_tax", equalTo(100))
                    .spec(ResponseSpecs.defaultResponseSpec()).extract().response();
            System.out.println(rs.jsonPath().getString("detail.premium"));




        /// ///////    Create Quote       //////////////
        given().spec(RequestSpecs.getSpecWithAuth()).body(payload1)
                .when().post(APIEndpoints.getQuoteList())
                .then().spec(ResponseSpecs.createdSpec());


    }

    @Test(groups = {"post"})
    public void CreateQuotesByGivenInput() throws IOException {
        String payload1 = getPayloadFromConfig("quote_create_json");
        String payload2 = getPayloadFromConfig("create_location_json");

        Response rs = given().spec(RequestSpecs.getSpecWithAuth()).body(payload2)
                .when().post(APIEndpoints.getLocation())
                .then().body("detail[0].fees", equalTo(50))
                .body("detail[0].water_sewer_amt", equalTo(25.0F))
                .body("detail[0].taxes_details.sl_tax", equalTo(100))
                .spec(ResponseSpecs.defaultResponseSpec()).extract().response();
        System.out.println(rs.jsonPath().getString("detail.premium"));



        /// ///////    Create Quote       //////////////
        given().spec(RequestSpecs.getSpecWithAuth()).body(payload1)
                .when().post(APIEndpoints.getQuoteList())
                .then().spec(ResponseSpecs.createdSpec());


    }

}
