package org.ual.hmis.equipo2;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class ShortPasswordTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	// Using a system property to chose the browser (by jjcanada)
			// Browser as System.property: "browserWebDriver"
			// In maven, call: 
			//    run with firefox: clean test -DbrowserWebDriver=firefox
			//    run with chrome : clean test -DbrowserWebDriver=chrome 

			// System.setProperty("browserWebDriver", "firefox"); 
			String browserProperty = ""; 
			browserProperty= System.getProperty("browserWebDriver");
			
			// run headless: clean test -DbrowserWebDriver=firefox -Dheadless=true
			Boolean headless = false;
			if (System.getProperty("headless").equals("true")) {
				headless = true;
			}

			switch (browserProperty) {
			case "firefox": 
				// Firefox 
				// Descargar geckodriver de https://github.com/mozilla/geckodriver/releases
				// Descomprimir el archivo geckodriver.exe en la carpeta drivers

				//System.setProperty("webdriver.gecko.driver",  "drivers/geckodriver.exe");
				System.getProperties().list(System.out);
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				if (headless) firefoxOptions.setHeadless(headless);
				driver = new FirefoxDriver(firefoxOptions);

				break;
			case "chrome":
				// Chrome
				// Descargar Chromedriver de https://chromedriver.chromium.org/downloads
				// Descomprimir el archivo chromedriver.exe en la carpeta drivers

				//System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
				if (headless) chromeOptions.setHeadless(headless);
				chromeOptions.addArguments("window-size=1920,1080");
				driver = new ChromeDriver(chromeOptions);

				break;

			default:
				fail("Please select a browser");
				break;
			}
			js = (JavascriptExecutor) driver;
			vars = new HashMap<String, Object>();
//	  int browser = 0;
//	  Boolean headless = true;
//	  
//	  switch(browser) {
//	  case 0: //firefox
//		  //System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
//		  FirefoxOptions firefoxOptions = new FirefoxOptions();
//		  if (headless) firefoxOptions.setHeadless(headless);
//		  driver = new FirefoxDriver(firefoxOptions);
//		  break;
//	  case 1: //chrome
//		  //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//		  ChromeOptions chromeOptions = new ChromeOptions();
//		  if (headless) chromeOptions.setHeadless(headless);
//		  chromeOptions.addArguments("window-size=1920,1080");
//		  driver = new ChromeDriver(chromeOptions);
//		  break;
//	  default:
//		  fail("Elige un buscador (0,1)");
//		  break;
//	  }
//	  js = (JavascriptExecutor) driver;
//	  vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void shortPassword() {
    // Test name: shortPassword
    // Step # | name | target | value
    // 1 | open | /login | 
    driver.get("http://seleniumweb.northeurope.cloudapp.azure.com/");
    // 2 | setWindowSize | 1218x648 | 
    driver.manage().window().setSize(new Dimension(1218, 648));
    // 3 | click | linkText=Create an account | 
    driver.findElement(By.linkText("Create an account")).click();
    try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    // 4 | type | id=username | alex
    driver.findElement(By.id("username")).sendKeys("alex");
    // 5 | type | id=password | hmis
    driver.findElement(By.id("password")).sendKeys("hmis");
    // 6 | click | id=username | 
    driver.findElement(By.id("username")).click();
    // 7 | type | id=username | alexalex
    driver.findElement(By.id("username")).sendKeys("alexalex");
    // 8 | click | css=.btn | 
    driver.findElement(By.cssSelector(".btn")).click();
    // 9 | click | id=userForm | 
    driver.findElement(By.id("userForm")).click();
    try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    // 10 | assertText | id=password.errors | Try one with at least 8 characters.
    assertThat(driver.findElement(By.id("password.errors")).getText(), is("Try one with at least 8 characters."));
  }
}
