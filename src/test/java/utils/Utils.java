package utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.Config;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;

public class Utils {

	private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());
	private static final PrintStream LOG_FILE = openLogFile();

	private static PrintStream openLogFile() {
		try {
			return new PrintStream(new FileOutputStream("target/logging.txt", false));
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Unable to open logging.txt", e);
		}
	}

	private void logBanner(String title) {
		logBannerStatic(title);
	}

	public static void logBannerStatic(String title) {
		LOG_FILE.println();
		LOG_FILE.println("================================================================================");
		LOG_FILE.println(title + " | " + LocalDateTime.now());
		LOG_FILE.println("================================================================================");
	}

	public RequestSpecification requestSpecification() {
		return new RequestSpecBuilder()
				.setBaseUri(Config.baseURI())
				.addQueryParam("key", Config.apiKey())
				.setContentType(ContentType.JSON)
				.addFilter(new RequestLoggingFilter(LOG_FILE))
				.addFilter(new ResponseLoggingFilter(LOG_FILE))
				.addFilter(new AllureRestAssured())
				.build();
	}

	public ResponseSpecification responseSpecification(int expectedStatusCode) {
		return new ResponseSpecBuilder()
				.expectStatusCode(expectedStatusCode)
				.expectContentType(ContentType.JSON)
				.build();
	}

	public Response addPlace(AddPlace payload) {
		LOGGER.info("Adding place: " + payload.getName());
		logBanner("ADD PLACE REQUEST: " + payload.getName());

		RequestSpecification req = requestSpecification();
		ResponseSpecification resspec = responseSpecification(200);

		try {
			return RestAssured.given()
					.spec(req)
					.body(payload)
					.when()
					.post(Config.addPlaceEndpoint())
					.then()
					.spec(resspec)
					.extract()
					.response();
		} catch (RuntimeException e) {
			LOGGER.log(Level.SEVERE, "Add place request failed for: " + payload.getName(), e);
			throw e;
		}
	}

	public Response deletePlace(String placeId, String placeName) {
		LOGGER.info("Deleting place: " + placeName);
		logBanner("DELETE PLACE REQUEST: " + placeName);

		RequestSpecification req = requestSpecification();
		ResponseSpecification resspec = responseSpecification(200);

		try {
			return RestAssured.given()
					.spec(req)
					.body("{\"place_id\": \"" + placeId + "\"}")
					.when()
					.post(Config.deletePlaceEndpoint())
					.then()
					.spec(resspec)
					.extract()
					.response();
		} catch (RuntimeException e) {
			LOGGER.log(Level.SEVERE, "Delete place request failed for placeId: " + placeId, e);
			throw e;
		}
	}

}
