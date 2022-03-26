package com.android.automation.pagefactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {

	private AndroidDriver<AndroidElement> driver;
	private WebDriverWait wait;
	
	@AndroidFindBy(id="com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	private AndroidElement allowAccessLocationUse;
	
	@AndroidFindBy(id="com.google.android.gms:id/cancel")
	private AndroidElement phoneContinueNoneOfAbove;
	

	public BasePage(AndroidDriver<AndroidElement> driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	

	
	public void allowAccessLocation() {
		allowAccessLocationUse.click();
	}
	
	public void clickPhoneNoneAbove() {
		phoneContinueNoneOfAbove.click();
	}
	
	public void tapXY(int x,int y) {
		TouchAction action = new TouchAction(driver);
		action.tap(PointOption.point(x,y)).perform();
	}
	
	public void swipe(int fromX,int fromY,int toX,int toY) throws InterruptedException {
		 @SuppressWarnings("rawtypes")
		TouchAction action = new TouchAction(driver);
		 action.press(PointOption.point(fromX,fromY))
		 .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))) 
		 .moveTo(PointOption.point(toX, toY))
		 .release()
		 .perform();
		 Thread.sleep(500);
		 }
	
	public void scrollToElement(String expectedView) throws InterruptedException {
		boolean found = false;
		
		while(found==false) {
		List<AndroidElement> listViews = driver.findElements(By.xpath("//android.widget.TextView"));
		List<String> listViewsText = new ArrayList<>();	
		
		
		for(AndroidElement view: listViews) {
			listViewsText.add(view.getText());
		}
		
		if(!listViewsText.contains(expectedView)) {
			swipe(632,1000,632,250);
			Thread.sleep(200);
			found = false;
		}else {
			System.out.println("Element found");
			found = true;
		}		
		
	}		
	}
}
