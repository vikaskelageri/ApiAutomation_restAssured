package utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class ResponseSpecs {

    public static ResponseSpecification defaultResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }

    public static ResponseSpecification createdSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType("application/json")
                .build();
    }

    public static ResponseSpecification unauthorizedSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectContentType("application/json")
                //.expectBody("detail", equalTo("Invalid token"))
                .build();
    }

    public static ResponseSpecification forbiddenSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(403)
                .expectContentType("application/json")
//                .expectBody("detail", containsString("Access denied"))
                .build();
    }

    public static ResponseSpecification badRequestSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectContentType("application/json")
//                .expectBody("reason", notNullValue())
                .build();
    }

    public static ResponseSpecification internalServerErrorSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .expectContentType("application/json")
                .expectBody("detail", containsString("Something went wrong"))
                .build();
    }
}
