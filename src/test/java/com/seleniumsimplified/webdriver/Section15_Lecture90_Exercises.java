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

public class Section15_Lecture90_Exercises {

	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/gui_user_interactions.html";
	public WebElement drag1, drag2, drop1, canvas; 
	
	
	@BeforeClass
	public static void mainSetup(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Before
	public void preTestSetup(){
		//the test needs to navigate to the basic form page prior to each test
		//if it is in @BeforeClass it will do this only once, and so the first test will pass
		driver.get(compendiumDevSite);
		drag1 = new PageElements().draggable1;
		drag2 = new PageElements().draggable2;
		drop1 = new PageElements().droppable1;
		canvas = new PageElements().canvas;		
	}
	
	@Test
	public void draggable1OntoDroppable1Method1Exercise1(){ 		
		Action dragAndDrop = new Actions(driver).dragAndDrop(drag1, drop1).build();
		dragAndDrop.perform();
		
		Assert.assertEquals("Droppable1 should now say Dropped!", "Dropped!", drop1.getText());
	}
	
	@Test
	public void draggable1OntoDroppable1Method2Exercise1(){
		
		Actions dragAndDrop = new Actions(driver).clickAndHold(drag1);
		dragAndDrop.moveByOffset(300, 40);
		dragAndDrop.release().perform();
		
		Assert.assertEquals("Droppable1 should now say Dropped!", "Dropped!", drop1.getText());		
	}
	
	@Test
	public void draggable2OntoDroppable1Method1Exercise2(){
		
		Action drag2AndDropOnto1 = new Actions(driver).dragAndDrop(drag2, drop1).build();
		
		drag2AndDropOnto1.perform();
		
		Assert.assertEquals("Droppable1 should now say Get Off Me!", "Get Off Me!", drop1.getText());
		
	}
	

	@Test
	public void controlAndBTextChangeOnDraggable1Exercise3(){
		Actions controlAndB = new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, "b"));
		controlAndB.perform();
		
		Assert.assertEquals("Draggrable1 does not say 'Bwa.Ha.Ha'","Bwa! Ha! Ha!", drag1.getText());
	}
	
	@Test
	public void drawOnCanvasChallenge2(){
	
		
	 new Actions(driver)
		.clickAndHold(canvas)
		.moveByOffset(100,200)
		.release()
		.clickAndHold(canvas)
		.moveByOffset(101,200)
		.release()
		.clickAndHold(canvas)
		.moveByOffset(102,200)
		.release()
		.clickAndHold(canvas)
		.moveByOffset(103,200)
		.release()
		.clickAndHold(canvas)
		.moveByOffset(104,200)
		.release()
		.clickAndHold(canvas)
		.moveByOffset(105,200)
		.release()
		.perform();
	 	
	}
	
	
	
	 class PageElements{
		private WebElement draggable1; 
		private WebElement draggable2;
		private WebElement droppable1; 
		private WebElement canvas;
		
		public PageElements(){
			this.draggable1 = driver.findElement(By.cssSelector("#draggable1"));
			this.droppable1 = driver.findElement(By.cssSelector("#droppable1"));
			this.draggable2 = driver.findElement(By.cssSelector("#draggable2"));
			this.canvas = driver.findElement(By.cssSelector("#canvas"));
		}
	}
	
	
//	@AfterClass
//	public static void tearDown(){
//		driver.quit();
//	}
}
