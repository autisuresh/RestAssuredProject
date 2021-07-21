package com.testing;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@SuppressWarnings("unchecked")
public class TestOne {

    @Test
    void test_01_get() {
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        System.out.println(response.getTime());

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    void test_02_get() {
        given()
            .get("https://reqres.in/api/users?page=2")
            .then()
            .statusCode(200)
            .body("data.id[0]", equalTo(7))
        .log().all();
    }

    @Test
    void test_03_post() {
        JSONObject request = new JSONObject();
        request.put("name","Suresh");
        request.put("job", "QA Engineer");

        given()
            .body(request.toJSONString())
            .when()
            .post("https://reqres.in/api/users")
            .then()
            .statusCode(201);
    }

    @Test
    void test_04_put() {
        JSONObject request = new JSONObject();
        request.put("name","Suresh");
        request.put("job", "QA Engineer");

        given()
                .body(request.toJSONString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void test_05_patch() {
        JSONObject request = new JSONObject();
        request.put("name","Suresh");
        request.put("job", "QA Engineer");

        given()
                .body(request.toJSONString())
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void test_06_delete() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log().all();
    }

}
