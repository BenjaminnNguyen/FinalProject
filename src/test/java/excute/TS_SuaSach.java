package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class TS_SuaSach extends CommonBase { 
	@BeforeMethod 
	public void beforeMethod() { 
		openBrowser("chorme","http://113.160.133.144:20201/Admin/Login/Index/");
		waitForPageLoaded(driver);
		login("manh","manh123");}
	@AfterMethod
	public void afterMethod() {
		quitDriver();}
	@Test
	public void TC_01_SuaThanhCong() {
		navigateURL("http://113.160.133.144:20201/Admin/Book/Manage",driver);
		waitForPageLoaded(driver);
		setText(By.id("searchString"),"Book test");
		click(By.id("btnsearch"));
		waitForPageLoaded(driver);
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[1]"));
		setText(By.id("name"),"Book test");
		setText(By.id("image"),"Anh 1");
		selectFromDropdownByIndex(By.id("pub"),"0");
		String nhaSanXuat=getFirstSelectedOption(By.id("pub"));
		selectFromDropdownByIndex(By.id("author"),"0");
		String tacGia=getFirstSelectedOption(By.id("author"));
		setText(By.id("desc"),"Mo ta 1");
		click(By.id("btnUpdate"));
		pause(1000);
		String thongbaoluu=getAlertText();
		verifyCompare(thongbaoluu,"Cập nhật sách thành công!");
		acceptAlert();
		verifyElementText(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[1]"),"Book test");
		verifyElementText(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[4]"),tacGia);
		verifyElementText(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[5]"),nhaSanXuat);
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[6]/a"));
		pause(1000);
		click(By.xpath("//*[@id='tbody_cate']/tr[1]//input[@type='checkbox']"));
		click(By.id("btn_updatecate"));
		pause(1000);
		String thongbaotheloai=getAlertText();
		verifyCompare(thongbaotheloai,"Cập nhật thể loại thành công!");
		acceptAlert();
		verifyElementText(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[7]"),"0");
		verifyElementText(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[8]"),"0");
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[8]/a"));
		setText(By.id("priceout"),"100000");
		setText(By.id("promotionforPrice"),"10");
		click(By.id("btnUpdatePrice"));
		pause(1000);
		String thongbaoGia=getAlertText();
		verifyCompare(thongbaoGia,"Thiết lập giá thành công!");
		acceptAlert();
		verifyElementText(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[8]"),"100000");
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[2]"));
		pause(1000);
		String thongbaoxoa=getAlertText();
		verifyCompare(thongbaoxoa,"Bạn có muốn xóa đầu sách hiện tại?");}
	@Test
	public void TC_02_KiemTraBoTrong() {
		navigateURL("http://113.160.133.144:20201/Admin/Book/Manage",driver);
		waitForPageLoaded(driver);
		setText(By.id("searchString"),"Book test");
		click(By.id("btnsearch"));
		waitForPageLoaded(driver);
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[1]"));
		String tenAnh=getValue(By.id("image"));
		String moTa=getValue(By.id("desc"));
		String tenSach=getValue(By.id("name"));
		waitForPageLoaded(driver);
		click(By.id("btnUpdate"));
		String thongbaoluu=getAlertText();
		verifyCompare(thongbaoluu,"Cập nhật sách thành công!");
		acceptAlert();
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[1]"));
		clearText(By.id("name"));
		clearText(By.id("image"));
		clearText(By.id("desc"));
		click(By.id("btnUpdate"));
		verifyAttributeValue(By.id("name"),"style","border-color: red");
		verifyAttributeValue(By.id("image"),"style","border-color: red");
		verifyAttributeValue(By.id("desc"),"style","border-color: red");
		click(By.id("btn_cancel"));
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[1]"));
		verifyElementText(By.id("name"),tenSach);
		verifyElementText(By.id("image"),tenAnh);
		verifyElementText(By.id("desc"),moTa);}
	@Test
	public void TC_03_ValidateTenSach() {
		navigateURL("http://113.160.133.144:20201/Admin/Book/Manage",driver);
		waitForPageLoaded(driver);
		setText(By.id("searchString"),"Book test");
		click(By.id("btnsearch"));
		waitForPageLoaded(driver);
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[1]"));
		clearText(By.id("name"));
		setText(By.id("image"),"Anh 3");
		selectFromDropdownByIndex(By.id("pub"),"0");
		selectFromDropdownByIndex(By.id("author"),"0");
		setText(By.id("desc"),"Mo ta 3");
		click(By.id("btnUpdate"));
		pause(1000);
		verifyAttributeValue(By.id("name"),"style","border-color: red");
		setText(By.id("name"),"Người dùng chọn chức năng [Tạo mới], một popup hiện ra như trên Hình 3-7, người dùng nhập/chọn thông tin vào các ô nhập liệu và nhấn nút [Lưu]Nếu có trường không hợp lệ về validAate: focus vào ô nhập liệu và hiển thị border  màu đỏ tại ô nhập liệu đó.");
		click(By.id("btnUpdate"));
		pause(1000);
		verifyAttributeValue(By.id("name"),"style","border-color: red");
		verifyTextPresent("Tên sách nhỏ hơn 250 ký tự");}
	@Test
	public void TC_04_ValidateTenAnh() {
		navigateURL("http://113.160.133.144:20201/Admin/Book/Manage",driver);
		waitForPageLoaded(driver);
		setText(By.id("searchString"),"Book test");
		click(By.id("btnsearch"));
		waitForPageLoaded(driver);
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[1]"));
		setText(By.id("name"),"ten sach 4");
		clearText(By.id("image"));
		selectFromDropdownByIndex(By.id("pub"),"0");
		selectFromDropdownByIndex(By.id("author"),"0");
		setText(By.id("desc"),"Mo ta 3");
		click(By.id("btnUpdate"));
		pause(1000);
		verifyAttributeValue(By.id("image"),"style","border-color: red");
		setText(By.id("image"),"Người dùng chọn chức năng [Tạo mới], một popup hiện ra như trên Hình 3-7, người dùng nhập/chọn thông tin vào các ô nhập liệu và nhấn nút [Lưu]Nếu có trường không hợp lệ về validAate: focus vào ô nhập liệu và hiển thị border  màu đỏ tại ô nhập liệu đó.");
		click(By.id("btnUpdate"));
		pause(1000);
		verifyAttributeValue(By.id("image"),"style","border-color: red");
		verifyTextPresent("Tên ảnh nhỏ hơn 250 ký tự");}
	@Test
	public void TC_06_ValidateNhaXuatBan() {
		navigateURL("http://113.160.133.144:20201/Admin/Book/Manage",driver);
		waitForPageLoaded(driver);
		setText(By.id("searchString"),"Book test");
		click(By.id("btnsearch"));
		waitForPageLoaded(driver);
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[1]"));
		setText(By.id("name"),"Book test");
		setText(By.id("image"),"Anh 6");
		selectFromDropdownByIndex(By.id("author"),"0");
		setText(By.id("desc"),"Mo ta 6");
		click(By.id("btnUpdate"));
		pause(1000);
		verifyAttributeValue(By.id("pub"),"style","border-color: red");}
	@Test
	public void TC_07_ValidateTacGia() {
		navigateURL("http://113.160.133.144:20201/Admin/Book/Manage",driver);
		waitForPageLoaded(driver);
		setText(By.id("searchString"),"Book test");
		click(By.id("btnsearch"));
		waitForPageLoaded(driver);
		click(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[10]/a[1]"));
		setText(By.id("name"),"Book test");
		setText(By.id("image"),"Anh 7");
		selectFromDropdownByIndex(By.id("pub"),"0");
		setText(By.id("desc"),"Mo ta 7");
		click(By.id("btnUpdate"));
		pause(1000);
		verifyAttributeValue(By.id("author"),"style","border-color: red");
	}
}