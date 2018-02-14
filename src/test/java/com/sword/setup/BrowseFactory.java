package com.sword.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * 
 * This class is used to setup different types of browser drivers
 * @author priya
 *
 */
public class BrowseFactory {
	static WebDriver driver;
	public static WebDriver  startbrowser(String browser)
	{
		if(browser=="firefox")
		{
			System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browser=="chrome")
		{
			System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browser=="InternetExplorer")
		{
			System.setProperty("webdriver.ie.driver", ".\\Drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
	return driver;
	}
}



