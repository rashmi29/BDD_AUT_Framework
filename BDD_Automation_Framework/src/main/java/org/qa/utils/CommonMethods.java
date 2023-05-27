package org.qa.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonMethods {
	public static Logger log = LogManager.getLogger(CommonMethods.class.getName());
	CommonUtilities objCU = new CommonUtilities();

	Boolean flag = null;
	WebDriverWait wait;
	String parenwindow = "", Newcurrentwin = "";

	/**
	 * Launch url in browser and maximize browser
	 * 
	 * @param driver - WebDriver instance
	 * @param url    - url to open
	 * @return true if url is launched
	 */
	public boolean launchURL(WebDriver driver, String url) {
		try {
			driver.get(url);
			driver.manage().window().maximize();
			log.debug("Launching url: " + url);
			return true;
		} catch (Exception e) {
			log.error("Error in <launchURL> " + e.getMessage());
			return false;
		}
	}

	/**
	 * Identify the object on UI and highlight the object boundary with RED border
	 * 
	 * @param driver  - WebDriver instance
	 * @param locator - locator to identify element
	 * @return - WebElement
	 */
	public WebElement objecLocater(WebDriver driver, By locator) {

		WDWait(driver, locator);
		WebElement objTemp = driver.findElement(locator);
		if (objTemp.isDisplayed() || objTemp.isEnabled()) {

			try {
				log.debug("Object is Visible/Enabled: " + locator);
				return objTemp;
			} catch (Exception e) {
				log.error("Object failed to be highlighted: " + locator);
				return null;
			}

		} else {
			log.error("Object has not built:  " + locator);
			throw new ElementNotInteractableException(null, null);
		}

	}

	/**
	 * Waits until visibility of element OR max 30 sec
	 * 
	 * @param driver  - WebDriver instance
	 * @param locator - locator to identify element
	 * @return WebElement - value if the function returned something different from
	 *         null or false before the timeout expired.
	 */
	public WebElement WDWait(WebDriver driver, By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Clears and sets text in text box and highlight the text box boundary with RED
	 * border
	 * 
	 * @param driver  - WebDriver instance
	 * @param locator - locator to identify element
	 * @param txtVar  - text to set in elements
	 * @return boolean
	 */
	public boolean setText(WebDriver driver, By locator, String txtVar) {
		boolean flag;
		try {
			objecLocater(driver, locator);
			WebElement wd = driver.findElement(locator);
			wd.clear();
			wd.click();
			wd.sendKeys(txtVar);
			flag = true;
			log.debug("Setting text <" + txtVar + "> in field with locator: " + locator);
		} catch (Exception e) {
			flag = false;
			log.error("Error in <setText> " + e.getMessage());
		}

		return flag;
	}

	/**
	 * Get text from element
	 * 
	 * @param driver  - WebDriver instance
	 * @param locator - locator to identify element
	 * @return text from element
	 */
	public String getText(WebDriver driver, By locator) {
		try {
			log.debug("Getting text from element with locator: " + locator);
			return objecLocater(driver, locator).getText();
		} catch (Exception e) {
			log.error("Error in <getText> " + e.getMessage());
			return driver.findElement(locator).getText();
		}
	}

	/**
	 * Clicking on element identified by given locator
	 * 
	 * @param driver  - WebDriver instance
	 * @param locator - locator to identify element
	 * @return boolean
	 */
	public boolean click(WebDriver driver, By locator) {
		WebElement objWE = objecLocater(driver, locator);

		if (objWE.isEnabled()) {
			log.debug("Clicking on element with locator: " + locator);
			objWE.click();
			return true;
		} else
			return false;
	}

	/**
	 * Select value from Drop down based on Visible text / Value / index 1
	 * 
	 * @param driver  - WebDriver instance
	 * @param locator - locator to identify element
	 * @param txtVar  - value to be selected in drop down
	 * @return boolean
	 */
	public boolean selectValue(WebDriver driver, By locator, String txtVar) {
		Select objWE = new Select(objecLocater(driver, locator));
		boolean flag = false;
		try {
			objWE.selectByValue(txtVar);
			flag = true;
		} catch (Exception e) {
			try {
				objWE.selectByVisibleText(txtVar);
				flag = true;
				log.debug("Selecting value <" + txtVar + "> in element with locator: " + locator);
			} catch (Exception e2) {
				try {
					objWE.selectByIndex(1);
					log.debug("Selecting value at index 1 in element with locator: " + locator);
					flag = true;
				} catch (Exception e3) {
					log.error("Error in <selectValues> " + e.getMessage());
				}
			}
		}
		return flag;
	}

	/**
	 * Checks if element present or not
	 * 
	 * @param driver  - WebDriver instance
	 * @param locator - locator to identify element
	 * @return boolean
	 */
	public boolean isElementPresent(WebDriver driver, By locator) {
		try {
			objecLocater(driver, locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			log.error("Error in <isElementPresent> " + e.getMessage());
			return false;
		}
	}

	/**
	 * Check element visibility on page
	 * 
	 * @param driver  - WebDriver driver
	 * @param locator - locator to identify element
	 * @return boolean
	 */
	public boolean checkElementVisibility(WebDriver driver, By locator) {
		try {
			if (driver.findElement(locator).isDisplayed()) {
				log.debug("Element is visible with locator: " + locator);
				return true;
			} else
				return false;
		} catch (NoSuchElementException e) {
			log.error("Error in <checkElementVisibility> " + e.getMessage());
			return false;
		}
	}

	/**
	 * Returns attribute value
	 * 
	 * @param driver
	 * @param locator
	 * @param attribute
	 * @return
	 */
	public String getAttribute(WebDriver driver, By locator, String attribute) {
		String attrValue = "";
		try {
			attrValue = objecLocater(driver, locator).getAttribute(attribute);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			attrValue = "";
		}
		return attrValue;
	}

	/**
	 * Waits until element is clickable OR max specified time
	 * 
	 * @param driver        - WebDriver instance
	 * @param locator       - locator to identify element
	 * @param timeInSeconds - maximum seconds to wait
	 * @return - the WebElement once it is located and clickable (visible and
	 *         enabled)
	 */
	public WebElement waitUntillElementClickable(WebDriver driver, By locator, long timeInSeconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * Waits untill element is visible
	 * 
	 * @param driver        - WebDriver instance
	 * @param locator       - locator to identify element
	 * @param timeInSeconds - maximum seconds to wait
	 * @return - the WebElement once it is visible and enabled)
	 */
	public WebElement waitUntillElementVisible(WebDriver driver, By locator, long timeInSeconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}