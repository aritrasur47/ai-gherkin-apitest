package stepDefinitions;

public enum ApiEndpoint {

	ADD_PLACE_API("AddPlaceAPI"),
	DELETE_PLACE_API("DeletePlaceAPI");

	private final String stepValue;

	ApiEndpoint(String stepValue) {
		this.stepValue = stepValue;
	}

	public static ApiEndpoint fromStepValue(String value) {
		for (ApiEndpoint endpoint : values()) {
			if (endpoint.stepValue.equals(value)) {
				return endpoint;
			}
		}
		throw new IllegalArgumentException("Unknown API: " + value);
	}

}
