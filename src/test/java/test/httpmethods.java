package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.base.BaseTest;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class httpmethods extends BaseTest {

//        @Test
//        public void testGetUser() {
//            test = extent.createTest("Validate GET /users/1 API");
//
//            // Send API Request
//            Response response = given()
//                    .when()
//                    .get("/users/1");
//
//            // Log response
//            test.info("Response: " + response.prettyPrint());
//
//            // Validate response
//            Assert.assertEquals(response.statusCode(), 200);
//            Assert.assertTrue(response.body().asString().contains("Leanne Graham"));
//
//            test.pass("Test Passed: GET /users/1 validated successfully");
//        }
//    @Test
//         public void testCreateUser() {
//                // Define request body
//                String requestBody = "{ \"name\": \"Vikas\", \"job\": \"QA Engineer\" }";
//
//                // Sending POST request
//                Response response = given()
//                        .contentType(ContentType.JSON)
//                        .body(requestBody)
//                        .when()
//                        .post("https://reqres.in/api/users")
//                        .then()
//                        .statusCode(201)  // Verify status code
//                        .extract().response();
//                System.out.println(response.statusCode());
//                String name=response.jsonPath().getString("name");
//
//
//                Assert.assertEquals(name, "Vikas", "Name mismatch!");

    /// /                Assert.assertEquals(job, "QA Engineer", "Job mismatch!");
//            }
//
//            @Test
//            public void senddata(){
//
//            String body="{ \"name\": \"Vikas\", \"job\": \"QA Engineer\" }";
//            Response response=given().contentType(ContentType.JSON).body(body).when().post("https://reqres.in/api/users")
//                    .then().statusCode(201).extract().response();
//            System.out.println(response.jsonPath().getString("job"));
//
//
//            }
//    @Test
//    public void uploadFile() {
//        File fileToUpload = new File("src/test/resourses/sample.txt");
//
//        Response response =given()
//                .multiPart("file", fileToUpload)
//                .contentType(ContentType.MULTIPART)
//                .when()
//                .post("https://fakeapi.platzi.com/api/v1/files/upload")
//                .then()
//                .statusCode(200).extract().response(); // Adjust based on the expected status code
//    }
//    @Test
//    public void handlingJson() {
//
//        File file=new File("src/test/resourses/simplejson2.json");
//        JsonPath jsonpath=new JsonPath(file);
//        String name=jsonpath.getString("data.users[0].name");
//String phno=jsonpath.getString(("data.users[1].contacts.phone"));
//
//        System.out.println("name: "+name +" phone number: "+phno);
//    }
//    @Test
//    public void handlingJson2() {

//        File file=new File("src/test/resourses/simplejson2.json");
//        JsonPath jsonpath=new JsonPath(file);
//
//        List<String> alldata=jsonpath.getList(("data.users.name"));
//        for(int i=0;i<alldata.size();i++){
//            System.out.println("List: "+alldata.get(i));
//        }
//
//        System.out.println("List: "+alldata );

//        File file = new File("src/test/resourses/simplejson2.json");
//        JsonPath jsonPath = new JsonPath(file);
//        Map<String, String> data = jsonPath.getMap(("data.users[1].contacts"));
//        for (int i = 0; i < data.size(); i++) {
//            System.out.println("List: " + data.get("email"));
//        }
//
//        File file2 = new File("src/test/resourses/simplejson2.json");
//        JsonPath jsonPath1 = new JsonPath(file2);
//        Map<String, String> data2 = jsonPath.getMap(("data.users[1].contacts"));
//
//
//    }

//        @Test
//         public void testCreateUser() {
//                // Define request body
//                String requestBody = "{ \"name\": \"Vikas\", \"job\": \"QA Engineer\" }";
//
//                // Sending POST request
//            RequestSpecification httpRequest = RestAssured.given();
//            Response response = httpRequest.get();
//            // Get all the headers and then iterate over allHeaders to print each header
//            Headers allHeaders = response.headers();
//            response.getHeader("Content-Type");
//            Assert.assertEquals(response.getHeader("Content-Type"),"application/json; charset=utf-8");
//
//
//            // Iterate over all the Headers
//           for(Header header : allHeaders) {
//                System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
//            }
//
//            System.out.println("Key: " +response.getHeader("Content-Type"));
//
//        }
//
    @Test()
    public void buleey() {
        RestAssured.baseURI = "https://dev-dot-bulley-boss-v4.uc.r.appspot.com";
        Response response = given().get("/api/v1/job-id-name/?googleUser=vikas.kelageri@attri.ai");
        System.out.println(response.getBody().asString());

        JsonPath jsonPath = response.jsonPath();

        // Validate overall response structure
        assertEquals(jsonPath.getString("status"), "success");
        assertEquals(jsonPath.getString("message"), "Jobs fetched successfully");

        // Validate first job's details
        assertEquals(jsonPath.getInt("data[0].id"), 1228);
//        assertEquals(jsonPath.getString("data[0].job_name"), "Test Job");
//        assertEquals(jsonPath.getInt("data[0].job_number"), 111);
//        assertEquals(jsonPath.getString("data[0].address"), "123 Test Street");
        assertEquals(jsonPath.getString("data[3].job_number"), "114");
        System.out.println(jsonPath.getString("data[3].job_number"));

        // Validate that all jobs have a non-null job_name
        for (int i = 0; i < jsonPath.getList("data").size(); i++) {
            assertTrue(jsonPath.getString("data[" + i + "].job_name") != null, "Job name is null at index " + i);
        }


        List<String> alldata = jsonPath.getList("data.address");

        System.out.println(alldata);

    }

    @Test
    public void buleeyupdate() {
        RestAssured.baseURI = "https://dev-dot-bulley-boss-v4.uc.r.appspot.com";
        String requestBody = "{\n" +
                "  \"job_name\": \"Ttest est Job\",\n" +
                "  \"job_number\": 119,\n" +
                "  \"address\": \"12345 testing\",\n" +  // Fixed missing closing quote
                "  \"payroll_admin_id\": 112,\n" +
                "  \"inactive_users\": false\n" +
                "}";
        Response response = given().contentType(ContentType.JSON).body(requestBody)
                .post("/api/v1/job-id-name/?googleUser=vikas.kelageri@attri.ai");

        JsonPath jsonPath = response.jsonPath();


    }

    @Test
    public void buleeyupdate2() {
        RestAssured.baseURI = "https://dev-dot-bulley-boss-v4.uc.r.appspot.com";
        Response response = given().get("/api/v1/user/?page=1&search=vk767613212@gmail.com")
                .then().statusCode(200)
                .body("status", equalTo("success"))
                .body("data.results[0].first_name", equalTo("vikas_test"))

                .body("message", equalTo("User profiles fetched successfully")).extract().response();
        String kl = response.asString();
        System.out.println(kl);
        String df = response.getHeader("content-type");
        response.getTime();
        List<String> ll = response.jsonPath().getList("data.results");

        Assert.assertEquals("application/json", "application/json");
        System.out.println(ll);
    }


    @Test
    public void handlingJson() {
        File file = new File("src/test/resourses/simplejson.json");
        JsonPath jsonpath = new JsonPath(file);
        List<String> jobname = jsonpath.getList("data.job_name");
        String[] pl = {"55 E Wacker", "Lasalle Wacker", "3cds"};
        for (int i = 0; i < pl.length; i++) {
            if (jobname.contains(pl[i])) {
                System.out.println(pl[i] + "  is present api! ");
            } else {
                System.out.println(pl[i] + "  Not Present :( ");
            }
        }


        System.out.println(jobname);
    }

    @Test
    public void CreatingJobbulleyboss() {
        RestAssured.baseURI = "https://dev-dot-bulley-boss-v4.uc.r.appspot.com";
        String payload = "{\n" +
                "\"job_number\": \"42001011\",\n" +
                "\"job_name\": \"test - bulleyb121\",\n" +
                "\"address\": \"texas\",\n" +
                "\"work_hours\": \"7:30 AM-4:00 PM\",\n" +
                "\"certified_payrole\": false,\n" +
                "\"close_time\": \"16:00:00\",\n" +
                "\"delivery_contact\": \"UserProfile-179\",\n" +
                "\"delivery_notes\": \"\",\n" +
                "\"division\": 1,\n" +
                "\"do_not_text\": false,\n" +
                "\"foreman\": \"UserProfile-177\",\n" +
                "\"four_day_week\": false,\n" +
                "\"manpower_notes\": \"\",\n" +
                "\"mbe_wbe\": \"\",\n" +
                "\"ordered_by\": [153],\n" +
                "\"out_of_town_union\": \"\",\n" +
                "\"payroll_admin\": 178,\n" +
                "\"payroll_location\": 1,\n" +
                "\"project_managers\": [153, 178],\n" +
                "\"start_time\": \"7:30:00\",\n" +
                "\"vaccination_required\": false\n" +
                "}";


        Response response = given().contentType("application/json").body(payload)
                .post("/api/v1/create-job/?googleUser=vk767613212%40gmail.com")
                .then().statusCode(201).extract().response();
        String kl = response.asString();
        System.out.println(response.getBody().asString());
        System.out.println(kl);

    }


    @Test
    public void CPI_createAgesnt() {

        RestAssured.baseURI = "https://cpiai-dev.azurewebsites.net";
        String payload = "{\n" +
                "  \"company_name\": \"vkss\",\n" +
                "  \"email\": \"vktest@gmail.com\",\n" +
                "  \"first_name\": \"vkv\",\n" +
                "  \"is_physical_mailing_same\": false,\n" +
                "  \"last_name\": \"kk\",\n" +
                "  \"mailing_address\": {\n" +
                "    \"address\": \"Texas 8 Beltway, Houston, TX\",\n" +
                "    \"state\": \"Texas\",\n" +
                "    \"city\": \"Houston\",\n" +
                "    \"postal_code\": \"89090\"\n" +
                "  },\n" +
                "  \"new_comm\": 12,\n" +
                "  \"phone\": \"876562789\",\n" +
                "  \"physical_address\": {\n" +
                "    \"address\": \"Texas 8 Beltway, Houston, TX\",\n" +
                "    \"state\": \"Texas\",\n" +
                "    \"city\": \"Houston\",\n" +
                "    \"postal_code\": \"48999\"\n" +
                "  },\n" +
                "  \"renewal_comm\": 12,\n" +
                "  \"role\": \"AGENT\"\n" +
                "}";


        Response response = given().contentType("application/json")
                .body(payload)
                .when().post("/api/v1/user/signup")
                .then().statusCode(201).extract().response();
        String p = response.asString();
        System.out.println(p);


    }

    @Test
    public void CPI_Verify_getAgesnt() {
        RestAssured.baseURI = "https://cpiai-dev.azurewebsites.net";

        Response response = given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzQ3MzIxMTg5LCJpYXQiOjE3NDQ3MjkxODksImp0aSI6IjRmZGNkNTFjZGI3MTQ2YzVhZDhjNTUzMDYwZWVkODA3IiwidXNlcl9pZCI6ODl9.6cDhidmoHm1ZO77KbAtCWIc6HNYRgoAdIGToCD28y1o")

                .get("/api/v1/agent/list?search=&is_deleted=false&page_size=1")
                .then()
                .body("detail.agents[0].first_name", equalTo("vkss"))
                .body("detail.agents[0].physical_address.city", equalTo("Houston"))
                .body("detail.agents[0].physical_address.city", equalTo("Houston"))
                .body("detail.agents[0].mailing_address.state", equalTo("Texas"))
                .body("total_pages", equalTo("17"))
                .body("total_records", equalTo("17"))
                .statusCode(200).extract().response();
        System.out.println(response.asString());

    }

    @Test
    public void CPI_verify_() {
        RestAssured.baseURI = "https://cpiai-dev.azurewebsites.net";

        Response response = given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzQ3MzIxMTg5LCJpYXQiOjE3NDQ3MjkxODksImp0aSI6IjRmZGNkNTFjZGI3MTQ2YzVhZDhjNTUzMDYwZWVkODA3IiwidXNlcl9pZCI6ODl9.6cDhidmoHm1ZO77KbAtCWIc6HNYRgoAdIGToCD28y1o")

                .get("/api/v1/agent/list?search=&is_deleted=false&page_size=1").then().extract().response();


    }

    @Test
    public void CPI_DeleteAgent_() {
        RestAssured.baseURI = "https://cpiai-dev.azurewebsites.net/";
        Response res = given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzQ3MzIxMTg5LCJpYXQiOjE3NDQ3MjkxODksImp0aSI6IjRmZGNkNTFjZGI3MTQ2YzVhZDhjNTUzMDYwZWVkODA3IiwidXNlcl9pZCI6ODl9.6cDhidmoHm1ZO77KbAtCWIc6HNYRgoAdIGToCD28y1o")
                .delete("/api/v1/quote/950/update").then()
                .statusCode(200).body("status", equalTo("success")).body("detail", equalTo("Quote Deleted Successfully")).extract().response();
        System.out.println(res.asString());

    }

    @Test
    public void CPI_UpdateAgent_() {

        RestAssured.baseURI = "https://cpiai-dev.azurewebsites.net/";
        Response res=given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzQ3MzIxMTg5LCJpYXQiOjE3NDQ3MjkxODksImp0aSI6IjRmZGNkNTFjZGI3MTQ2YzVhZDhjNTUzMDYwZWVkODA3IiwidXNlcl9pZCI6ODl9.6cDhidmoHm1ZO77KbAtCWIc6HNYRgoAdIGToCD28y1o")
                .patch("/api/v1/user/111/manage").then().extract().response();
        System.out.println(res.asString());


    }
}