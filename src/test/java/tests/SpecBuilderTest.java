package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.AddPlace;
import testdata.TestDataBuild;
import utils.Utils;

public class SpecBuilderTest {

	private final Utils utils = new Utils();

	@Test
	public void addPlaceReturnsSuccess() {
		AddPlace addPlace = TestDataBuild.buildAddPlace();
		addPlace.setName("SpecBuilderTest Place");

		Response response = utils.addPlace(addPlace);

		Assert.assertEquals(response.jsonPath().getString("status"), "OK");
		Assert.assertEquals(response.jsonPath().getString("scope"), "APP");
	}

}
