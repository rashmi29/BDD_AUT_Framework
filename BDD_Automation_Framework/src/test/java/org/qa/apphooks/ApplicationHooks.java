package org.qa.apphooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.qa.automationengine.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	@Before(order = 1)
	public void launchBrowser() {
		ApplicationSetup objAppSetup = new ApplicationSetup();
		objAppSetup.init(System.getProperty("Environment"), System.getProperty("Browser"));
	}

	@After(order = 0)
	public void quitBrowser() {
		ApplicationSetup.getDriver().quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) (ApplicationSetup.getDriver())).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}
}
