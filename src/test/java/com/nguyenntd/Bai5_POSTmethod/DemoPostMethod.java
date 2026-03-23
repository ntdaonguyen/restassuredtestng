package com.nguyenntd.Bai5_POSTmethod;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoPostMethod {

    @Test
    public void testRegisterUser() {
        String username = "nguyenntd30";
        String firstName = "Nguyen";
        String lastName = "NTD";
        String email = "nguyenntd30@email.com";
        String password = "Demo@123";
        String phone = "0123456789";
        int userStatus = 1;

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"firstName\": \"" + firstName + "\",\n" +
                        "  \"lastName\": \"" + lastName + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "  \"phone\": \"" + phone + "\",\n" +
                        "  \"userStatus\": " +  userStatus + "\n" +
                        "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);

        //Verify response
        response.then().contentType(ContentType.JSON);

        //Cách 1
        ResponseBody responseBody = response.getBody();

        //Cách 2 (Ưu tiên)
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("response.username"), username, "The username not match");
        Assert.assertEquals(responseBody.path("response.firstName"), firstName, "The first name not match");
        Assert.assertEquals(responseBody.path("response.lastName"), lastName, "The last name not match");
        Assert.assertEquals(responseBody.path("response.email"), email, "The email not match");
        // Assert.assertEquals(responseBody.path("response.password"), password, "The password not match");
        Assert.assertEquals(responseBody.path("response.phone"), phone, "The phone not match");
        Assert.assertEquals(Integer.parseInt(responseBody.path("response.userStatus").toString()), userStatus);
        Assert.assertTrue(responseBody.path("response.id").toString().length() > 0, "The ID is not existing");

    }


    @Test
    public void testLoginUser() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"username\": \"nguyenntd\",\n" +
                        "  \"password\": \"Demo@123\",\n" +
                        "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/login");
        response.prettyPrint();

    }
}
