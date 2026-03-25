package com.nguyenntd.Bai17_Keyword;

import com.nguyenntd.globals.EndPointGlobal;
import com.nguyenntd.helpers.JsonHelper;
import com.nguyenntd.keywords.ApiKeyword;
import com.nguyenntd.listeners.TestListener;
import com.nguyenntd.utils.LogUtils;
import common.BaseTest;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Locale;

@Listeners(TestListener.class)
    public class BookTest_Keyword extends BaseTest {

        @Test
        public void testGetBooks() {
            Response reponse = ApiKeyword.get(EndPointGlobal.EP_BOOKS);
            ApiKeyword.verifyStatusCode(reponse, 200);
            LogUtils.info(ApiKeyword.getResponseKeyValue(reponse, "response[0].name"));
        }
}
