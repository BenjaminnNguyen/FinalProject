package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class TS_DangNhap extends CommonBase { 
	@BeforeMethod 
	public void beforeMethod() { 
		openBrowser("chorme","http://113.160.133.144:20201/Admin/Login/Index/");
		waitForPageLoaded(driver);}
	@AfterMethod
	public void afterMethod() {
		quitDriver();}
	@Test
	public void TC_01_DangNhapThanhCong() {
		waitForPageLoaded(driver);
		setText(By.id("UserName"),"manh");
		setText(By.id("PassWord"),"manh123");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String titleHomePage=getTitle();
		verifyCompare(titleHomePage,"Trang chủ");
		String userName=getText(By.xpath("//li[contains(@class,'dropdown user user-menu')]//span"));
		verifyCompare(userName,"Tran Tiên Manh");}
	@Test
	public void TC_03_DangNhapKhongThanhCong_BoTrongTruongDL() {
		clearText(By.id("UserName"));
		setText(By.id("PassWord"),"manh123");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.xpath("//div[@class='validation-summary-errors text-danger']//li"));
		verifyCompare(popUpFail,"Enter a valid username!");
		setText(By.id("UserName"),"manh");
		clearText(By.id("PassWord"));
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail2=getText(By.xpath("//div[@class='validation-summary-errors text-danger']//li"));
		verifyCompare(popUpFail2,"Enter a valid password!");}
	@Test
	public void TC_04_DangNhapKhongThanhCong() {
		waitForPageLoaded(driver);
		setText(By.id("UserName"),"bao bao");
		setText(By.id("PassWord"),"manh123");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail1=getText(By.xpath("//div[@class='validation-summary-errors text-danger']//li"));
		verifyCompare(popUpFail1,"Tài khoản không tồn tại!");
		setText(By.id("UserName"),"manh");
		setText(By.id("PassWord"),"b");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail2=getText(By.xpath("//div[@class='validation-summary-errors text-danger']//li"));
		verifyCompare(popUpFail2,"Mật khẩu không khớp!");
		setText(By.id("UserName"),"bao bao");
		setText(By.id("PassWord"),"b");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail3=getText(By.xpath("//div[@class='validation-summary-errors text-danger']//li"));
		verifyCompare(popUpFail3,"Tài khoản không tồn tại!");}
	@Test
	public void TC_05_DangNhapVoiGoogle() {
		waitForPageLoaded(driver);
		setText(By.id("UserName"),"manh");
		setText(By.id("PassWord"),"manh123");
		click(By.xpath("//*[@class='btn btn-google btn-user btn-block']"));
		waitForPageLoaded(driver);
		String titleGooglePage=getTitle();
		verifyCompare(titleGooglePage,"Google");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("PassWord"));}
	@Test
	public void TC_07_DangNhap_QuenMatKhau() {
		waitForPageLoaded(driver);
		setText(By.id("UserName"),"manh");
		setText(By.id("PassWord"),"manh123");
		click(By.xpath("//*[contains(text(),'Forgot Password?')]"));
		waitForPageLoaded(driver);
		String titleForgotPassword=getTitle();
		verifyCompare(titleForgotPassword,"Forgot Password");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("PassWord"));
	}
}