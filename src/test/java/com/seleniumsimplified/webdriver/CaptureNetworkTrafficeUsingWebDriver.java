package com.seleniumsimplified.webdriver;

import java.util.List;

import junit.framework.Assert;

import org.browsermob.core.har.Har;
import org.browsermob.core.har.HarEntry;
import org.browsermob.core.har.HarNameValuePair;
import org.browsermob.proxy.ProxyServer;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CaptureNetworkTrafficeUsingWebDriver {
	
	private static WebDriver driver;
	private final static String webSite = "http://www.yell.com";
	private static ProxyServer server;
	
	@Test
	public void captureNetworkTrafficTest(){
		// start the proxy
		server = new ProxyServer(4444);
		try {
			server.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		server.setCaptureHeaders(true);
		server.setCaptureContent(true);
		
		Proxy proxy = server.seleniumProxy();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		
		driver = new FirefoxDriver(capabilities);

		driver.get(webSite);
		
		driver.findElement(By.id("search_whatInput")).sendKeys("plumbers");
		driver.findElement(By.id("location")).sendKeys("reading");
		server.newHar("yell.com");
		driver.findElement(By.className("search-submit")).click();
		
		driver.findElement(By.xpath(".//*[@class='parentListing'][1]//*[@class='last']//*[@class='l-link-icon']")).click();
		
		
		Har har = server.getHar();
		List<HarEntry> harEntries = har.getLog().getEntries();
		
		for(HarEntry entry: harEntries){
			if(entry.getRequest().getUrl().contains("yellgroup.122.2o7.net")){
				List<HarNameValuePair> harQueryStringEntries = entry.getRequest().getQueryString();
					for(HarNameValuePair harPair: harQueryStringEntries){
						System.out.println("Name is= "+ harPair.getName() + " and Value is= " + harPair.getValue());
						
						if(harPair.getName().equals("pev2")){
						Assert.assertTrue(harPair.getValue().equals("LIST"));
						}
					}
			}
		}
	}
	
	@AfterClass
	public static void tearDown(){
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();
	}

	
	

}
