package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static final Properties PROPERTIES = new Properties();

	static {
		try (InputStream in = Config.class.getClassLoader().getResourceAsStream("global.properties")) {
			if (in == null) {
				throw new IllegalStateException("global.properties not found on classpath");
			}
			PROPERTIES.load(in);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to load global.properties", e);
		}
	}

	private Config() {
	}

	public static String get(String key) {
		String value = System.getProperty(key, PROPERTIES.getProperty(key));
		if (value == null) {
			throw new IllegalArgumentException("No config value found for key: " + key);
		}
		return value;
	}

	public static String baseURI() {
		return get("baseURI");
	}

	public static String apiKey() {
		return get("apiKey");
	}

	public static String addPlaceEndpoint() {
		return get("addPlaceEndpoint");
	}

	public static String deletePlaceEndpoint() {
		return get("deletePlaceEndpoint");
	}

}
