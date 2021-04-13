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
public class LogoutTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	  int browser = 1;
	  Boolean headless = true;
	  
	  switch(browser) {
	  case 0: //firefox
		  System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		  FirefoxOptions firefoxOptions = new FirefoxOptions();
		  if (headless) firefoxOptions.setHeadless(headless);
		  driver = new FirefoxDriver(firefoxOptions);
		  break;
	  case 1: //chrome
		  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		  ChromeOptions chromeOptions = new ChromeOptions();
		  if (headless) chromeOptions.setHeadless(headless);
		  chromeOptions.addArguments("window-size=1920,1080");
		  driver = new ChromeDriver(chromeOptions);
		  break;
	  default:
		  fail("Elige un buscador (0,1)");
		  break;
	  }
	  js = (JavascriptExecutor) driver;
	  vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void logout() {
    // Test name: logout
    // Step # | name | target | value
    // 1 | open | http://localhost:8080/login | 
    driver.get("http://localhost:8080/login");
    // 2 | setWindowSize | 1221x648 | 
    driver.manage().window().setSize(new Dimension(1221, 648));
    // 3 | click | name=username | 
    driver.findElement(By.name("username")).click();
    // 4 | type | name=username | alexalex
    driver.findElement(By.name("username")).sendKeys("alexalex");
    // 5 | type | name=password | hmishmis
    driver.findElement(By.name("password")).sendKeys("hmishmis");
    // 6 | sendKeys | name=password | ${KEY_ENTER}
    driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
    try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    // 7 | click | css=h2 | 
    driver.findElement(By.cssSelector("h2")).click();
    // 8 | assertText | css=h2 | Welcome alexalex | Logout
    assertThat(driver.findElement(By.cssSelector("h2")).getText(), is("Welcome alexalex | Logout"));
    // 9 | click | linkText=Logout | 
    driver.findElement(By.linkText("Logout")).click();
    // 10 | click | css=.form-heading | 
    
    driver.findElement(By.cssSelector(".form-heading")).click();
    // 11 | assertText | css=.form-heading | Log in
    assertThat(driver.findElement(By.cssSelector(".form-heading")).getText(), is("Log in"));
  }
}
