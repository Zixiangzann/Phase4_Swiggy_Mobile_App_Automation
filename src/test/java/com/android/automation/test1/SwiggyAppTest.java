package com.android.automation.test1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.android.automation.pagefactory.BasePage;
import com.android.automation.pagefactory.MainPage;
import com.android.automation.pagefactory.MenuPage;
import com.android.automation.pagefactory.SearchPage;
import com.android.automation.pagefactory.SelectLocationPage;
import com.android.automation.pagefactory.SignUpPage;
import com.android.automation.utils.ReadProperties;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SwiggyAppTest {

	public static AndroidDriver<AndroidElement> driver;
	public static WebDriverWait wait;
	static URL URL;
	static BasePage base;
	static SelectLocationPage deliveryPage;
	static MainPage mainPage;
	static SearchPage searchPage;
	static MenuPage menuPage;
	static SignUpPage signupPage;

	@BeforeMethod
	public static void setup() throws InterruptedException {

		try {

			ReadProperties configProperties = new ReadProperties();

			try {
				configProperties.loadProperties("CapabilityProperties.properties");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String url = configProperties.get("URL");
			String platformName = configProperties.get("platformName");
			String platformVersion = configProperties.get("platformVersion");
			String appPackage = configProperties.get("appPackage");
			String appActivity = configProperties.get("appActivity");

			URL = new URL(url);
			DesiredCapabilities capabilties = new DesiredCapabilities();
			capabilties.setCapability("platformName", platformName);
			capabilties.setCapability("platformVersion", platformVersion);
			capabilties.setCapability("appPackage", appPackage);
			capabilties.setCapability("appActivity", appActivity);

			driver = new AndroidDriver<AndroidElement>(URL, capabilties);
			wait = new WebDriverWait(driver, 15000);
			System.out.println(driver.getSessionId());

			base = new BasePage(driver, wait);
			deliveryPage = new SelectLocationPage(driver, wait);
			mainPage = new MainPage(driver, wait);
			searchPage = new SearchPage(driver, wait);
			menuPage = new MenuPage(driver, wait);
			signupPage = new SignUpPage(driver, wait);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Thread.sleep(1000);

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.closeApp();
	}
	
	@Test
	public void LocateMe_InvalidLocation_tc1() throws InterruptedException{
		System.out.println("Location check");
		mainPage.clickSetDeliveryLocation();
		base.allowAccessLocation();
		deliveryPage.clickLocateMe();
		AssertJUnit.assertEquals(deliveryPage.getLocation(), "Mountain View");
		deliveryPage.clickConfirmLocation();
		AssertJUnit.assertEquals(deliveryPage.getInvalidPageTitle(), "We are not here yet!");
		Thread.sleep(2000);
		
	}
	
	@Test
	public void Signup_Invalid_phone_number_tc2() throws InterruptedException{
		System.out.println("Invalid phone number check");
		deliveryPage.clickLoginBtn();
		base.clickPhoneNoneAbove();
		signupPage.sendMobileNumberTextField("123");
		AssertJUnit.assertEquals(signupPage.continueBtnCheck(), false);
		signupPage.clearMobileNumberTextField();
		signupPage.sendMobileNumberTextField("12345678");
		AssertJUnit.assertEquals(signupPage.continueBtnCheck(), false);
		signupPage.clearMobileNumberTextField();
		signupPage.sendMobileNumberTextField("1234567890");
		AssertJUnit.assertEquals(signupPage.continueBtnCheck(), true);
		//can continue to check if have valid number but I do not own a India number 
	}
	

	@Test
	public void Checkbox_Radio__Adding_Item_tc3() throws InterruptedException {

		System.out.println("Check able to check checkbox and radio");
		System.out.println("Check able to add item");

		// Setting location
		mainPage.clickSetDeliveryLocation();
		base.allowAccessLocation();
		deliveryPage.clickChangeLocation();
		deliveryPage.sendLocationTextBox("New Delhi");
		deliveryPage.clickSearchResults(0);
		deliveryPage.clickConfirmLocation();

		// Searching
		mainPage.clickBtmSearchBtn();
		searchPage.searchTextBox("Pizza");
		Thread.sleep(1000);
		base.scrollToElement("Domino's Pizza");
		Thread.sleep(1000);
		searchPage.clickSearchBarResults("Domino's Pizza");
		Thread.sleep(1000);
		searchPage.clickResults("Domino's Pizza");
		base.tapXY(900, 268);

		// Adding
		Thread.sleep(1000);
		base.scrollToElement("Farmhouse");
		Thread.sleep(5000);
		menuPage.addToCart("Farmhouse");
		Thread.sleep(10000);
		menuPage.choiceRadioBtn(3);
		menuPage.clickContinueBtn();
		menuPage.choiceRadioBtn(1);
		menuPage.clickContinueBtn();
		menuPage.addOnCheckBox(0);
		AssertJUnit.assertEquals(menuPage.getNumberOfAddOn(), 1);
		menuPage.addOnCheckBox(1);
		AssertJUnit.assertEquals(menuPage.getNumberOfAddOn(), 2);
		menuPage.addOnCheckBox(2);
		AssertJUnit.assertEquals(menuPage.getNumberOfAddOn(), 3);
		menuPage.addOnCheckBox(3);
		AssertJUnit.assertEquals(menuPage.getNumberOfAddOn(), 4);
		menuPage.clickAddItem();

		// Checking
		System.out.println("Item: " +menuPage.getNumberOfItem());
		AssertJUnit.assertEquals(menuPage.getNumberOfItem(), "1");
		System.out.println("Price: " + menuPage.getPrice());

	}

}
