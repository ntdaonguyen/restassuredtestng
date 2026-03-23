package com.nguyenntd.Bai18_AllureReport;

import com.nguyenntd.globals.EndPointGlobal;
import com.nguyenntd.helpers.JsonHelper;
import com.nguyenntd.keywords.ApiKeyword;
import com.nguyenntd.listeners.TestListener;
import com.nguyenntd.utils.LogUtils;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Locale;

@Listeners(TestListener.class)
public class CategoryTest_AllureReport extends BaseTest {

    int CATEGORY_ID;
    String CATEGORY_NAME;

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void testAddNewCategory() {
        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        Faker faker = new Faker(new Locale("vi"));
        CATEGORY_NAME = faker.job().title();
        System.out.println("CATEGORY_NAME: " + CATEGORY_NAME);

        JsonHelper.updateValueJsonFile(dataFile, "name", CATEGORY_NAME);

        Response response = ApiKeyword.post(EndPointGlobal.EP_CATEGORY, new File(dataFile));

        response.then().statusCode(200);

        CATEGORY_ID = Integer.parseInt(ApiKeyword.getResponseKeyValue(response, "response.id"));
        CATEGORY_NAME = ApiKeyword.getResponseKeyValue(response, "response.name");

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        LogUtils.info("CATEGORY_NAME: " + CATEGORY_NAME);

    }

    @Test(priority = 2)
    public void getCategoryById() {

//        RequestSpecification request = given();
//        request.baseUri("https://api.anhtester.com/api")
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        Response response = ApiKeyword.get("/category/" + CATEGORY_ID);

        response.then().statusCode(200);

        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "response.name"), CATEGORY_NAME, "The Category Name not match.");

    }
}
