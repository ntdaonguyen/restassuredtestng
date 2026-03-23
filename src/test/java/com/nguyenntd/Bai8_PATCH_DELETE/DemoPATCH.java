package com.nguyenntd.Bai8_PATCH_DELETE;

import com.google.gson.Gson;
import com.nguyenntd.globals.TokenGlobal;
import com.nguyenntd.model.LoginPOJO;
import com.nguyenntd.model.PatchUserPOJO;
import com.nguyenntd.model.RegisterUserPOJO_Lombok;
import common.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoPATCH extends BaseTest {
    @Test
    public void testUpdateUser_PATCH() {

        Faker faker = new Faker(new Locale("en"));

        String phoneNumber = faker.phoneNumber().phoneNumber();
        System.out.println(phoneNumber);

        //Chuẩn bị data cho edit user
        RegisterUserPOJO_Lombok registerUserPOJOLombok = new RegisterUserPOJO_Lombok();
        registerUserPOJOLombok.setFirstName(faker.name().firstName());
        registerUserPOJOLombok.setLastName(faker.name().lastName());
        registerUserPOJOLombok.setEmail(faker.internet().emailAddress());
        registerUserPOJOLombok.setPhone(phoneNumber);
        registerUserPOJOLombok.setUserStatus(1);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJOLombok));

        Response response = request.when().patch("/user/23910");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }

}
