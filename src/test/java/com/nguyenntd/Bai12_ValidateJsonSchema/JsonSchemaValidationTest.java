package com.nguyenntd.Bai12_ValidateJsonSchema;

import com.nguyenntd.globals.ConfigsGlobal;
import com.nguyenntd.helpers.SystemHelper;
import common.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class JsonSchemaValidationTest extends BaseTest {
    @Test
    public void validateJsonSchema_GetBookById() {
        InputStream GetBookIdSchema = getClass().getClassLoader()
                .getResourceAsStream("testdata/GetBookIdSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.URI)
                .when()
                .get("/book/1112")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetBookIdSchema));
    }

    @Test
    public void validateJsonSchema_GetBookAll() {
        //Để file ngoài cùng của thư mục resources
        InputStream GetBookAllSchema = getClass().getClassLoader()
                .getResourceAsStream("testdata/GetBookAllSchema.json");

        //Để file ở bất kỳ thư mục nào
        String filePath = SystemHelper.getCurrentDir() + "src/test/resources/testdata/GetBookAllSchema.json";

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.URI)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(filePath)));
    }
}