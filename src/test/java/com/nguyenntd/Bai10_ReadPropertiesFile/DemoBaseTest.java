package com.nguyenntd.Bai10_ReadPropertiesFile;

import com.google.gson.Gson;
import com.nguyenntd.globals.ConfigsGlobal;
import com.nguyenntd.globals.TokenGlobal;
import com.nguyenntd.model.RegisterUserPOJO_Lombok;
import com.nguyenntd.model.data.UserPOJO_Lombok_Builder;
import common.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoBaseTest  extends BaseTest {
    @Test
    public void testUpdateUser_PATCH() {

        RegisterUserPOJO_Lombok registerUserPOJO_lombok = UserPOJO_Lombok_Builder.getUserData();

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJO_lombok));

        Response response = request.when().patch("/user/720");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
