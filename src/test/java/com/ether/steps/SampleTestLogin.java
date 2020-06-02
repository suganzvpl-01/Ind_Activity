package com.ether.steps;

import java.sql.ResultSet;

import com.ether.base.SeleniumDriver;
import com.ether.base.TestUtil;
import com.ether.pages.actions.LoginAction;
import com.ether.pages.locators.LoginPageLocators;

//import com.ether.pages.locators.loginPageLocators;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class SampleTestLogin {
	TestUtil util = new TestUtil();
	LoginPageLocators locator = new LoginPageLocators();

	@Given("^Launch url$")
	public void loginToUrl() {
		SeleniumDriver.openPage("http://elastic.rapidtestpro.com:8086/index");
	}

	@And("^login with userName and password$")
	public void loginWithusername() throws Exception {
		String userName = null;
		String password = null;
		util.setUpDBConnection();
		String query = "Select * from User_Details";
		ResultSet result = util.getDataFromDB(query);

		while (result.next()) {
			userName = result.getString("Username");
			password = result.getString("Password");
		}
		SeleniumDriver.inputValuesInElement(locator.username, userName);
		SeleniumDriver.inputValuesInElement(locator.password, password);
		SeleniumDriver.clickOnTheElement(locator.signIn);

		util.closeDBConnection();
	}

}
