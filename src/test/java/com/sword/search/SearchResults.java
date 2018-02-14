package com.sword.search;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.sword.setup.BrowseFactory;
/**
 * 
 * @author priya
 *
 */
public class SearchResults {
	WebDriver driver;
	By searchBox=By.xpath("//input[@id='s' and @type='search']") ;
	By searchButton=By.xpath(".//*[@id='searchsubmit']");
	String resFound="//div[@class='search-results']/article";
	String resNotFound="//a[contains(text(),'Return to Previous Screen')]";
	
	/**
	 * This life cycle method is called first to setup driver, browser and URL
	 */
	@BeforeMethod
	public  void setup(){
		driver=BrowseFactory.startbrowser("chrome");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.sword-activerisk.com/");
	}
	
	/**
	 * This helper method locates search text box and performs button click 
	 * @param searchTerm
	 */
	public void searchHelper(String searchTerm){
		driver.findElement(searchBox).sendKeys(searchTerm);
		driver.findElement(searchButton).click();
	}
	
	/**
	 * This scenarios method asserts the results.
	 * @param searchTerm
	 * @param expected
	 */
	@Test(dataProvider = "provideSearchData")
	public void search(String searchTerm,String expected) {
		if (expected.equalsIgnoreCase("ResultsFound")){
			searchHelper(searchTerm);
			Boolean Status=driver.findElement(By.xpath(resFound)).isDisplayed();
			Assert.assertTrue(Status);
		} else if (expected.equalsIgnoreCase("ResultsNotFound")){
			searchHelper(searchTerm);
			Boolean Status=driver.findElement(By.xpath(resNotFound)).isDisplayed();
			Assert.assertTrue(Status);
		} else {
			Assert.fail();
		}
	}
	
	/**
	 * This method contains  search terms(test data) and each one is executed independently
	 * @return
	 */
	@DataProvider(name = "provideSearchData")
	public Object[][] provideSearchData() {
		return new Object[][] { 
				{ "Active", "ResultsFound" }, 
				{ "Snappit", "ResultsFound" }, 
				{ "Regression", "ResultsNotFound" } 
		};
	}
	
	/**
	 * This life cycle method is called before exit
	 */
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}


