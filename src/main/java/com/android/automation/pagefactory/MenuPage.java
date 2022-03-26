package com.android.automation.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MenuPage {

	private AndroidDriver<AndroidElement> driver;
	private WebDriverWait wait;

	@AndroidFindBy(id="in.swiggy.android:id/radio_button")
	private List<AndroidElement> choiceRadioBtn;
	
	@AndroidFindBy(id="in.swiggy.android:id/progressive_variants_continue_button")
	private AndroidElement continueBtn;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout/android.widget.TextView[contains(@text,'MORE')]")
	private AndroidElement moreBtn;
	
	@AndroidFindBy(id="in.swiggy.android:id/check_box")
	private List<AndroidElement> addOnCheckBox;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD ITEM']")
	private AndroidElement addItem;
	
	@AndroidFindBy(xpath="//*[@resource-id='in.swiggy.android:id/fab_container_layout']//android.widget.TextView[@resource-id='in.swiggy.android:id/card_fab_item_count']")
	private AndroidElement numberOfItem;
	
	@AndroidFindBy(xpath="//*[@resource-id='in.swiggy.android:id/fab_container_layout']"
			+ "//android.widget.TextView[@resource-id='in.swiggy.android:id/card_fab_item_count']/following-sibling::android.widget.TextView[2]")
	private AndroidElement getPrice;
	
	
	@AndroidFindBy(id="in.swiggy.android:id/tv_checkout")
	private AndroidElement viewCart;
	
	@AndroidFindBy(id="in.swiggy.android:id/extras_text")
	private AndroidElement addOn;
	
	
	public MenuPage(AndroidDriver<AndroidElement> driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void addToCart(String whatToAdd) {
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+whatToAdd+"']"
				+ "/parent::android.view.ViewGroup"
				+ "/parent::android.widget.LinearLayout"
				+ "//following-sibling::android.widget.FrameLayout[2]")).click();
	}
	
	public void choiceRadioBtn(int num) {
		wait.until(ExpectedConditions.elementToBeClickable(choiceRadioBtn.get(num)));
		choiceRadioBtn.get(num).click();
	}
	
	public void clickContinueBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtn.click();
	}
	
	public void clickMore() {
		wait.until(ExpectedConditions.elementToBeClickable(moreBtn));
		moreBtn.click();
	}
	
	public void addOnCheckBox(int num) {
		wait.until(ExpectedConditions.elementToBeClickable(addOnCheckBox.get(num)));
		addOnCheckBox.get(num).click();
	}
	
	public void clickAddItem() {
		wait.until(ExpectedConditions.elementToBeClickable(addItem));
		addItem.click();
	}
	
	public String getNumberOfItem() {
		wait.until(ExpectedConditions.visibilityOf(numberOfItem));
		return numberOfItem.getText();
	}
	
	public String getPrice() {
		wait.until(ExpectedConditions.visibilityOf(getPrice));
		return getPrice.getText();
	}
	
	public void clickViewCart() {
		wait.until(ExpectedConditions.elementToBeClickable(viewCart));
		viewCart.click();
	}
	
	public int getNumberOfAddOn() {
		Integer numberOfAddOn = Integer.parseInt(addOn.getText().replaceAll("[^0-9]", ""));
		return numberOfAddOn;
	}

		
}
