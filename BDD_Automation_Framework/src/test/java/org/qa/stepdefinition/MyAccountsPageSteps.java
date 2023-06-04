package org.qa.stepdefinition;

import java.util.List;
import java.util.Map;

import org.qa.automationengine.ApplicationSetup;
import io.cucumber.java.en.*;
import org.qa.pages.AccountsPage;
import org.qa.pages.LoginPage;
import org.testng.Assert;

public class MyAccountsPageSteps extends ApplicationSetup {
	private LoginPage objLoginPg = new LoginPage(getDriver());
	private AccountsPage objAcctPg;
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(io.cucumber.datatable.DataTable credTable) {
		List<Map<String, String>> credList = credTable.asMaps();
		String uname = credList.get(0).get("username");
		String pwd = credList.get(0).get("password");

		getDriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		objAcctPg = objLoginPg.doLogin(uname, pwd);
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
		String title = objAcctPg.getAccountsPageTitle();
	}

	@Then("user gets accounts section")
	public void uer_gets_accounts_section(io.cucumber.datatable.DataTable expSectionsTable) {
		List<String> actSectionsList = objAcctPg.getAccountSections();
		List<String> expSectionsList = expSectionsTable.asList();

		for (String expSectionName : expSectionsList) {
			Assert.assertTrue(actSectionsList.contains(expSectionName));
		}
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expSectionCount) {
		Assert.assertTrue(objAcctPg.getAccountSectionsCount() == expSectionCount);
	}

}
