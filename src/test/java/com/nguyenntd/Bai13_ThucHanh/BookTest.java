package com.nguyenntd.Bai13_ThucHanh;

import com.google.gson.Gson;
import com.nguyenntd.globals.ConfigsGlobal;
import com.nguyenntd.globals.TokenGlobal;
import com.nguyenntd.helpers.JsonHelper;
import com.nguyenntd.model.data.BookPOJO;
import common.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class BookTest extends BaseTest {

    int CATEGORY_ID;
    @Test (priority = 1)
    public void testAddNewCategory() {
        System.out.println("Create Category");
        String fileData = "src/test/resources/testdata/CreateCategory.json";
        // File fileData = new File("src/test/resources/testdata/CreateCategory.json");
        Faker faker = new Faker(new Locale("vi"));

        JsonHelper.updateValueJsonFile(fileData,"name", faker.job().title());
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(fileData));
//                .body(fileData);


        Response response = request.post("/category");
        response.prettyPrint();
        response.then().statusCode(200);

//      JsonPath jsonPath = response.jsonPath();
//      CATEGORY_ID = Integer.parseInt(jsonPath.get("response.id").toString());

        CATEGORY_ID = Integer.parseInt(response.path("response.id").toString());
    }


    @Test (priority = 2)
    public void testAddNewBook() {
        Faker faker = new Faker(new Locale("vi"));

        BookPOJO bookPOJO = new BookPOJO();
        bookPOJO.setName(faker.book().title());
        bookPOJO.setCategory_id(CATEGORY_ID);
        bookPOJO.setPrice(120000);
        bookPOJO.setRelease_date("2025-12-12");
        bookPOJO.setStatus(true);

//        ArrayList<Integer> imagesID = new ArrayList<>();
//        imagesID.add(1);
//        imagesID.add(2);
//        bookPOJO.setImage_ids(imagesID);

        bookPOJO.setImage_ids(new ArrayList<>(Arrays.asList(27, 28)));

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(bookPOJO));

        Response response = request.post("/book");
        response.prettyPrint();

        response.then().statusCode(200);
    }
}
