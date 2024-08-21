package step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SellerSteps {

    RequestSpecification request;
    Response response;
    Map<String, Object> params = new HashMap<>();
    CustomResponse customResponse;


    @Given("the base URL")
    public void theBaseURL() {
        request = RestAssured.given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                baseUri(Config.getProperty("cashwiseApiUrl"));
    }
    @Given("I provide a valid authorization token")
    public void i_provide_a_valid_authorization_token() {
        request = request.auth().oauth2(Config.getProperty("cashwiseToken"));
    }
    @Given("I store parameters in a hashmap")
    public void i_store_parameters_in_a_hashmap() {
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 100);
    }

    @When("I send a GET request to retrieve all sellers {string}")
    public void iSendAGETRequestToRetrieveAllSellers(String endpoint) {
        response = request.params(params).get(endpoint);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer int1) {
        int status = response.statusCode();
        Assert.assertEquals(200, status);
    }

    @And("I should receive a list of sellers and verify seller's email is not empty")
    public void iShouldReceiveAListOfSellersAndVerifySellerSEmailIsNotEmpty() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        int size = customResponse.getResponses().size();

        for (int i = 0; i < size; i++){
            String email = customResponse.getResponses().get(i).getEmail();
            response.prettyPrint();
            Assert.assertFalse(email.isEmpty());

        }
    }


}
