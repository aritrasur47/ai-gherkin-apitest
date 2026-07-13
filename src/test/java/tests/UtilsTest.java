package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.AddPlace;
import testdata.TestDataBuild;
import utils.Utils;

public class UtilsTest {

	private final Utils utils = new Utils();

	@Test
	public void addPlaceThenDeletePlace() {
		AddPlace addPlace = TestDataBuild.buildAddPlace();
		addPlace.setName("UtilsTest Place");

		Response addResponse = utils.addPlace(addPlace);
		Assert.assertEquals(addResponse.jsonPath().getString("status"), "OK");

		String placeId = addResponse.jsonPath().getString("place_id");
		Assert.assertNotNull(placeId);

		Response deleteResponse = utils.deletePlace(placeId, addPlace.getName());
		Assert.assertEquals(deleteResponse.jsonPath().getString("status"), "OK");
	}

}
