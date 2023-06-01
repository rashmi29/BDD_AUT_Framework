package org.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountsPage {
private WebDriver driver;
	
	// OR
	private By accountSections = By.xpath("//div[@id='account-account']//h2");
	
	// Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}	
	
	// Action
	public String getAccountsPageTitle() {
		return driver.getTitle();
	}
	
	public int getAccountSectionsCount() {
		return (driver.findElements(accountSections).size() - 1);
	}
	
	public List<String> getAccountSections() {
		List<WebElement> sectionsList = driver.findElements(accountSections);
		List<String> sectionHeader = new ArrayList<String>();
		
		for(int i = 0; i <sectionsList.size() -1; i++) {
			sectionHeader.add(sectionsList.get(i).getText());
		}
		return sectionHeader;
	}

}
