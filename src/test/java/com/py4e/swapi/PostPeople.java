package com.py4e.swapi;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PostPeople extends Base {

    @Test
    public void postPerson() {
        given()
                .spec(reqSpec)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

}
