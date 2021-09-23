package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.democart.pages.ResultPage;
import com.qa.opencart.utils.ConstantsUtil;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String title = accPage.getAccPageTitle();
		System.out.println("acc page title is: " + title);
		Assert.assertEquals(title, ConstantsUtil.ACCOUNT_PAGE_Title);
	}

	@Test(priority = 2)
	public void accPageHeaderTest() {
		String title = accPage.getAccPageHeader();
		Assert.assertEquals(title, ConstantsUtil.PAGE_Header);
	}

	@Test(priority = 3)
	public void accSectionsListTest() {
		List<String> actualAccSecList = accPage.getAccountSectionList();
		Assert.assertEquals(actualAccSecList, ConstantsUtil.EXPECTED_ACC_SEC_LIST);

	}

	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { { "Macbook Pro" }, { "Macbook Air" }, { "Apple" } };
	}

	@Test(priority = 5, dataProvider = "getSearchData")
	public void searchTest(String productName) {
		resPage = accPage.doSearch(productName);
		String resultHeader = resPage.getSearchPageHeader();
		Assert.assertTrue(resultHeader.contains(productName));
	}

	@DataProvider
	public Object[][] getProductSelectData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "Macbook", "MacBook Air" },
				{ "Apple", "Apple Cinema 30\"" } };
	}

	@Test(priority = 6, dataProvider = "getProductSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resPage = accPage.doSearch(productName);
		prodInfoPage = resPage.selectProduct(mainProductName);
		String header = prodInfoPage.getProductHeaderText();
		System.out.println("The product header is . . ." + header);
		Assert.assertEquals(header, mainProductName);
	}
	
	
}
