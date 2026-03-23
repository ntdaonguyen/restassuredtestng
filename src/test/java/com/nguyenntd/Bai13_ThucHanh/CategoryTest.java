package com.nguyenntd.Bai13_ThucHanh;

import com.nguyenntd.globals.TokenGlobal;
import com.nguyenntd.helpers.JsonHelper;
import common.BaseTest;
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

public class CategoryTest extends BaseTest {

    int CATEGORY_ID;

    @Test (priority = 1)
    public void testAddNewCategory() {
        System.out.println("Create Category");

      //  String fileData = "src/test/resources/testdata/CreateCategory.json";
        File fileData = new File("src/test/resources/testdata/CreateCategory.json");
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(fileData);
       // .body(new File(fileData))

        Response response = request.post("/category");
        response.prettyPrint();
        response.then().statusCode(200);

//      JsonPath jsonPath = response.jsonPath();
//      CATEGORY_ID = Integer.parseInt(jsonPath.get("response.id").toString());

        CATEGORY_ID = Integer.parseInt(response.path("response.id").toString());
    }

    @Test (priority = 2)
    public void getCategoryById() {
        System.out.println("Get Category By Id");

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        Response response = request.get("/category/" + CATEGORY_ID);
        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("response.name"), "Test Category 25");
    }

    @Test (priority = 3)
    public void testUpdateCategory() {
        System.out.println("Update Category");

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body("{\n" +
                        "  \"name\": \"Tester Category 55\"\n" +
                        "}\n");

        Response response = request.put("/category/" + CATEGORY_ID);
        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("response.name"), "Test Category 55");
    }

    @Test (priority = 4)
    public void testDeleteCategory() {
        System.out.println("Delete Category");

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        Response response = request.delete("/category/" + CATEGORY_ID);
        response.prettyPrint();
        response.then().statusCode(200);
    }

}
