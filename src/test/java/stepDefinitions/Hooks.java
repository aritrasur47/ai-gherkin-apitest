package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utils.Utils;

public class Hooks {

	@Before
	public void beforeScenario(Scenario scenario) {
		Utils.logBannerStatic("SCENARIO START: " + scenario.getName());
	}

	@After
	public void afterScenario(Scenario scenario) {
		Utils.logBannerStatic("SCENARIO " + scenario.getStatus() + ": " + scenario.getName());

		if (scenario.isFailed()) {
			Allure.addAttachment("Scenario failure", "text/plain",
					"Scenario \"" + scenario.getName() + "\" failed with status: " + scenario.getStatus());
		}
	}

}
