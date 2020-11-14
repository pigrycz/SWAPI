package com.py4e.swapi;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class Base {
    public static String BASE_URL =  "https://swapi.py4e.com/api/";
    public static String PEOPLE = "people/";
    public static String FILMS = "films/";
    public static String PLANETS  = "planets/";

    protected static RequestSpecBuilder reqBuilder;
    protected static RequestSpecification reqSpec;

    @BeforeAll
    public static void beforeAll(){
        reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(ContentType.JSON);
        reqBuilder.setBaseUri(BASE_URL);
        reqBuilder.addFilter(new AllureRestAssured());

        reqSpec = reqBuilder.build();
    }
}
