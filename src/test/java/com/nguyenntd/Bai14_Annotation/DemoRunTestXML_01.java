package com.nguyenntd.Bai14_Annotation;

import com.nguyenntd.globals.ConfigsGlobal;
import com.nguyenntd.globals.TokenGlobal;
import com.nguyenntd.helpers.JsonHelper;
import com.nguyenntd.model.LoginPOJO;
import com.nguyenntd.model.LoginPOJO_Lombok;
import com.nguyenntd.model.data.LoginPOJO_Builder;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoRunTestXML_01 {
    @Test(priority = 0)
    public void loginUser() {
        LoginPOJO_Lombok loginPOJO = LoginPOJO_Builder.getDataLogin();

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        response.then().statusCode(200);

        TokenGlobal.TOKEN = response.getBody().path("token");
        System.out.println("Token Global: " + TokenGlobal.TOKEN);
    }

    public static int CATEGORY_ID;
    public static String CATEGORY_NAME;

    @Test(priority = 1)
    public void testAddNewCategory() {
        System.out.println("Create Category");
        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        Faker faker = new Faker(new Locale("vi"));
        CATEGORY_NAME = faker.job().title();
        System.out.println("CATEGORY_NAME: " + CATEGORY_NAME);

        JsonHelper.updateValueJsonFile(dataFile, "name", CATEGORY_NAME);

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(dataFile));

        Response response = request.post("/category");

        response.prettyPrint();
        response.then().statusCode(200);

        CATEGORY_ID = Integer.parseInt(response.path("response.id").toString());
        System.out.println("CATEGORY_ID: " + CATEGORY_ID);
    }

    @Test(priority = 2)
    public void testGetCategoryById() {
        System.out.println("Get Category By Id");

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        System.out.println("CATEGORY_ID: " + CATEGORY_ID);
        Response response = request.get("/category/" + CATEGORY_ID);

        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("response.name"), CATEGORY_NAME, "Category name not match.");
    }
}