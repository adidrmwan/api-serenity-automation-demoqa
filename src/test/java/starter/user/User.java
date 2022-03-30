package starter.user;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class User {
    protected String baseUrl = "https://reqres.in/api/";

    @Step("user set API GET endpoints")
    public String userSetGet(){
        return baseUrl + "users?page=2";
    }

    @Step("user send GET HTTP request")
    public void userSendGET(){
        SerenityRest.given().get(userSetGet());
    }

    @Step("user receive valid HTTP response code")
    public void validateResponseCode(){
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("user receive valid data for detail user")
    public void validateData(){
        Response response = SerenityRest.lastResponse();
        int id = response.getBody().jsonPath().get("data.id[0]");
        String first_name = response.getBody().jsonPath().get("data.first_name[0]");
        String email = response.getBody().jsonPath().get("data.email[1]");
        Assert.assertEquals(id,7);
        Assert.assertEquals(first_name, "Michael");
        Assert.assertEquals(email, "lindsay.ferguson@reqres.in");
    }
}
