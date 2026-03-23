package com.nguyenntd.Bai14_Annotation;

import com.nguyenntd.globals.ConfigsGlobal;
import com.nguyenntd.globals.TokenGlobal;
import com.nguyenntd.model.data.BookPOJO;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.joda.time.LocalDateTime;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoRunTestXML_02 {

    @Test
    public void testAddNewBook() {

        Faker faker = new Faker(new Locale("vi"));
        String BOOK_NAME = faker.book().title() + "_" + LocalDateTime.now();

        BookPOJO bookPOJO = new BookPOJO();

        bookPOJO.setName(BOOK_NAME);
        bookPOJO.setCategory_id(DemoRunTestXML_01.CATEGORY_ID);
        bookPOJO.setPrice(120000);
        bookPOJO.setRelease_date("2024-01-27");
        bookPOJO.setStatus(true);

        bookPOJO.setImage_ids(new ArrayList<>(Arrays.asList(27, 28)));

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(bookPOJO));

        Response response = request.post("book");
        response.prettyPrint();

        response.then().statusCode(200);

    }
}