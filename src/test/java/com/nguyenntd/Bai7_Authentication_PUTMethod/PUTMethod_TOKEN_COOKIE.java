package com.nguyenntd.Bai7_Authentication_PUTMethod;

import com.google.gson.Gson;
import com.nguyenntd.model.LoginPOJO;
import com.nguyenntd.model.RegisterUserPOJO;
import common.VerifyDataUserBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PUTMethod_TOKEN_COOKIE {

    //Khai báo biến toàn tục TOKEN để lưu trữ từ hàm Login
    String TOKEN = "";

    @BeforeMethod
    public void loginUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        LoginPOJO loginPOJO = new LoginPOJO("nguyenntd30", "Demo@123");

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);

        //Lưu giá trị token vào biến TOKEN nhé
        TOKEN = response.getBody().path("token");
        System.out.println(TOKEN);
    }

    @Test
    public void testEditUser_HasAuth() {

        //Chuẩn bị data cho edit user
        RegisterUserPOJO registerUserPOJO = new RegisterUserPOJO();
        registerUserPOJO.setUsername("nguyenntd30");
        registerUserPOJO.setPassword("Demo@123");
        registerUserPOJO.setFirstName("Nguyennn");
        registerUserPOJO.setLastName("NTD");
        registerUserPOJO.setEmail("nguyenntd30@email.com");
        registerUserPOJO.setPhone("0123456789");
        registerUserPOJO.setUserStatus(1);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(gson.toJson(registerUserPOJO));

        Response response = request.when().put("/user/23910");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");

        VerifyDataUserBody.verifyDataUserBody(response, registerUserPOJO);
    }
}
