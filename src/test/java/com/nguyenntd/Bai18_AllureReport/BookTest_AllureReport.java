package com.nguyenntd.Bai18_AllureReport;

import com.nguyenntd.globals.EndPointGlobal;
import com.nguyenntd.helpers.JsonHelper;
import com.nguyenntd.keywords.ApiKeyword;
import com.nguyenntd.listeners.TestListener;
import com.nguyenntd.utils.LogUtils;
import common.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Locale;

@Listeners(TestListener.class)
public class BookTest_AllureReport extends BaseTest {

    @Test(priority = 1)
    @Severity(SeverityLevel.MINOR)
    @Epic("Regression Test")
    @Feature("Book Test")
    @Story("Test Get Book")
    @Description("Get All Books")
    @Attachment
    @Link("https://jira.com/nguyenntd")
    public void testGetBooks() {
        Response response = ApiKeyword.get(EndPointGlobal.EP_BOOKS);
        ApiKeyword.verifyStatusCode(response, 200);
        LogUtils.info(ApiKeyword.getResponseKeyValue(response, "response[0].name"));
    }
}
