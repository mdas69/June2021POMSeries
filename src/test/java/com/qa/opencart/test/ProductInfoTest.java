package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void productImagesTest() {
		resPage = accPage.doSearch("Macbook");
		prodInfoPage = resPage.selectProduct("MacBook Air");
		Assert.assertEquals(prodInfoPage.getProductImagesCount(), 4);

	}

	@Test
	public void productInfoTest() {
		resPage = accPage.doSearch("Macbook");
		prodInfoPage = resPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = prodInfoPage.getProductInfo();
		Assert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");

	}

}
