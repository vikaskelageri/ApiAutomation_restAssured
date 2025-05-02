package test;


import listeners.ExtentTestListener;
import org.testng.Reporter;
import org.testng.annotations.*;
import io.restassured.response.Response;
import base.BaseTest;
import utils.APIEndpoints;
import utils.TokenManagement;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Listeners(ExtentTestListener.class)
public class AgentAPI extends BaseTest {



    @Test(priority = 1, groups = "get")
    public void getAgent() {


        Response response = given()
                .header("Authorization", "Bearer "+ TokenManagement.getAccessToken())
                .get(APIEndpoints.getAgentList())
                .then()
                .statusCode(200)
                .extract().response();

        Reporter.getCurrentTestResult().setAttribute("response", response);

        System.out.println("Successful: " + response.asString());

    }

    @Test(priority = 2, groups = "Create", enabled = false)
    public void testCreateAgent() {
        String payload = "";
        try {
            payload = new String(Files.readAllBytes(Paths.get("src/test/resources/payloads/AgentPayload.json")));
        } catch (IOException e) {
            System.err.println("Error reading AgentPayload.json: " + e.getMessage());
            org.testng.Assert.fail("Failed to read AgentPayload.json file.");
            return;
        }

        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(APIEndpoints.createAgent())
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        Reporter.getCurrentTestResult().setAttribute("response", response);

        String agentId;
        if (statusCode != 201) {
            System.err.println("❌ Test Failed with status code: " + statusCode);
            System.err.println("🔽 Response body:");
            System.err.println(response.asString());

            org.testng.Assert.fail("Expected status code 201, but got: " + statusCode);
        } else {
            System.out.println("✅ Test Passed: " + response.asString());
            agentId = response.jsonPath().getString("id");
        }
        agentId = response.jsonPath().getString("id");
    }

    @Test(priority = 3, groups = "Update")
    public void CPI_UpdateAgent_() {

        String payload = "";
        try {
            payload = new String(Files.readAllBytes(Paths.get("src/test/resources/payloads/Agent_update.json")));
        } catch (IOException e) {
            System.err.println("Error reading AgentPayload.json: " + e.getMessage());
            org.testng.Assert.fail("Failed to read AgentPayload.json file.");
            return;
        }

        Response res=given()
                .header("Authorization", "Bearer accessToken")
                .header("Content-Type", "application/json")
                .body(payload)
                .post(APIEndpoints.updateAgent(241))
                .then().log().all().extract().response();
        int statuscode=res.getStatusCode();
        if(statuscode!=200){

            System.out.println(statuscode+"     response:  "+res.asString());
        }



    }
    @Test(priority = 4, groups = "Delete")
    public void DeleteAgent() {

        Response response=given()
                .header("Authorization", "Bearer accessToken")
                .get(APIEndpoints.getAgentList())
                .then().statusCode(200).extract().response();

        String name=response.jsonPath().getString("detail.agents[0].first_name");
        String address=response.jsonPath().getString("detail.agents[1].physical_address.address");
        System.out.println("name: "+name+" address: "+address);

        Response res=given()
                 .header("Authorization", "Bearer accessToken")
                .delete("/api/v1/user/113/manage")
                .then().extract().response();

            int statuscode=res.getStatusCode();

           if(res.jsonPath().getString("detail").equalsIgnoreCase("User Deleted Successfully")){
               System.out.println("Message: "+res.jsonPath().getString("detail"));
           }
            if(statuscode!=200) {
                System.out.println("Failed: "+res.asString());
            }
            else{
                System.out.println("Deleted : "+res.asString() +" name:"+name +" address:"+address);
            }


    }


}
