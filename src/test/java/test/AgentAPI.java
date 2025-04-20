package test;


import listeners.ExtentTestListener;
import org.testng.Reporter;
import org.testng.annotations.*;
import io.restassured.response.Response;
import test.base.BaseTest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners(ExtentTestListener.class)
public class AgentAPI extends BaseTest {
    public static String agentId;


    @Test(priority = 1, groups = "Get")
    public void getAgent() {


        Response response = given()
                .header("Content-Type", "application/json")
                .get("https://cpiai-dev.azurewebsites.net/api/v1/agent/list")
                .then()
                .statusCode(200)
                .extract().response();

        Reporter.getCurrentTestResult().setAttribute("response", response);

        System.out.println("Successful: " + response.asString());

    }

    @Test(priority = 2, groups = "Create")
    public void testCreateAgent() {
        String payload = "";
        try {
            payload = new String(Files.readAllBytes(Paths.get("src/test/resourses/payloads/AgentPayload.json")));
        } catch (IOException e) {
            System.err.println("Error reading AgentPayload.json: " + e.getMessage());
            org.testng.Assert.fail("Failed to read AgentPayload.json file.");
            return;
        }

        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/api/v1/user/signup")
                .then()
                .statusCode(201)
                .extract().response();

        Reporter.getCurrentTestResult().setAttribute("response", response);

        System.out.println("Successful: " + response.asString());
        agentId = response.jsonPath().getString("id");
    }

    @Test(priority = 3, groups = "Update")
    public void CPI_UpdateAgent_() {

        String payload = "";
        try {
            payload = new String(Files.readAllBytes(Paths.get("src/test/resourses/payloads/Agent_update.json")));
        } catch (IOException e) {
            System.err.println("Error reading AgentPayload.json: " + e.getMessage());
            org.testng.Assert.fail("Failed to read AgentPayload.json file.");
            return;
        }

        Response res=given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzQ3MzIxMTg5LCJpYXQiOjE3NDQ3MjkxODksImp0aSI6IjRmZGNkNTFjZGI3MTQ2YzVhZDhjNTUzMDYwZWVkODA3IiwidXNlcl9pZCI6ODl9.6cDhidmoHm1ZO77KbAtCWIc6HNYRgoAdIGToCD28y1o")
                .body(payload)
                .post("/api/v1/user/111/manage")
                .then().extract().response();
        System.out.println(res.asString());


    }
}
