package excute;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class NewTest {
	WebDriver driver;
  @Test
  public void f() throws InterruptedException {
	  Thread.sleep(10000);
	  
  }
  @BeforeMethod
  public void beforeMethod() {
	  //.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/DRIVER/chromedriver");
	  WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
  }

  @AfterMethod
  public void afterMethod() {
  }

}
