package com.qa.democart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	/** OR "(//div[@id='content']//ul[@class='list-unstyled'])[1]/li" */
	private By productPriceDate = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By qunatity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}

	public int getProductImagesCount() {
		return elementUtil.getElements(productImages).size();
	}

	public Map<String,String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());
		List<WebElement> metaDataList = elementUtil.getElements(productMetaData);
		
		// product meta data
		for (WebElement e : metaDataList) {
			String meta[]=e.getText().split(":");
			String metaKey =meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
				
		//product price data
		List<WebElement> metaPriceList = elementUtil.getElements(productPriceDate);
		String price =metaPriceList.get(0).getText().trim();
		String exTaxprice =metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exTaxPrice", exTaxprice);
		
		return productInfoMap;
	}

}
