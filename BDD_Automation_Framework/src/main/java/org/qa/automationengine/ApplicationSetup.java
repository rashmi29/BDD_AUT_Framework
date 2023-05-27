package org.qa.automationengine;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.qa.utils.*;

public class ApplicationSetup {

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String testURL;
	CommonUtilities objCU = new CommonUtilities();
	CommonMethods ObjCm = new CommonMethods();

	public synchronized void init(String environment, String browser) {

		if (environment.equalsIgnoreCase("QA")) {
			startBrowser(browser);
			setTestURL(objCU.readPropertyFile("QAURL"));
		}
		if (environment.equalsIgnoreCase("Dev")) {
			startBrowser(browser);
			setTestURL(objCU.readPropertyFile( "DEVURL"));
		}
		if (environment.equalsIgnoreCase("Prod")) {
			startBrowser(browser);
			setTestURL(objCU.readPropertyFile("PRODURL"));
		}

	}

	public WebDriver startBrowser(String browser) {
		int wt = Integer.parseInt(objCU.readPropertyFile("waitTimeout"));
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);

			tlDriver.set(new ChromeDriver(options));
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(wt));
			System.out.println(browser + " browser launch sucessfully");
		}

		else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(wt));
			System.out.println(browser + " browser launch sucessfully");
		}

		else if (browser.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(wt));
			System.out.println(browser + " browser launch sucessfully");
		}

		else if (browser.equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			tlDriver.set(new InternetExplorerDriver());
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(wt));
			System.out.println(browser + " browser launch sucessfully");
		} else {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(wt));
			System.out.println("Defaul browser launch sucessfully");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}

	public void tearDown() {
		try {
			getDriver().quit();
			System.out.println("Application Closed sucessfully");
		} catch (Exception e) {
			System.out.println("Application Closed force-fully ");
		}
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public String getTestURL() {
		return testURL;
	}

	public void setTestURL(String testUrl) {
		testURL = testUrl;
	}


}
