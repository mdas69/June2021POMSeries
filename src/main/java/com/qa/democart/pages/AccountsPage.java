package com.qa.democart.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ConstantsUtil;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// by locator
	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo h1 a");
	private By logoutlink = By.xpath("//aside[@id='column-right']//a[text()='Logout']");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		return elementUtil.waitForTitleIs(5, ConstantsUtil.ACCOUNT_PAGE_Title);
	}

	public String getAccPageUrl() {
		return elementUtil.waitForUrlFraction(ConstantsUtil.ACCOUNT_PAGE_URL_FRATCION, 5);
	}

	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
	}

	public List<String> getAccountSectionList() {
		List<String> accSecValueList = new ArrayList<String>();
		List<WebElement> accSecList = elementUtil.waitForElementsToBeVisibleBy(accSections, 5);
		for (WebElement e : accSecList) {
			accSecValueList.add(e.getText());
		}
		// Collections.sort(accSecValueList);
		return accSecValueList;
	}

	public boolean isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logoutlink);
	}

	// this method has to return something so that testng test class could verify
	// more will be enhanced
	public ResultPage doSearch(String productName) {

		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doActionClick(searchButton);
		return new ResultPage(driver);
	}
}
