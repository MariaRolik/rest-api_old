package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;
import static helpers.CustomAllureListener.withCustomTemplates;

public class BasicSpec {
    public static RequestSpecification basicRequestSpec = with()
            .filter(withCustomTemplates())
            .log().method()
            .log().uri()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification created201ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(BODY)
            .build();

    public static ResponseSpecification ok200ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(BODY)
            .build();
}
