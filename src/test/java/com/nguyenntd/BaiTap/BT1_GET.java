package com.nguyenntd.BaiTap;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BT1_GET {
    @Test
    public void testGetBookById() {
        int id = 198;
        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com/")
                .accept("application/json")
                .when().get()
                .then().statusCode(200);

        Response response = request.when().get("/booking/" + id);
        response.prettyPrint();

        response.then().body("firstname", containsString("Josh"));
        response.then().body("lastname", containsString("Allen"));
        response.then().body("totalprice", equalTo(111));
        response.then().body("depositpaid", equalTo(true));
        response.then().body("bookingdates.checkin", containsString("2018-01-01"));
        response.then().body("bookingdates.checkout", containsString("2019-01-01"));
        response.then().body("additionalneeds", containsString("super bowls"));


    }

    @Test
    public void testGetAllBooks() {

        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com/")
                .accept("application/json")
                .when().get()
                .then().statusCode(200);

        Response response = request.when().get("/booking");
        response.prettyPrint();
    }
}
