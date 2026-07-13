package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.AddPlace;
import testdata.TestDataBuild;
import utils.Utils;

public class StepDefinition {

	private final Utils utils = new Utils();
	private AddPlace addPlace;
	private Response response;
	private String placeId;

	@Given("Add place payload")
	public void add_place_payload() {
		addPlace = TestDataBuild.buildAddPlace();
	}

	@Given("Add place payload with name {string} and address {string}")
	public void add_place_payload_with_name_and_address(String name, String address) {
		add_place_payload();
		addPlace.setName(name);
		addPlace.setAddress(address);
	}

	@When("User calls {string} with Post https request")
	public void user_calls_with_post_https_request(String apiName) {
		switch (ApiEndpoint.fromStepValue(apiName)) {
			case ADD_PLACE_API:
				response = utils.addPlace(addPlace);
				placeId = response.jsonPath().getString("place_id");
				break;
			case DELETE_PLACE_API:
				response = utils.deletePlace(placeId, addPlace.getName());
				break;
		}
	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
		Assert.assertEquals(response.getStatusCode(), statusCode.intValue());
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String field, String expectedValue) {
		String responseBody = response.asString();
		JsonPath jsonPath = new JsonPath(responseBody);
		Assert.assertEquals(jsonPath.get(field).toString(), expectedValue);
	}

}
