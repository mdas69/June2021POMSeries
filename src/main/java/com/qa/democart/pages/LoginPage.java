package com.qa.democart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ConstantsUtil;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// private By Locator
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdlink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By header = By.cssSelector("div#logo h1 a");
	private By registerLink = By.linkText("Register");

	// constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil=new ElementUtil(driver);
	}

	// page actions /page methods/ functionalties/behaviour
	// no assertions
	@Step("getting login page titile")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleContains(5,ConstantsUtil.LOGIN_PAGE_TITLE);
	}

	public String getPageHeaderText() {
		return elementUtil.doGetText(header);
	}

	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdlink);
	}
	
	@Step("logging into applciation username {0} and password {0} ")
	public AccountsPage doLogin(String un, String pwd) {
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password,pwd);
		elementUtil.doClick(loginBtn);
		
		//return the object of next landing  page class object, which is after login it should be accounts page
		return new AccountsPage(driver);
		// this return from here is taking the reference in AccountsPage accPage LoginPageTest:

	}
	
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
	return new RegistrationPage(driver);
	}

}
