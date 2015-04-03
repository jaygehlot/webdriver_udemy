package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Section15_Lecture90_Exercises_AlansMethod {

	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/gui_user_interactions.html"; 
	
	
	@BeforeClass
	public static void mainSetup(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
		driver.get(compendiumDevSite);
	}
	
	@Before
	public void refresh(){
		driver.navigate().refresh();
		
		//added additional synchronisation as the refresh doesnt block in any way, making the test intermittent
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("canvas")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("keyeventslist")));
		
		//user interactions can be intermittent
		//so click on the html to force focus on the page
		driver.findElement(By.tagName("html")).click();
	}
	
	@Test
	public void moveDraggable1ToDroppable1(){
		WebElement draggable1 = driver.findElement(By.cssSelector("#draggable1"));
		WebElement droppable1 = driver.findElement(By.cssSelector("#droppable1"));
		
		Actions actions = new Actions(driver);
		actions.clickAndHold(draggable1).moveToElement(droppable1).release().perform();
		
		Assert.assertEquals("Dropped should say Dropped!", "Dropped!", droppable1.getText());		
	}
	
	@Test
	public void drapAndDropDraggable2ToDroppable1(){
		WebElement draggable2 = driver.findElement(By.cssSelector("#draggable2"));
		WebElement droppable1 = driver.findElement(By.cssSelector("#droppable1"));
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(draggable2, droppable1).perform();
		
		Assert.assertEquals("Dropped should say Get Off Me!", "Get Off Me!", droppable1.getText());
	}
	
	@Test
	public void controlAndBwaHaHa(){
		WebElement draggable1 = driver.findElement(By.cssSelector("#draggable1"));
		
		new Actions(driver).keyDown(Keys.CONTROL).sendKeys("b").keyUp(Keys.CONTROL).perform();
		
		Assert.assertEquals("Bwa! Ha! Ha!", draggable1.getText());		
	}
	
	
	@Test
	public void drawOnCanvas(){
		WebElement canvas = driver.findElement(By.id("canvas"));
		WebElement eventList = driver.findElement(By.id("keyeventslist"));
		
		//get the list of draw events before drawing
		int eventCount = eventList.findElements(By.tagName("li")).size();
		
		new Actions(driver)
		.clickAndHold(canvas)
		.moveByOffset(100,100)
		.moveByOffset(101, 100)
		.moveByOffset(102, 100)
		.moveByOffset(103, 100)
		.moveByOffset(104, 100)
		.moveByOffset(105, 100)
		.moveByOffset(106, 100)
		.moveByOffset(107, 100)
		.moveByOffset(108, 100)
		.moveByOffset(109, 100)
		.moveByOffset(110, 100)
		.moveByOffset(111, 100)
		.release()
		.perform();
		
		Assert.assertTrue("some events are listed", eventCount < eventList.findElements(By.tagName("li")).size());
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
