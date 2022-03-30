package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.user.Delete;

public class DeleteSteps {
    @Steps
    Delete delete;

    @Given("admin success auth using new user")
    public void adminSuccessAuthUsingNewUser() {
        delete.adminSuccessAuthUsingNewUser();
    }
    @When("admin set endpoint for delete existing user")
    public void adminSetEndpointForDeleteExistingUser() {
        delete.adminSetEndpointForDeleteExistingUser();
    }
    @And("admin send delete endpoint")
    public void adminSendDeleteEndpoint() {
        delete.adminSendDeleteEndpoint();
    }
    @Then("admin received HTTP response {int}")
    public void adminReceiverHTTPResponse(Integer statusCode) {
        delete.validateResponseCode(statusCode);
    }
}
