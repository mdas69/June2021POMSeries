package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getRegTestData() {
		return new Object[][] { { "m", "das", "1234567890", "123@1234ind", "yes" },
				{ "Tommy", "Jerry", "6781234590", "123@1234ind", "No" },
				{ "Kendrick", "Recce", "1023456789", "123@1234ind", "No" },
				{ "Allistor", "Snape", "1023456089", "123@14ind", "Yes" } };
	}

	@Test(dataProvider = "getRegTestData")
	public void registrationTest(String firsName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firsName, lastName, getRandomEmail(), telephone,
				password, subscribe));
	}

	public String getRandomEmail() {
		Random random = new Random();
		String email = "discard+" + random.nextInt(5000) + "@commoninf.com";
		return email;
	}

}
