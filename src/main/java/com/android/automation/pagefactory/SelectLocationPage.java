package com.android.automation.pagefactory;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SelectLocationPage {

	private AndroidDriver<AndroidElement> driver;
	private WebDriverWait wait;

	@AndroidFindBy(id = "in.swiggy.android:id/tv_primary_cta")
	private AndroidElement setDeliveryLocation;
	
	@AndroidFindBy(id="in.swiggy.android:id/item_menu_top_header_restaurant_name1")
	private AndroidElement changeLocation;
	
	@AndroidFindBy(id="in.swiggy.android:id/location_description")
	private AndroidElement setLocationTextBox;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout")
	private List<AndroidElement> searchResults;
	
	@AndroidFindBy(id="in.swiggy.android:id/google_place_search_title_text1")
	private AndroidElement confirmLocation;
	
	@AndroidFindBy(id="in.swiggy.android:id/pick_current_location_button")
	private AndroidElement locateMeLocation;
	
	@AndroidFindBy(id="in.swiggy.android:id/item_menu_top_header_restaurant_name")
	private AndroidElement locationName;
	
	@AndroidFindBy(id="in.swiggy.android:id/title")
	private AndroidElement invalidPageTitle;
	
	@AndroidFindBy(id="in.swiggy.android:id/disc_text_change_location")
	private AndroidElement invalidPageEditLocation;
	
	@AndroidFindBy(id="in.swiggy.android:id/tv_nux_login")
	private AndroidElement loginBtn;
	
	
	public SelectLocationPage(AndroidDriver<AndroidElement> driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void clickChangeLocation() {
		wait.until(ExpectedConditions.elementToBeClickable(changeLocation));
		changeLocation.click();	
	}
	
	public void sendLocationTextBox(String location) {
		wait.until(ExpectedConditions.elementToBeClickable(setLocationTextBox));
		setLocationTextBox.click();
		setLocationTextBox.sendKeys(location);
	}
	
	
	public void clickSearchResults(int num) {
		wait.until(ExpectedConditions.elementToBeClickable(searchResults.get(num)));
		searchResults.get(num).click();
	}
	
	public void clickConfirmLocation() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmLocation));
		confirmLocation.click();
	}
	
	public void clickLocateMe() {
		wait.until(ExpectedConditions.elementToBeClickable(locateMeLocation));
		locateMeLocation.click();
	}
	
	public String getLocation() {
		wait.until(ExpectedConditions.visibilityOf(locationName));
		return locationName.getText();
	}
	
	public String getInvalidPageTitle() {
		wait.until(ExpectedConditions.visibilityOf(invalidPageTitle));
		return invalidPageTitle.getText();
	}
	
	public void clickEditLocation() {
		wait.until(ExpectedConditions.elementToBeClickable(invalidPageEditLocation));
		invalidPageEditLocation.click();
	}
	
	public void clickLoginBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
	}
		
}
