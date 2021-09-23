package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.democart.pages.AccountsPage;
import com.qa.opencart.utils.ApplicationErrors;
import com.qa.opencart.utils.ConstantsUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 100: Desgin Login Page for Open cart application. . .")
@Story("US 101: Login page feature  with different features. . . ")
public class LoginPageTest extends BaseTest {

	@Description("Login page title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, ConstantsUtil.LOGIN_PAGE_TITLE, ApplicationErrors.TITLE_ERROR_MESSG);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageHeaderTest() {
		String header = loginPage.getPageHeaderText();
		Assert.assertEquals(header, ConstantsUtil.PAGE_Header, ApplicationErrors.HEADER_ERROR_MESSG);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());

	}
	
	@Description("loging into page")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void dologinTest() {
		 accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
}
