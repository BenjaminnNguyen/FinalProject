package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class Test extends CommonBase { 
	 @BeforeMethod 
	 public void beforeMethod() { 
 		init;
	 } 
	 @Test 
	 public void tc1() { 
 		Test;
	 } 
	 @AfterMethod 
	 public void afterMethod() { 
 		quitDriver(driver);
	 } 

}