package com.nguyenntd.Bai3_SendRequest_GETmethod;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoGivenWhenThen {

    @Test
    public void testGetUsers() {
        System.out.println("testGetUsers");

        given().baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .basePath("/users")
                .when().get()
                .then().statusCode(200);

    }
}
