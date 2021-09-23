package com.qa.democart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ResultPage{
	
	
	private WebDriver driver;
	private ElementUtil elementUtil;

	private By searchHeader= By.cssSelector("div#content h1");
	private By productResults= By.cssSelector("div.caption a");
	
	public ResultPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getSearchPageHeader() {
		return elementUtil.doGetText(searchHeader);
	}
	
	public int getSearcProductListsCount() {
		return elementUtil.getElements(productResults).size();
	}
	
	public ProductInfoPage selectProduct(String mainproductName) {
		List<WebElement> searchList=elementUtil.getElements(productResults);
		for (WebElement e: searchList) {
			if(e.getText().trim().equals(mainproductName)) {
				e.click();
				break;
			}
		}
		//after selecting the product you will be reaching the next landing page so return that page class
		// this approach is call Test Driven Approach
		return new ProductInfoPage(driver);
	}

}
