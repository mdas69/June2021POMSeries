package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	/*
	 * This was made private so that encapsulation will be achieved. So that no one
	 * could create the object of this class and access the driver
	 */
	private WebDriver driver;
	private JavasScriptUtil jsUtil;

	/*
	 * Constructor so that driver will have same session id from the class we are
	 * calling it.
	 */
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		this.jsUtil= new JavasScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		if (Boolean.parseBoolean(DriverFactory.highlight.trim())) {jsUtil.flash(driver.findElement(locator));}
		return (driver.findElement(locator));
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public void clickOnElements(By locator, String value) {
		List<WebElement> eleList = getElements(locator);
		System.out.println(eleList.size());

		for (WebElement e : eleList) {
			if (e.getText().equals(value)) {
				e.click();
				System.out.println(value + "will be clicked");
				break;
			}
		}
	}

	public List<String> getLinksTextList(By locator) {

		List<WebElement> eleList = getElements(locator);

		List<String> textList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				textList.add(text);
			}
		}
		return textList;
	}

	/*
	 * Select Class Drop-Down Only for Select html tag
	 */
	public void doSelectByVisibleText(By locator, String dropdownValue) {
		WebElement selectDropDown = getElement(locator);
		Select select = new Select(selectDropDown);
		select.selectByVisibleText(dropdownValue);
	}

	public void doSelectByValue(By locator, String dropdownValue) {
		WebElement selectDropDown = getElement(locator);
		Select select = new Select(selectDropDown);
		select.selectByValue(dropdownValue);
	}

	public void doSelectByIndex(By locator, int index) {
		WebElement selectDropDown = getElement(locator);
		Select select = new Select(selectDropDown);
		select.selectByIndex(index);
	}

	public List<String> getDropDownOptionsList(By locator) {
		WebElement elementLocated = getElement(locator);
		Select select = new Select(elementLocated);

		List<WebElement> allDropDownValues = select.getOptions();
		List<String> optionsValueList = new ArrayList<String>();

		for (WebElement e : allDropDownValues) {
			String text = e.getText();
			optionsValueList.add(text);
		}
		return optionsValueList;

	}

	public void selectdropDownValue(By locator, String Value) {
		WebElement elementLocated = getElement(locator);
		Select select = new Select(elementLocated);

		List<WebElement> allDropDownValues = select.getOptions();

		for (WebElement e : allDropDownValues) {
			String text = e.getText();
			if (text.equals(Value)) {
				e.click();
				break;
			}
		}

	}

	/*
	 * Without using Select class
	 */
	public void clickdropDownValue(By locator, String Value) {
		List<WebElement> listDropDownelements = getElements(locator);
		for (WebElement e : listDropDownelements) {
			String text = e.getText();
			if (text.equalsIgnoreCase(Value)) {
				e.click();
				break;
			}
		}

	}

	/**
	 * Single/Multiselect
	 * 
	 * @param locator
	 * @param Value   - Value is a string array for all select use all as arguments
	 */
	public void selectChoice(By locator, String... Value) {
		List<WebElement> choiceList = driver.findElements(locator);
		if (!Value[0].equals("all")) {
			for (WebElement e : choiceList) {
				String text = e.getText();

				for (int j = 0; j < Value.length; j++) {
					if (text.equals(Value[j])) {
						e.click();
						break;
					}
				}
			}
		} else {
			/*
			 * Use try catch because it may happen that there is more hidden element in the
			 * dom. and we will get
			 * 
			 */
			try {
				for (WebElement e : choiceList) {
					e.click();
				}
			} catch (Exception e) {

			}
		}

	}

	/**
	 * Suggestion List
	 * 
	 * @throws InterruptedException
	 */
	public void suggestionList(By locator, By suggestiondropdown, String... values) throws InterruptedException {
		getElement(locator).sendKeys(values[0]);
		Thread.sleep(3000);
		List<WebElement> suggestionList = getElements(suggestiondropdown);
		for (WebElement e : suggestionList) {
			String text = e.getText();
			System.out.println(text);
			for (int j = 1; j < values.length; j++) {
				if (text.equals(values[j])) {
					e.click();
					break;
				}

			}

		}

	}

	/************** Users Actions - Mouse Hover ***********************/

	public void threeLevelHandle(By parent1, By parent2, By child) throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(getElement(parent1)).perform();
		Thread.sleep(5000);
		act.moveToElement(getElement(parent2)).perform();
		Thread.sleep(5000);
		getElement(child).click();

	}

	public void twoLevelHandle(By parent1, By child) throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(getElement(parent1)).perform();
		Thread.sleep(5000);
		act.moveToElement(getElement(child)).perform();
	}

	/****************** User Action - Context Click *****************/

	public void contextMenuClick(By contextClick, By ContextMenu, String selectedMenu) {

		Actions act = new Actions(driver);
		act.contextClick(getElement(contextClick)).perform();
		;
		List<WebElement> listOfMenus = getElements(ContextMenu);
		System.out.println(listOfMenus.size());
		for (WebElement option : listOfMenus) {
			if (option.getText().equalsIgnoreCase(selectedMenu)) {
				option.click();
				break;
			}
		}

	}

	/************* User Action -SendKeys and Click *************/

	public void doActionSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	/**
	 * 
	 * @param By     locator
	 * @param String value This
	 * 
	 */
	public void doActionSendKeysAndEnter(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		getElement(locator).sendKeys(Keys.ENTER);
	}

	/********************* WebDriver Wait or Explicit Wait *************/
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible in the Web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		String text = waitForAlert(timeOut).getText();
		acceptAlert(timeOut);
		return text;
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public void sendKeysOnAlert(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);
	}

	// Wait for Web driver title.

	/**
	 * Repeatedly applies this instance's input value to the given function until
	 * one of the followingoccurs: 1.the function returns neither null nor false
	 * 2.the function throws an unignored exception 3.the timeout expires 4.the
	 * current thread is interrupted
	 * 
	 * @param timeOut
	 * @param value
	 * @return
	 */
	public String waitForTitleContains(int timeOut, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.titleContains(value))) {
			return driver.getTitle();
		}
		return null;

	}

	public String waitForTitleIs(int timeOut, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.titleIs(value))) {
			return driver.getTitle();
		}
		return null;

	}

	public String waitForUrlFraction(String urlfraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.urlContains(urlfraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	/**
	 * An expectation for checking whether the given frame is available to switch
	 * to. If the frame is available it switches the given driver to the specified
	 * frame.
	 * 
	 * @param framelocator
	 * @param timeOut
	 */
	public void waitForFrameAndSwitchToIt(By framelocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));
	}

	public void waitForFrameAndSwitchToIt(String frameName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public void waitForFrameAndSwitchToIt(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitchToIt(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param lcoator
	 * @param timeout
	 * @return the WebElement once it is located and visible
	 */

	public WebElement waitForElementToBeVisibleBy(By lcoator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(lcoator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param lcoator
	 * @param timeout
	 * @return the list of WebElements once they are located
	 */

	public List<WebElement> waitForElementsToBeVisibleBy(By lcoator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lcoator));
	}

	public void waitGetElementsTextList(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisibleBy(locator, timeOut);
		for (WebElement e : eleList) {
			e.getText();
		}
	}

	/**
	 * 
	 * @param datePickerLocator
	 * @param displayedMonth
	 * @param nextMonthButton
	 * @param ExpectedMonthYear
	 * @param date
	 */

	public void selectFutureDateCalendar(By datePickerLocator, By displayedMonth, By nextMonthButton,
			String ExpectedMonthYear, String date) {
		if (Integer.parseInt(date) >= 31)
			return;

		getElement(datePickerLocator).click();
		String actMonthYear = getElement(displayedMonth).getText();

		while (!actMonthYear.equalsIgnoreCase(ExpectedMonthYear)) {
			// traverse to next months
			getElement(nextMonthButton).click();
			actMonthYear = getElement(displayedMonth).getText();
		}
		getElement(By.xpath("//div[@class='calDate' and text()='" + date + "']")).click();

	}

	/**
	 * 
	 * @param locator
	 * @param pastDates
	 * @param ExpectedMonthYear
	 * @param date
	 */

	public void selectPastDateCalendar(By locator, By pastDates, String ExpectedMonthYear, String date) {

		String actMonthYear = getElement(locator).getText();

		while (!actMonthYear.equalsIgnoreCase(ExpectedMonthYear)) {
			// traverse to next months
			getElement(pastDates);
			actMonthYear = getElement(locator).getText();
		}

	}

	/********** Printing WebTable as is in the console ***********************/

	public void printingWebTable(By tableRows, String a, String b, String c) {

		int sizeOfTotalRows = getElements(tableRows).size();
		for (int j = 2; j < sizeOfTotalRows; j++) {
			for (int i = 1; i <= 3; i++) {
				String stringFullXpath = a + j + b + i + c;
				By byFullXpath = By.xpath(stringFullXpath);
				String values = getElement(byFullXpath).getText();
				System.out.print(values + "\t");
			}
			System.out.println();
		}

	}

}
