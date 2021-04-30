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
public class CorrectLoginTest {
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
  public void correctLogin() {
    // Test name: correctLogin
    // Step # | name | target | value
    // 1 | open | http://localhost:8080/login | 
    driver.get("http://seleniumweb.northeurope.cloudapp.azure.com/login");
    // 2 | setWindowSize | 1552x840 | 
    driver.manage().window().setSize(new Dimension(1552, 840));
    // 3 | type | name=username | alexalex
    driver.findElement(By.name("username")).sendKeys("alexalex");
    // 4 | type | name=password | alexalex
    driver.findElement(By.name("password")).sendKeys("alexalex");
    // 5 | sendKeys | name=password | ${KEY_ENTER}
    driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
    try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    // 6 | assertText | linkText=Edit your profile | Edit your profile
    assertThat(driver.findElement(By.linkText("Edit your profile")).getText(), is("Edit your profile"));
  }
}
