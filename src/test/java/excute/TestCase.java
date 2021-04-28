package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class Testcase extends CommonBase { 
	@BeforeMethod 
	public void beforeMethod() { 
		openBrowser("Chrome","google.com");
		waitForPageLoaded(driver);}
	@AfterMethod
	public void afterMethod() {
		quitDriver(driver);}
	@Test
	public void TC_01() {
		waitForPageLoaded(driver);
		click(By.xpath("//*[contains(text(),'Quản lý hóa đơn')]"));
		waitForPageLoaded(driver);
		click(By.xpath("//*[contains(text(),'Hóa đơn đã phát hành')]"));
		waitForPageLoaded(driver);}
	@Test
	public void TC_02() {
		click(By.xpath("//button/*[text()='Lập hóa đơn']"));
		waitForPageLoaded(driver);
		setText(By.id("id"),"Bao");
		setText(By.xpath("//input[contains(@formcontrolname,'buyerName')]"),"Ha Noi");
		setText(By.xpath("//input[contains(@formcontrolname,'buyerName')]"),"Viettel");
		setText(By.xpath("//input[contains(@formcontrolname,'buyerName')]"),"Viettel");
}
}