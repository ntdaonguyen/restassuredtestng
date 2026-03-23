package common;

import com.google.gson.Gson;
import com.nguyenntd.globals.ConfigsGlobal;
import com.nguyenntd.globals.EndPointGlobal;
import com.nguyenntd.globals.TokenGlobal;
import com.nguyenntd.helpers.PropertiesHelper;
import com.nguyenntd.keywords.ApiKeyword;
import com.nguyenntd.model.LoginPOJO;
import com.nguyenntd.model.LoginPOJO_Lombok;
import com.nguyenntd.model.data.LoginPOJO_Builder;
import com.nguyenntd.reports.AllureManager;
import com.nguyenntd.utils.LogUtils;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeTest
    public void loginUser() {
        LogUtils.info("********LOGIN USER********");
        //LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        LoginPOJO_Lombok loginPOJO = LoginPOJO_Builder.getDataLogin();

        Gson gson = new Gson();

//        RequestSpecification request = given();
//        request.baseUri(ConfigsGlobal.BASE_URI)
//                .accept("application/json")
//                .contentType("application/json")
//                .body(gson.toJson(loginPOJO));
//
//        Response response = request.when().post("/login");

        Response response = ApiKeyword.postNotAuth(EndPointGlobal.EP_LOGIN, gson.toJson(loginPOJO));

        response.then().statusCode(200);

        TokenGlobal.TOKEN = ApiKeyword.getResponseKeyValue(response, "token");
        LogUtils.info("Token Global: " + TokenGlobal.TOKEN);
        AllureManager.saveTextLog("Token Global: " + TokenGlobal.TOKEN);
    }

}
