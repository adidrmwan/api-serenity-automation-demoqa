package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.user.User;

public class UserSteps {
    @Steps
    User user;

    @Given("user set API GET endpoints")
    public void userSetGet(){
        user.userSetGet();
    }
    @When("user send GET HTTP request")
    public void userSendGET(){
        user.userSendGET();
    }
    @Then("user receive valid HTTP response code")
    public void validateResponseCode(){
        user.validateResponseCode();
    }

    @And("user receive valid data for detail user")
    public void validateData(){
        user.validateData();
    }
}
