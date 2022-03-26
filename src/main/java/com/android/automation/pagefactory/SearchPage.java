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

public class SearchPage {

	private AndroidDriver<AndroidElement> driver;
	private WebDriverWait wait;

	@AndroidFindBy(className = "android.widget.EditText")
	private AndroidElement searchTextBox;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
	private List<AndroidElement> searchResults;
	
	
	public SearchPage(AndroidDriver<AndroidElement> driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void searchTextBox(String toSearchText) {
		wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
		searchTextBox.click();
		searchTextBox.sendKeys(toSearchText);
		
	}
	
	public void clickSearchBarResults(int searchResultsNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(searchResults.get(searchResultsNumber)));
		searchResults.get(searchResultsNumber).click();
		
	}
	
	public void clickSearchBarResults(String searchResultsText) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@text=\"" +  searchResultsText + "\"]"))));
		driver.findElement(By.xpath("//*[@text=\"" +  searchResultsText + "\"]")).click();
		
	}
	
	public void clickResults(String results) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//android.widget.TextView[@text=\""+ results+ "\"]"))));
		driver.findElement(By.xpath("//android.widget.TextView[@text=\""+ results+ "\"]")).click();
	}
		
}
