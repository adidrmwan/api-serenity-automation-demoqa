package starter.user;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class Delete {
    protected String baseUrl = "https://demoqa.com/";
    protected static String name="";
    protected static String password="Password!2";
    protected static String token = "";
    protected static String userID = "";

    public String adminSuccessCreateNewUser(){
        Faker faker = new Faker();
        name = faker.name().username();
        SerenityRest.given().header("Content-Type", "application/json").body(setReqBodyDeleteUser().toJSONString()).post(baseUrl+"Account/v1/User");
        Response response = SerenityRest.lastResponse();
        return response.getBody().jsonPath().get("userID");
    }

    public JSONObject setReqBodyDeleteUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", name);
        requestBody.put("password", password);
        return requestBody;
    }

    @Step("admin success auth using new user")
    public void adminSuccessAuthUsingNewUser(){
        //create new user
        userID = adminSuccessCreateNewUser();
        //create token for new user
        SerenityRest.given().header("Content-Type", "application/json").body(setReqBodyDeleteUser().toJSONString()).post(baseUrl+"Account/v1/GenerateToken");
        Response resp = SerenityRest.lastResponse();
        token = resp.getBody().jsonPath().get("token");
        //validate authorization
        SerenityRest.given().header("Content-Type", "application/json").body(setReqBodyDeleteUser().toJSONString()).post(baseUrl+"Account/v1/Authorized");
        resp = SerenityRest.lastResponse();
        String respBody = resp.asString();
        Assert.assertEquals("true", respBody);
    }

    @Step("admin set endpoint for delete existing user")
    public String adminSetEndpointForDeleteExistingUser(){
        return baseUrl+"Account/v1/User/"+userID;
    }

    @Step("admin send delete endpoint")
    public void adminSendDeleteEndpoint(){
        SerenityRest.given().header("Authorization", "Bearer "+token).delete(adminSetEndpointForDeleteExistingUser());
    }

    @Step("admin received HTTP response {int}")
    public void validateResponseCode(int statusCode){
        Response response = SerenityRest.lastResponse();
        int actual = response.statusCode();
        Assert.assertEquals(statusCode, actual);
    }
}
