package com.android.automation.pagefactory;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MainPage {

	private AndroidDriver<AndroidElement> driver;
	private WebDriverWait wait;


	@AndroidFindBy(id = "in.swiggy.android:id/tv_primary_cta")
	private AndroidElement setDeliveryLocation;
	
	@AndroidFindBy(id = "in.swiggy.android:id/bottom_bar_explore")
	private AndroidElement btmSearchBtn;
	
	
	public MainPage(AndroidDriver<AndroidElement> driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void clickSetDeliveryLocation() {
		wait.until(ExpectedConditions.elementToBeClickable(setDeliveryLocation));
		setDeliveryLocation.click();
	}
	
	public void clickBtmSearchBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(btmSearchBtn));
		btmSearchBtn.click();
	}
	
		
}
