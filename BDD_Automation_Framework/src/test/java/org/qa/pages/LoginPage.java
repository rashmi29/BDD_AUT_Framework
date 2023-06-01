package org.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.qa.pages.*;
import org.qa.utils.*;

public class LoginPage {
	public WebDriver driver;
	CommonMethods objCM = new CommonMethods();

	// 1. Object Repository: By locators
	private By txtEmailId = By.id("input-email");
	private By txtPwd = By.id("input-password");
	private By linkForgotPassword = By.linkText("Forgotten Password?");
	private By buttSignIn = By.xpath("//input[@value='Login']");

	// 2. Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// 3. Actions - page actions / feature of the page/ business logic
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean isForgotPasswordLinkExist() {
		return objCM.elementPresence(driver, linkForgotPassword);
	}

	public void enterUsername(String username) {
		objCM.setText(driver, txtEmailId, username);
	}

	public void enterPassword(String pwd) {
		objCM.setText(driver, txtPwd, pwd);
	}

	public void clickSignIn() {
		objCM.onMouseHover(driver, buttSignIn);
		try {
			Thread.sleep(10000);
		} catch (Exception e) {

		}
	}

	public void waitUntillPageVisibility() {
		objCM.waitUntillElementClickable(driver, buttSignIn, 30);
	}

	public AccountsPage doLogin(String username, String pwd) {
		this.enterUsername(username);
		this.enterPassword(pwd);
		this.clickSignIn();
		return new AccountsPage(driver);
	}

}
