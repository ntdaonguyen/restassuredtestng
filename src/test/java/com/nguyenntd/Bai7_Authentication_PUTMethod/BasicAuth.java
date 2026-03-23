package com.nguyenntd.Bai7_Authentication_PUTMethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicAuth {

    @Test
    public void getData() {
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void testBasicAuth() {
        RequestSpecification httpRequest = given().auth().basic("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void testPreemptiveBasicAuth() {
        RequestSpecification httpRequest = given().auth().preemptive().basic("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void testDigestAuth() {
        RequestSpecification httpRequest = RestAssured.given().auth().digest("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
