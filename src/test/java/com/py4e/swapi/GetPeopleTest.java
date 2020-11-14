package com.py4e.swapi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

public class GetPeopleTest extends BaseTest {

    @Test
    public void getAllPeople() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(PEOPLE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getList("results.name")).hasSize(10);
        assertThat(json.getInt("count")).isEqualTo(87);
    }

    @Test
    public void getOnePerson() {
        Response response = given()
                .spec(reqSpec)
                .pathParam("personId", 1)
                .when()
                .get(PEOPLE + "{personId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getString("name")).isEqualTo("Luke Skywalker");
        assertThat(json.getString("gender")).isEqualTo("male");
        assertThat(json.getList("films")).containsExactly(BASE_URL + FILMS + "1/", BASE_URL + FILMS + "2/", BASE_URL + FILMS + "3/", BASE_URL + FILMS + "6/", BASE_URL + FILMS + "7/");
        assertThat(json.getString("url")).isEqualTo(BASE_URL + PEOPLE + "1/");
    }

    @Test
    public void getPersonInWookie() {
        Response response = given()
                .spec(reqSpec)
                .pathParam("personId", 1)
                .queryParam("format", "wookiee")
                .when()
                .get(PEOPLE  + "{personId}/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getString("whrascwo")).isEqualTo("Lhuorwo Sorroohraanorworc");
        assertThat(json.getString("rhahrcaoac_roworarc")).isEqualTo("19BBY");
    }

    @Test
    public void searchForPerson() {
        Response response = given()
                .spec(reqSpec)
                .queryParam("search", "Luke")
                .when()
                .get(PEOPLE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getList("results.name").get(0)).isEqualTo("Luke Skywalker");
        assertThat(json.getInt("count")).isEqualTo(1);
    }
}