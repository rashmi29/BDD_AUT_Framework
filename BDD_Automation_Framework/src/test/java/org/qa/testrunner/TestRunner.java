package org.qa.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = { "src/test/resources/org/qa/features" },
		glue = { "org.qa.stepdefinition", "org.qa.apphooks" },
		tags = "@Smoke",
		plugin = { "pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
		)

public class TestRunner extends AbstractTestNGCucumberTests{

}
