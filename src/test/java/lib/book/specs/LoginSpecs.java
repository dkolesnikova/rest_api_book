package lib.book.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static lib.book.helpers.CustomApiListener.withCustomTemplates;

public class LoginSpecs {

    public ApiSteps apiSteps = new ApiSteps();


        public static RequestSpecification request = with()
                .log().uri()
                .log().headers()
                .log().body()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .baseUri("http://localhost:5000")
                .basePath("/api");

        public static ResponseSpecification response200 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        public static ResponseSpecification response201 = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    public static ResponseSpecification response400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();
        public static ResponseSpecification response404 = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }


