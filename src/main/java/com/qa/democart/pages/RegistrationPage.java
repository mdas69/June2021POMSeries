package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ConstantsUtil;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// By Locators
	private By firstName = By.xpath("//input[@id='input-firstname']");
	private By lastName = By.xpath("//input[@id='input-lastname']");
	private By email = By.xpath("//input[@id='input-email']");
	private By telephone = By.xpath("//input[@id='input-telephone']");
	private By password = By.xpath("//input[@id='input-password']");
	private By passwordConfirm = By.xpath("//input[@id='input-confirm']");
	private By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
	private By checkBox = By.xpath("//input[@name='agree']");
	private By continuebutton = By.xpath("//input[@value='Continue']");
	private By sucessMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public boolean accountRegistration(String firsName, String lastName, String email, String telephone, String password,
			String subscribe) {
		elementUtil.doSendKeys(this.firstName, firsName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.passwordConfirm, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(subscribeYes);
		}
		elementUtil.doClick(checkBox);
		elementUtil.doClick(continuebutton);
		String msg=elementUtil.waitForElementPresence(sucessMessage, ConstantsUtil.DEFAULT_TIME_OUT).getText();
		if (msg.contains(ConstantsUtil.REGISTER_SUCCESS_MESSAGE)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
