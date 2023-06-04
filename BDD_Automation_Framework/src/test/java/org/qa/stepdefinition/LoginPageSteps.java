package org.qa.stepdefinition;

import io.cucumber.java.en.*;
import org.qa.pages.*;
import org.apache.logging.log4j.*;
import org.qa.automationengine.ApplicationSetup;
import org.testng.Assert;

public class LoginPageSteps extends ApplicationSetup{
	
	private LoginPage objLoginPg = new LoginPage(getDriver());
	private String title;
	public static Logger log = LogManager.getLogger(LoginPageSteps.class.getName());
	
	@Given("user is on login page")
	public void user_is_on_login_page() {
		getDriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		objLoginPg.waitUntillPageVisibility();
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		title = objLoginPg.getLoginPageTitle();
		System.out.println("Login page title is: " + title);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) {
		System.out.println("expectedTitleName-->" + expectedTitleName);
		System.out.println("title-->" + title);
		Assert.assertTrue(title.equals(expectedTitleName));
	}

	@Then("forgot your password link should be displayed")
	public void forgot_your_password_link_should_be_displayed() {
		Assert.assertTrue(objLoginPg.isForgotPasswordLinkExist());
	}

	@When("user enters username {string}")
	public void user_enters_username(String username) {
		objLoginPg.enterUsername(username);
	}

	@When("user enters password {string}")
	public void user_enters_password(String pwd) {
		objLoginPg.enterPassword(pwd);
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		objLoginPg.clickSignIn();
	}
}
