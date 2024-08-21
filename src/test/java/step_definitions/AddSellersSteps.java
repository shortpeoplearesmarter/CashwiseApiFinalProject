package step_definitions;

import com.github.javafaker.Faker;
import entities.RequestBody;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import utilities.Config;

import java.util.Arrays;

public class AddSellersSteps {
    Faker faker = new Faker();
    RequestSpecification request;
    RequestBody requestBody = new RequestBody();
    Response response;

    private static final Logger logger =
            LogManager.getLogger(AddSellersSteps.class);

    @Given("base url")
    public void base_url() {
        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(Config.getProperty("cashwiseApiUrl"));

    }
    @When("user provides valid authorization token")
    public void user_provides_valid_authorization_token() {
        request = request.auth().oauth2(Config.getProperty("cashwiseToken"));


    }

    @And("user provides title")
    public void user_provides_title_name() {
        requestBody.setCompany_name(faker.name().title());

    }
    @And("user provides seller's full name")
    public void user_provides_seller_s_full_name() {
        requestBody.setSeller_name(faker.name().fullName());

    }
    @And("user provides email")
    public void user_provides_email() {
        requestBody.setEmail(faker.internet().emailAddress());

    }
    @And("user provides phone number")
    public void user_provides_phone_number() {
        requestBody.setPhone_number(faker.phoneNumber().phoneNumber());

    }
    @And("user provides address")
    public void user_provides_address() {
        requestBody.setAddress(faker.address().fullAddress());

    }

    @And("user hits POST endpoint {string}")
    public void userHitsPOSTendpoint(String endpoint) {
        response = request.body(requestBody).post(endpoint);

    }

    @Then("user verifies status code is {int}")
    public void user_verifies_status_code_is(Integer statusCode) {
        Assert.assertEquals((int)statusCode,response.getStatusCode());
        logger.info("User successfully created a seller");

    }

}

