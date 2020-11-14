package com.py4e.swapi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

public class GetPlanets extends Base {

    @Test
    public void getAllPlanets() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(PLANETS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getString("count")).isEqualTo("61");
        assertThat(json.getList("results.name")).hasSize(10);
    }

    @Test
    public void getOnePlanet() {
        Response response = given()
                .spec(reqSpec)
                .pathParam("planetId", 1)
                .when()
                .get(PLANETS + "{planetId}/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        assertThat(json.getString("name")).isEqualTo("Tatooine");
        assertThat(json.getString("url")).isEqualTo(BASE_URL + PLANETS + "1/");
        assertThat(json.getString("surface_water")).isEqualTo("1");
    }
}
