package testdata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	private static final Properties PROPERTIES = new Properties();

	static {
		try (InputStream in = TestDataBuild.class.getClassLoader().getResourceAsStream("testdata.properties")) {
			if (in == null) {
				throw new IllegalStateException("testdata.properties not found on classpath");
			}
			PROPERTIES.load(in);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to load testdata.properties", e);
		}
	}

	public static AddPlace buildAddPlace() {
		Location location = new Location();
		location.setLat(Double.parseDouble(PROPERTIES.getProperty("lat")));
		location.setLng(Double.parseDouble(PROPERTIES.getProperty("lng")));

		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(Integer.parseInt(PROPERTIES.getProperty("accuracy")));
		addPlace.setAddress(PROPERTIES.getProperty("address"));
		addPlace.setLanguage(PROPERTIES.getProperty("language"));
		addPlace.setPhone_number(PROPERTIES.getProperty("phone_number"));
		addPlace.setWebsite(PROPERTIES.getProperty("website"));
		addPlace.setName(PROPERTIES.getProperty("name"));
		addPlace.setLocation(location);
		addPlace.setTypes(Arrays.asList(PROPERTIES.getProperty("types").split(",")));

		return addPlace;
	}

}
