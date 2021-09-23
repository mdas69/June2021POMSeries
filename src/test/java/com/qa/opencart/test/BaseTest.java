package com.qa.opencart.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.democart.pages.AccountsPage;
import com.qa.democart.pages.LoginPage;
import com.qa.democart.pages.ProductInfoPage;
import com.qa.democart.pages.RegistrationPage;
import com.qa.democart.pages.ResultPage;
import com.qa.opencart.factory.DriverFactory;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	LoginPage loginPage;
	AccountsPage accPage;
	ResultPage resPage;
	ProductInfoPage prodInfoPage;
	RegistrationPage registrationPage;
	Properties prop;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProperties();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
