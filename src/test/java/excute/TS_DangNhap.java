package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class TS_DangNhap extends CommonBase { 
	@BeforeMethod 
	public void beforeMethod() { 
		openBrowser("chorme","http://113.160.133.144:20201/");
		waitForPageLoaded(driver);}
	@AfterMethod
	public void afterMethod() {
		quitDriver(driver);}
	@Test
	public void TC_01_DangNhapThanhCong() {
		waitForPageLoaded(driver);
		setText(By.id("username"),"baobao");
		setText(By.id("password"),"baobao");
		click(By.id("login"));
		waitForPageLoaded(driver);
		String titleHomePage=getTitle);
		verifyCompare(titleHomePage,"Trang ch?");
		String userName=getText(By.xpath("//*[contains(@class,'lable-username')]"));
		verifyCompare(userName,"baobao");}
	@Test
	public void TC_02_DangNhapKhongThanhCong_BoTrongAll() {
		waitForPageLoaded(driver);
		clearText(By.id("username"));
		clearText(By.id("password"));
		click(By.id("login"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail,"Vui l?ng nh?p Tên ðãng nh?p và m?t kh?u");}
	@Test
	public void TC_03_DangNhapKhongThanhCong_BoTrongTruongDL() {
		);
		clearText(By.id("username"));
		setText(By.id("password"),"baobao");
		click(By.id("login"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail,"Vui l?ng nh?p Tên ðãng nh?p và m?t kh?u");
		setText(By.id("username"),"baobao");
		clearText(By.id("password"));
		click(By.id("login"));
		waitForPageLoaded(driver);
		String popUpFail2=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail2,"Vui l?ng nh?p Tên ðãng nh?p và m?t kh?u");}
	@Test
	public void TC_04_DangNhapKhongThanhCong() {
		waitForPageLoaded(driver);
		setText(By.id("username"),"bao bao");
		setText(By.id("password"),"baobao");
		click(By.id("login"));
		waitForPageLoaded(driver);
		String popUpFail1=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail1,"Tên ðãng nh?p");
		setText(By.id("username"),"bao123");
		setText(By.id("password"),"b");
		click(By.id("login"));
		waitForPageLoaded(driver);
		String popUpFail2=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail2,"Tên ðãng nh?p");
		setText(By.id("username"),"bao bao");
		setText(By.id("password"),"b");
		click(By.id("login"));
		waitForPageLoaded(driver);
		String popUpFail3=getText(By.xpath("//*[contains(@class,'lable-fail')]"));
		verifyCompare(popUpFail3,"Tên ðãng nh?p");}
	@Test
	public void TC_05_DangNhapVoiGoogle() {
		waitForPageLoaded(driver);
		setText(By.id("username"),"baobao");
		setText(By.id("password"),"baobao");
		click(By.id("logingoogle"));
		waitForPageLoaded(driver);
		String titleGooglePage=getTitle);
		verifyCompare(titleGooglePage,"Google");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("password"));}
	@Test
	public void TC_06_DangNhapVoiFacebook() {
		waitForPageLoaded(driver);
		setText(By.id("username"),"baobao");
		setText(By.id("password"),"baobao");
		click(By.id("loginfacebook"));
		waitForPageLoaded(driver);
		String titleFacebookPage=getTitle);
		verifyCompare(titleFacebookPage,"Facebook");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("password"));}
	@Test
	public void TC_07_DangNhap_QuenMatKhau() {
		waitForPageLoaded(driver);
		setText(By.id("username"),"baobao");
		setText(By.id("password"),"baobao");
		click(By.id("forgotpassword"));
		waitForPageLoaded(driver);
		String titleForgotPassword=getTitle);
		verifyCompare(titleForgotPassword,"Forgot password");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("password"));
}
}