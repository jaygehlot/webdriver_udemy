package com.seleniumsimplified.webdriver;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class FirstTest 
    
{
    @Test
    public void driverIsTheKing()
    {
    	WebDriver driver = new HtmlUnitDriver();
    	
    	driver.get("http://www.compendiumdev.co.uk/selenium");
        
    	assertTrue( driver.getTitle().startsWith("Selenium Simplified") );
    }
    
    @Test
    public void fireFoxIsSupportedByWebDriver()
    {
    	FirefoxProfile profile = new FirefoxProfile();
    	profile.setPreference("network.proxy.type", 0);
    	
    	WebDriver driver = new FirefoxDriver(profile);
    	
    	driver.get("http://www.compendiumdev.co.uk/selenium/");
        
    	assertTrue( driver.getTitle().startsWith("Selenium Simplified") );
    	
    	driver.close();
    	
    }
}
