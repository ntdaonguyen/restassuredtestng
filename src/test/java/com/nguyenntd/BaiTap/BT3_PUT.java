package com.nguyenntd.BaiTap;

import com.google.gson.Gson;
import com.nguyenntd.model.BookingBody;
import com.nguyenntd.model.BookingDates;
import common.VerifyDataUserBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BT3_PUT {
    String TOKEN =  "";

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
    public void testUpdateBooking() {

        int id = 198;
        BookingDates bookingDates = new BookingDates();

        bookingDates.setCheckin("2026-12-12");
        bookingDates.setCheckout("2026-12-18");
        BookingBody bookingBody = new BookingBody();
        bookingBody.setFirstname("Jenny");
        bookingBody.setLastname("Vayne");
        bookingBody.setBookingdates(bookingDates);
        bookingBody.setDepositpaid(true);
        bookingBody.setTotalprice(20000);
        bookingBody.setAdditionalneeds("Brunch");

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com")
                .accept("application/json")
                .contentType("application/json")
                .cookie("token", TOKEN)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(gson.toJson(bookingBody));

        Response response = request.when().put("/booking/" + id);
        response.prettyPrint();

        response.then().statusCode(200);


    }
}
