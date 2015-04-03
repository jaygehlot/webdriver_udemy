package com.seleniumsimplified.webdriver;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.*;

public class Section14_ManipulationExercises {

	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";
	private static By submitButton = By.cssSelector("input[type='submit'][name='submitbutton']");
	
	
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
	}
	
	//submit the form and assert that the page title changes
	@Test
	public void exercise1(){
		String getExistingTitle = driver.getTitle();
		driver.findElement(submitButton).click();
		
		//new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		Assert.assertThat(getExistingTitle, is(not(driver.getTitle())));
		Assert.assertThat("Processed Form Details", is(driver.getTitle()));
	}
	
	
	
	//clear the comments area, enter a comment, submit the form and then check the output of the form
	@Test
	public void exercise2(){
		String COMMENT = "A comment added by: Jay Gehlot";
		
		//new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("name='comments')")));
		WebElement commentsArea = driver.findElement(By.cssSelector("textarea[name='comments']"));
		commentsArea.clear();
		commentsArea.sendKeys(COMMENT);
		driver.findElement(submitButton).click();
		
		Assert.assertThat(COMMENT, is(driver.findElement(By.cssSelector("#_valuecomments")).getText()));
	}
	
	
	
	//submit form with only radio button 2 selected
	@Test
	public void exercise3(){
		boolean isRadio1Selected = driver.findElement(By.cssSelector("[value='rd1']")).isSelected();
		boolean isRadio2Selected = driver.findElement(By.cssSelector("[value='rd2']")).isSelected();
		boolean isRadio3Selected = driver.findElement(By.cssSelector("[value='rd3']")).isSelected();
		
		if (isRadio2Selected && !(isRadio1Selected && isRadio3Selected)){
			driver.findElement(submitButton).click();
		}
		
		Assert.assertThat("rd2", is(driver.findElement(By.cssSelector("#_valueradioval")).getText()));
	}
	
	
	
	//submit form with only checkbox1 selected
	@Test
	public void exercise4(){
		WebElement chkbox3 = driver.findElement(By.cssSelector("[value='cb3']"));
		WebElement chkbox2 = driver.findElement(By.cssSelector("[value='cb2']"));
		WebElement chkbox1 = driver.findElement(By.cssSelector("[value='cb1']"));
		
		String valueAttribute = chkbox1.getAttribute("value");
		chkbox1.click();
		chkbox3.click();
		
		boolean isChkbox1Checked = chkbox1.isSelected();
		boolean isChkbox2Checked = chkbox2.isSelected();
		boolean isChkbox3Checked = chkbox3.isSelected();
		
		if (isChkbox1Checked && !(isChkbox2Checked && isChkbox3Checked)){
			driver.findElement(submitButton).click();
		}
		
		Assert.assertThat(valueAttribute, is(driver.findElement(By.cssSelector("#_valuecheckboxes0")).getText()));
	}
	
	
	//submit form with drop down item 5 selected
	@Test
	public void exercise5(){
		driver.findElement(By.cssSelector("[value='dd5']")).click();
		driver.findElement(submitButton).click();
		
		Assert.assertThat("dd5", is(driver.findElement(By.cssSelector("#_valuedropdown")).getText()));
	}
	
	
	//submit form with multiple selects 1, 2 and 3
	@Test
	public void exercise6(){
		
		Select selection = new Select(driver.findElement(By.xpath("//*[@multiple='multiple']")));
		selection.selectByValue("ms1");
		selection.selectByValue("ms2");
		selection.selectByValue("ms3");
		selection.deselectByValue("ms4");
		
		driver.findElement(submitButton).click();
		
		Assert.assertThat("ms1", is(driver.findElement(By.cssSelector("#_valuemultipleselect0")).getText()));
		Assert.assertThat("ms2", is(driver.findElement(By.cssSelector("#_valuemultipleselect1")).getText()));
		Assert.assertThat("ms3", is(driver.findElement(By.cssSelector("#_valuemultipleselect2")).getText()));
		
	}
	
	
	
	//browse for a file and upload it, submit the form and check that it has been submitted
	@Test
	public void exercise7Version1(){		
		String fileName = "autotest-info.txt";
		
		WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
		
		//there are a number of different constructors for File, see File javadoc
		// this one creates a new File instance by converting the given pathname string into an abstract pathname.
		File file = new File("C:/Users/41629Ja/Desktop/autotest-info.txt");
		fileInput.sendKeys(file.getAbsolutePath());
		clickSubmitButton();
		
		Assert.assertThat(driver.findElement(By.cssSelector("#_valuefilename")).getText(), is(fileName));
	}
	
	@Test
	public void exercise7Version2(){
		String fileName = "autotest-info.txt";
		
		WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
		fileInput.sendKeys("C:\\Users\\41629Ja\\Desktop\\autotest-info.txt");
		clickSubmitButton();
		
		Assert.assertThat(driver.findElement(By.cssSelector("#_valuefilename")).getText(), is(fileName));
	}
	
	@Test
	public void exercise7Version3() throws URISyntaxException{
		WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
		
		//there are a number of different constructors for File, see File javadoc, one constructor takes in a URI
		File testFile = new File(this.getClass().getResource("/resources/CompendiumDevTestFile.txt").toURI());
		fileInput.sendKeys(testFile.getAbsolutePath());
		clickSubmitButton();
		
		Assert.assertEquals("CompendiumDevTestFile.txt", driver.findElement(By.cssSelector("#_valuefilename")).getText());
	}
	
	@Test
	public void exercise7Version4(){		
		String fileName = "autotest-info.txt";
		
		WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
		
		//there are a number of different constructors for File, see File javadoc
		// this one creates a new File instance by converting the given pathname string into an abstract pathname.
		File file = new File("C:\\Users\\41629Ja\\Desktop\\autotest-info.txt");
		fileInput.sendKeys(file.getAbsolutePath());
		clickSubmitButton();
		
		Assert.assertThat(driver.findElement(By.cssSelector("#_valuefilename")).getText(), is(fileName));
	}
	
	private void clickSubmitButton(){
		driver.findElement(submitButton).click();
	}
	
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
