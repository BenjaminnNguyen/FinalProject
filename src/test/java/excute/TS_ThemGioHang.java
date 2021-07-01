package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class TS_ThemGioHang extends CommonBase { 
	@BeforeMethod 
	public void beforeMethod() { 
		openBrowser("Chrome","http://113.160.133.144:20201/Admin/Login");
		waitForPageLoaded(driver);}
	@AfterMethod
	public void afterMethod() {
		quitDriver();}
	@Test
	public void TC_01_ThemGioHangMotSanPham() {
		waitForPageLoaded(driver);
		login("bao","bao123");
		click(By.xpath("//i[@class='fa fa-cart-plus fa-2x']"));
		verifyTextPresent("Chưa có sản phẩm nào trong giỏ hàng");
		back(driver);
		waitForPageLoaded(driver);
		click(By.xpath("//input[@name='keyword']"));
		setText(By.xpath("//input[@name='keyword']"),"Đắc nhân tâm");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		waitForElement(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"),driver);
		click(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"));
		String bookName=getText(By.xpath("//*[@id='product']/div[1]/div[2]/h1"));
		verifyCompare(bookName,"Đắc nhân tâm");
		scrollToElement(By.xpath("//a[@class='btn-add-cart']"));
		click(By.xpath("//a[@class='btn-add-cart']"));
		waitForPageLoaded(driver);
		navigateURL("http://113.160.133.144:20201/gio-hang",driver);
		waitForPageLoaded(driver);
		verifyElementText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[1]"),bookName);
		String bookPrice=getText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[5]"));
		String bookCount=getValue(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[4]/input"));
		verifyCompare(bookCount,"1");
		setText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[4]/input"),"2");
		String bookCountChanged=getValue(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[4]/input"));
		click(By.id("btn_update"));
		String total=evaluate(bookPrice + "*" + bookCountChanged);
		String totalPrice=getText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[6]"));
		verifyCompareNumber(total,totalPrice);
		click(By.id("btn_payment"));
		waitForPageLoaded(driver);
		click(By.xpath("//*[@id='confirm']/div[1]//a"));
		String totalPayment=getText(By.xpath("//*[@id='total_price']"));
		verifyCompare(totalPrice,totalPayment);}
	@Test
	public void TC_02_ThemGioHangNhieuSanPham() {
		waitForPageLoaded(driver);
		login("bao","bao123");
		click(By.xpath("//i[@class='fa fa-cart-plus fa-2x']"));
		verifyTextPresent("Chưa có sản phẩm nào trong giỏ hàng");
		back(driver);
		waitForPageLoaded(driver);
		click(By.xpath("//input[@name='keyword']"));
		setText(By.xpath("//input[@name='keyword']"),"Đắc nhân tâm");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		waitForElement(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"),driver);
		click(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"));
		String bookName=getText(By.xpath("//*[@id='product']/div[1]/div[2]/h1"));
		verifyCompare(bookName,"Đắc nhân tâm");
		scrollToElement(By.xpath("//a[@class='btn-add-cart']"));
		click(By.xpath("//a[@class='btn-add-cart']"));
		waitForPageLoaded(driver);
		navigateURL("http://113.160.133.144:20201/gio-hang",driver);
		waitForPageLoaded(driver);
		verifyElementText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[1]"),bookName);
		String bookPrice=getText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[5]"));
		String bookCount=getValue(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[4]/input"));
		verifyCompare(bookCount,"1");
		click (By.id("btn_continue"));
		waitForPageLoaded(driver);
		click(By.xpath("//input[@name='keyword']"));
		setText(By.xpath("//input[@name='keyword']"),"Kinh tế");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		waitForElement(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"),driver);
		String popupBookName=getText(By.xpath("//ul[@class='product-list']/li[1]//h5[@class='product-name']/a"));
		mouseOver(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"),false);
		click(By.xpath("//ul[@class='product-list']/li[1]//div[@class='add-to-cart']/a"));
		waitForPageLoaded(driver);
		verifyElementText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[2]/td[1]"),popupBookName);
		String bookPrice2=getText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[2]/td[5]"));
		String bookCount2=getValue(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[2]/td[4]/input"));
		verifyCompare(bookCount2,"1");
		String totalCart=getText(By.id("total_price"));
		String totalCartTrue=evaluate(bookPrice2 +"+"+bookPrice);
		verifyCompareNumber(totalCartTrue,totalCart);
		click(By.id("btn_deleteAll"));
		waitForPageLoaded(driver);
		verifyTextPresent("Chưa có sản phẩm nào trong giỏ hàng");}
	@Test
	public void TC_03_GioHangChuaDangNhap() {
		waitForPageLoaded(driver);
		navigateURL("http://113.160.133.144:20201/gio-hang",driver);
		verifyTextPresent("Chưa có sản phẩm nào trong giỏ hàng");
		back(driver);
		waitForPageLoaded(driver);
		click(By.xpath("//input[@name='keyword']"));
		setText(By.xpath("//input[@name='keyword']"),"Cuộc chiến công nghệ số");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		waitForElement(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"),driver);
		click(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"));
		String bookName=getText(By.xpath("//*[@id='product']/div[1]/div[2]/h1"));
		verifyCompare(bookName,"Cuộc chiến công nghệ số");
		scrollToElement(By.xpath("//a[@class='btn-add-cart']"));
		click(By.xpath("//a[@class='btn-add-cart']"));
		waitForPageLoaded(driver);
		navigateURL("http://113.160.133.144:20201/gio-hang",driver);
		waitForPageLoaded(driver);
		verifyElementText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[1]"),bookName);
		String bookPrice=getText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[5]"));
		String bookCount=getValue(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[4]/input"));
		verifyCompare(bookCount,"1");
		String total=evaluate(bookPrice + "*" + bookCount);
		String totalPrice=getText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[6]"));
		verifyCompareNumber(total,totalPrice);
		verifyElementText(By.id("check_login"),"Vui lòng đăng nhập để tiếp tục đặt hàng");
		click(By.id("check_login"));
		waitForPageLoaded(driver);
		isDisplay(By.id("UserName"));
		isDisplay(By.id("PassWord"));
		setText(By.id("UserName"),"bao");
		setText(By.id("PassWord"),"bao123");
		click(By.xpath("//*[@value='ĐĂNG NHẬP' or @value='Log in' or @value='Login']|//button[contains(text(),'Đăng nhập') or text()='Login']"));
		waitForPageLoaded(driver);
		navigateURL("http://113.160.133.144:20201/gio-hang",driver);
		waitForPageLoaded(driver);
		verifyElementText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[1]"),bookName);
		verifyCompare(bookCount,"1");}
	@Test
	public void TC_04_LuuSanPhamTrongGioHang() {
		waitForPageLoaded(driver);
		login("bao","bao123");
		click(By.xpath("//i[@class='fa fa-cart-plus fa-2x']"));
		verifyTextPresent("Chưa có sản phẩm nào trong giỏ hàng");
		back(driver);
		waitForPageLoaded(driver);
		click(By.xpath("//input[@name='keyword']"));
		setText(By.xpath("//input[@name='keyword']"),"Điệp viên kỳ quái");
		click(By.xpath("//button[@type='submit']"));
		waitForPageLoaded(driver);
		waitForElement(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"),driver);
		click(By.xpath("//li[@class='col-sm-4 product-item']/div[1]"));
		String bookName=getText(By.xpath("//*[@id='product']/div[1]/div[2]/h1"));
		verifyCompare(bookName,"Điệp viên kỳ quái");
		scrollToElement(By.xpath("//a[@class='btn-add-cart']"));
		click(By.xpath("//a[@class='btn-add-cart']"));
		waitForPageLoaded(driver);
		navigateURL("http://113.160.133.144:20201/gio-hang",driver);
		waitForPageLoaded(driver);
		verifyElementText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[1]"),bookName);
		String bookPrice=getText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[5]"));
		String bookCount=getValue(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[4]/input"));
		verifyCompare(bookCount,"1");
		click(By.xpath("//*[text()=' Log Out']"));
		waitForPageLoaded(driver);
		login("bao","bao123");
		waitForPageLoaded(driver);
		navigateURL("http://113.160.133.144:20201/gio-hang",driver);
		waitForPageLoaded(driver);
		verifyElementText(By.xpath("//table[@class='table table-bordered table-responsive cart_summary']/tbody/tr[1]/td[1]"),bookName);
		verifyCompare(bookCount,"1");
	}
}