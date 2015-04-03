package com.seleniumsimplified.webdriver;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Section18_Lecture120_WebDriverWaitCustomExpectedConditions {
	
	private static WebDriver driver;
	private WebDriverWait wait;
	private String basicAjaxSite = "http://www.compendiumdev.co.uk/selenium/basic_ajax.html";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Before
	public void navigateToSite(){
		driver.get(basicAjaxSite);
		
		wait = new WebDriverWait(driver, 10, 50);
	}
	
	@Test
	public void customExpectedConditionUsingAClassMethod1(){
		//selects Server in the first combo box
		selectServerInCombo1();
		
		//custom wait using a named class
		
		wait.until(new SelectContainsText(By.id("combo2"), "Java"));
		//new WebDriverWait(driver,10,50).until(new SelectContainsText(By.id("combo2"), "Java"));
		
		//select Java in the second combo box and then clicks submit and checks the correct id on the resulting page
		selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
	}
	
	@Test
	public void customExpectedConditionUsingAClassMethod2(){
		//selects Server in the first combo box
		selectServerInCombo1();
		
		//custom wait using a named class
		
		wait.until(new SelectContainsText2(By.id("combo2"), "Java"));
		//new WebDriverWait(driver,10,50).until(new SelectContainsText(By.id("combo2"), "Java"));
		
		//select Java in the second combo box and then clicks submit and checks the correct id on the resulting page
		selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
	}
	
	@Test
	public void customExpectedConditionUsingAnonymousClassDirectly(){
		//selects Server in the first combo box
		selectServerInCombo1();
		
		//custom wait which returns an object that implements the ExpectedCondition interface
		//again have to override apply method
		//can't use a constructor because its an anonymous class
		
		wait.until( new ExpectedCondition<Boolean>(){
	
				public Boolean apply(WebDriver driver){
					return driver.findElement(By.cssSelector("option[value='23']")).isDisplayed();
			}
		});
		
		//select Java in the second combo box and then clicks submit and checks the correct id on the resulting page
		selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
	}
	
	
	@Test
	public void customExpectedConditionUsingMethodThatWrapsTheExpectedCondition(){
		selectServerInCombo1();
		
		//custom wait using a named class
		
		WebElement javaOption = wait.until(optionWithJavaValueDisplayed("23"));
		
		javaOption.click();
		
		selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
	}
	
	//this method must return an object which implements ExpectedCondition interface
	//with the method a new ExpectedCondition object is instantiated and returned
	private ExpectedCondition<WebElement> optionWithJavaValueDisplayed(final String value) {
				
		 return new ExpectedCondition<WebElement>(){
			public WebElement apply(WebDriver driver){
				return driver.findElement(By.cssSelector("option[value='" +value+ "']" ));
			}
			
		};
	}


	private class SelectContainsText implements ExpectedCondition<Boolean>{
		private String textToFind;
		private By findBy;
		
		public SelectContainsText(final By comboFindBy, final String textToFind){
			this.findBy = comboFindBy;
			this.textToFind = textToFind;
			
		}

		public Boolean apply(WebDriver driver) {
		 	
			WebElement comboBox2 = driver.findElement(this.findBy);
			//apply with xpath - no problem with stale element
            WebElement comboWithText = comboBox2.findElement(By.xpath("option[.='" + textToFind + "']"));
            return true;
		 
		}		
		
		public String toString(){
			return "select " + this.findBy + "to contain " + this.textToFind;
		}
			
	}
	
	
	private class SelectContainsText2 implements ExpectedCondition<Boolean>{
		private String textToFind;
		private By findBy;
		
		public SelectContainsText2(final By comboFindBy, final String textToFind){
			this.findBy = comboFindBy;
			this.textToFind = textToFind;
			
		}

		public Boolean apply(WebDriver driver) {
			WebElement comboBox2 = driver.findElement(this.findBy);
			List<WebElement> comboOptions = comboBox2.findElements(By.tagName("option"));
			
		try{	
			for(WebElement element:comboOptions){
				if (element.getText().equals(this.textToFind)){
					return true;
				}				
			}
			 // if the combo changes in the middle of the apply call
            // we might encounter a StaleElementReferenceException
            // catch it and continue waiting
		}catch(StaleElementReferenceException e){
			return false;
		}
			return false;
		}		
		
		public String toString(){
			return "select " + this.findBy + "to contain " + this.textToFind;
		}
			
	}
	

	private void selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected() {
		driver.findElement(By.cssSelector("#combo2 > option[value='23']")).click();
		
		//submit the form
		driver.findElement(By.name("submitbutton")).submit();
		
		wait.until(titleIs("Processed Form Details"));
		
		//ensure that value language is id 23
		Assert.assertEquals(driver.findElement(By.id("_valuelanguage_id")).getText(), "23");
	}

	private void selectServerInCombo1() {
		driver.findElement(By.cssSelector("#combo1 > option[value='3']")).click();
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

}
