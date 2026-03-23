package common;

import com.nguyenntd.model.RegisterUserPOJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerifyDataUserBody {
    public static void verifyDataUserBody(Response response, RegisterUserPOJO registerUserPOJO) {
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("response.username"), registerUserPOJO.getUsername(), "The username not match");
        Assert.assertEquals(jsonPath.get("response.firstName"), registerUserPOJO.getFirstName(), "The first name not match");
        Assert.assertEquals(jsonPath.get("response.lastName"), registerUserPOJO.getLastName(), "The last name not match");
        Assert.assertEquals(jsonPath.get("response.email"), registerUserPOJO.getEmail(), "The email not match");
        // Assert.assertEquals(responseBody.path("response.password"), registerUserPOJO.getPassword(), "The password not match");
        Assert.assertEquals(jsonPath.get("response.phone"), registerUserPOJO.getPhone(), "The phone not match");
        Assert.assertEquals(Integer.parseInt(jsonPath.get("response.userStatus").toString()), registerUserPOJO.getUserStatus());
        Assert.assertTrue(jsonPath.get("response.id").toString().length() > 0, "The ID is not existing");
    }
}
