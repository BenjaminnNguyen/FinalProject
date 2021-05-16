package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class TS_DangKy extends CommonBase { 
	@BeforeMethod 
	public void beforeMethod() { 
		openBrowser("Chorme","http://113.160.133.144:20201/Admin/Register/Index");
		waitForPageLoaded(driver);}
	@AfterMethod
	public void afterMethod() {
		quitDriver();}
	@Test
	public void TC_01_DangKyThanhCong() {
		waitForPageLoaded(driver);
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String titleHomePage=getTitle();
		verifyCompare(titleHomePage,"Fairy Tail");
		String userName=getText(By.id("//*[@href='/Admin/Login/Logout']//../a[1]"));
		verifyCompare(userName,"baobao");}
	@Test
	public void TC_02_DangKyKhongThanhCong_BoTrongAll() {
		waitForPageLoaded(driver);
		clearText(By.id("Name"));
		clearText(By.id("Email"));
		clearText(By.id("Phone"));
		clearText(By.id("UserName"));
		clearText(By.id("PassWord"));
		clearText(By.id("Address"));
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail,"Tên đăng nhập không được để trống!");}
	@Test
	public void TC_03_DangKyKhongThanhCong_BoTrongTruongDL() {
		setText(By.id("Name"),"Nguyen Quoc Bao");
		clearText(By.id("Email"));
		clearText(By.id("Phone"));
		clearText(By.id("UserName"));
		clearText(By.id("PassWord"));
		clearText(By.id("Address"));
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail,"Vui lòng nhập đầy đủ thông tin");
		clearText(By.id("Name"));
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail1=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail1,"Vui lòng nhập đầy đủ thông tin");
		setText(By.id("Name"),"Nguyen Quoc Bao");
		clearText(By.id("Email"));
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail2=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail2,"Vui lòng nhập đầy đủ thông tin");
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		clearText(By.id("Phone"));
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail3=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail3,"Vui lòng nhập đầy đủ thông tin");
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		clearText(By.id("UserName"));
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail4=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail4,"Vui lòng nhập đầy đủ thông tin");
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		clearText(By.id("PassWord"));
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail5=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail5,"Vui lòng nhập đầy đủ thông tin");
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		clearText(By.id("Address"));
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail6=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail6,"Vui lòng nhập đầy đủ thông tin");}
	@Test
	public void TC_04_DangKyKhongThanhCong_TrungEmail() {
		waitForPageLoaded(driver);
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail,"Email đã có người đăng ký!");}
	@Test
	public void TC_05_DangKyKhongThanhCong_TrungUserName() {
		waitForPageLoaded(driver);
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"manh");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail,"Tên tài khoản đã được sử dụng");}
	@Test
	public void TC_06_DangKyKhongThanhCong_SaiEmail() {
		waitForPageLoaded(driver);
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//button[text()='Đăng ký']"));
		waitForPageLoaded(driver);
		String popUpFail=getText(By.id("//*[@class='validation-summary-errors text-danger']//li[1]"));
		verifyCompare(popUpFail,"Tên email không hợp lệ!");}
	@Test
	public void TC_07_DangKy_DaCoTaiKhoan_DangNhap() {
		waitForPageLoaded(driver);
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//a[@href='/Admin/Login/Login']"));
		waitForPageLoaded(driver);
		String titleLogin=getTitle();
		verifyCompare(titleLogin,"Login");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("PassWord"));}
	@Test
	public void TC_08_DangKy_QuenMatKhau() {
		setText(By.id("Name"),"Nguyen Quoc Bao");
		setText(By.id("Email"),"nguyenquocbao.1999443@gmail.com");
		setText(By.id("Phone"),"0334218638");
		setText(By.id("UserName"),"baobao");
		setText(By.id("PassWord"),"baobao");
		setText(By.id("Address"),"Văn Giang, Hưng Yên");
		click(By.id("//a[@href='forgot-password.html']"));
		waitForPageLoaded(driver);
		String titleForgotPass=getTitle();
		verifyCompare(titleForgotPass,"Forgot Password");
		back(driver);
		waitForPageLoaded(driver);
		verifyEmpty(By.id("PassWord"));
}
}