package com.seleniumsimplified.webdriver;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import static org.hamcrest.CoreMatchers.is;

public class Section11_CSSSelector_Example {

	
	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/find_by_playground.php";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver();
		driver.get(compendiumDevSite);
	}
	
	
	@Test
	public void findByUsingCSSDataDriven(){
		
		class TestData{
			public String css;
			public String name;
			public String value;
			public String alt;
			
			public TestData(String css, String name, String value, String alt){
				this.css=css;
				this.name=name;
				this.value=value;
				this.alt=alt;
			}
		}
		
		//asList converts an Array to an object that implements the list interface
		List<TestData> dataItems = Arrays.asList(
				new TestData("[id=\"p31\"]", "name", "pName31", "By.id(\"p31\")"),
				new TestData("#p31", "name", "pName31", "By.id(\"p31\")"),
				new TestData("[id='p31']", "name", "pName31", "By.id(\"p31\")"),
				new TestData("*[id='p31']", "name", "pName31", "By.id(\"p31\")"),
				new TestData("[name='ulName1']", "id", "ul1", "By.name(\"ulName1\")"),
				new TestData("*[name='ulName1']", "id", "ul1", "By.name(\"ulName1\")"),
				new TestData("*[name=\"ulName1\"]", "id", "ul1", "By.name(\"ulName1\")"),
				new TestData("ul[name='ulName1']", "id", "ul1", "By.name(\"ulName1\")"),
				new TestData("ul[name=\"ulName1\"]", "id", "ul1", "By.name(\"ulName1\")"),
				new TestData("[name=\"ulName1\"]", "id", "ul1", "By.name(\"ulName1\")"),
				new TestData("div.specialDiv", "id", "div1", "By.className(\"specialDiv\")"),
				new TestData(".specialDiv", "id", "div1", "By.className(\"specialDiv\")"),
				new TestData("*.specialDiv", "id", "div1", "By.className(\"specialDiv\")"),
				new TestData("li", "name", "liName1", "By.tagName(\"li\")")
				);
		
		for (TestData dataItem: dataItems){
			WebElement element = driver.findElement(By.cssSelector(dataItem.css));			
			
			System.out.println(
					String.format("Instead of %s use By.cssSelector(%s))",
									dataItem.alt, dataItem.css.replaceAll("\"", "\\\\\"") ));
			
			
			Assert.assertThat(element.getAttribute(dataItem.name), is(dataItem.value));
		}
		
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
