package com.android.automation.pagefactory;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignUpPage {

	private AndroidDriver<AndroidElement> driver;
	private WebDriverWait wait;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private AndroidElement mobileNumberTextField;
	
	
	@AndroidFindBy(id="in.swiggy.android:id/loginCheckButton")
	private AndroidElement enterPhoneNumberBtn;
	

	public SignUpPage(AndroidDriver<AndroidElement> driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void sendMobileNumberTextField(String mobileNumber) {
		wait.until(ExpectedConditions.visibilityOf(mobileNumberTextField));
		mobileNumberTextField.sendKeys(mobileNumber);
	}
	
	public void clearMobileNumberTextField() {
		mobileNumberTextField.clear();
	}
	
	public boolean continueBtnCheck() {
		return enterPhoneNumberBtn.isEnabled();
	}
	
	
	

	
}
