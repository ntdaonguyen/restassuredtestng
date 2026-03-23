package com.nguyenntd.BaiTap;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BT2_POST {

    String TOKEN = "";
    @Test
    public void getToken() {
        String filePath = "src/test/resources/testdata/LoginBooking.json";

        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com")
                .accept("application/json")
                .contentType("application/json")
                .body(new File(filePath));
        Response response = request.when().post("/auth");
        response.prettyPrint();
        response.then().statusCode(200);

        TOKEN = response.getBody().path("token");
    }

    @Test
    public void testCreateBooking() {
        String filePath = "src/test/resources/testdata/CreateBooking.json";

        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com")
                .accept("application/json")
                .contentType("application/json")
                .body(new File(filePath));

        Response response = request.when().post("/booking");
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200);
        ResponseBody body = response.getBody();
        Assert.assertEquals(body.asString().contains("Jimmy"), true);
        Assert.assertEquals(body.asString().contains("Jhin"), true);
        Assert.assertEquals(body.asString().contains("1234"), true);
        Assert.assertEquals(body.asString().contains("true"), true);
        Assert.assertEquals(body.asString().contains("2025-01-01"), true);
        Assert.assertEquals(body.asString().contains("2026-01-01"), true);
        Assert.assertEquals(body.asString().contains("Sunset view"), true);



    }
}
