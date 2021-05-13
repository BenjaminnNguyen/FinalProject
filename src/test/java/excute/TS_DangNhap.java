package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class TS_DangNhap extends CommonBase { 
	@BeforeMethod 
	public void beforeMethod() { 
		openBrowser("horme","http://113.160.133.144:20201/Admin/Login/Index/");
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
	public void TC_02_DangNhapKhongThanhCong_BoTrongAll() {
		waitForPageLoaded(driver);
		clearText(By.id("UserName"));
		clearText(By.id("PassWord"));
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail,"Vui lòng nhập Tên đăng nhập và mật khẩu");}
	@Test
	public void TC_03_DangNhapKhongThanhCong_BoTrongTruongDL() {
		clearText(By.id("UserName"));
		setText(By.id("PassWord"),"manh123");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail,"Vui lòng nhập Tên đăng nhập và mật khẩu");
		setText(By.id("UserName"),"manh");
		clearText(By.id("PassWord"));
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail2=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail2,"Vui lòng nhập Tên đăng nhập và mật khẩu");}
	@Test
	public void TC_04_DangNhapKhongThanhCong() {
		waitForPageLoaded(driver);
		setText(By.id("UserName"),"bao bao");
		setText(By.id("PassWord"),"baobao");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail1=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail1,"Tên đăng nhập");
		setText(By.id("UserName"),"bao123");
		setText(By.id("PassWord"),"b");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail2=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail2,"Tên đăng nhập");
		setText(By.id("UserName"),"bao bao");
		setText(By.id("PassWord"),"b");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		String popUpFail3=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail3,"Tên đăng nhập");}
	@Test
	public void TC_05_DangNhapVoiGoogle() {
		waitForPageLoaded(driver);
		setText(By.id("UserName"),"manh");
		setText(By.id("PassWord"),"manh123");
		click(By.id("logingoogle"));
		waitForPageLoaded(driver);
		String titleGooglePage=getTitle();
		verifyCompare(titleGooglePage,"Google");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("PassWord"));}
	@Test
	public void TC_06_DangNhapVoiFacebook() {
		waitForPageLoaded(driver);
		setText(By.id("UserName"),"manh");
		setText(By.id("PassWord"),"manh123");
		click(By.id("loginfacebook"));
		waitForPageLoaded(driver);
		String titleFacebookPage=getTitle();
		verifyCompare(titleFacebookPage,"Facebook");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("PassWord"));}
	@Test
	public void TC_07_DangNhap_QuenMatKhau() {
		waitForPageLoaded(driver);
		setText(By.id("UserName"),"manh");
		setText(By.id("PassWord"),"manh123");
		click(By.id("forgotpassword"));
		waitForPageLoaded(driver);
		String titleForgotPassword=getTitle();
		verifyCompare(titleForgotPassword,"Forgot password");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("PassWord"));
}
}