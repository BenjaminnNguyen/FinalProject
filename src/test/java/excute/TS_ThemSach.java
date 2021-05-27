package excute;
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 

public final class TS_ThemSach extends CommonBase { 
	@BeforeMethod 
	public void beforeMethod() { 
		openBrowser("chorme","http://113.160.133.144:20201/Admin/Login/Index/");
		waitForPageLoaded(driver);
		login("manh","manh123");}
	@AfterMethod
	public void afterMethod() {
		quitDriver();}
	@Test
	public void TC_01_ThemThanhCong() {
		navigateURL("http://113.160.133.144:20201/Admin/Book/Manage",driver);
		waitForPageLoaded(driver);
		click(By.xpath("//button[text()='Tạo mới']"));
		setText(By.id("name"),"Ten sach 1");
		setText(By.id("image"),"Anh 1");
		selectFromDropdownByIndex(By.id("pub"),"0");
		String nhaSanXuat=getFirstSelectedOption(By.id("pub"));
		selectFromDropdownByIndex(By.id("author"),"0");
		String tacGia=getFirstSelectedOption(By.id("author"));
		setText(By.id("desc"),"Mo ta 1");
		click(By.id("btnAdd"));
		pause(1000);
		String thongbaoluu=getAlertText();
		verifyCompare(thongbaoluu,"Tạo mới sách thành công!");
		acceptAlert();
		verifyElementText(By.xpath("//th[text()='Tên sách']//./../../..//tbody/tr[1]/td[1]"),"Ten sach 1");
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
		verifyCompare(thongbaoxoa,"Bạn có muốn xóa đầu sách hiện tại?");
		acceptAlert();
		pause(1000);
		String thongbaoxoathanhcong=getAlertText();
		verifyCompare(thongbaoxoathanhcong,"Xóa sách thành công!");
		acceptAlert();}
	@Test
	public void TC_02_ThemKhongThanhCong() {
		navigateURL("http://113.160.133.144:20201/Admin/Book/Manage",driver);
		waitForPageLoaded(driver);
		click(By.xpath("//button[text()='Tạo mới']"));
		waitForPageLoaded(driver);
		click(By.id("btnAdd"));
		isDisplay(By.id("name"));
		setText(By.id("name"),"Ten sach 2");
		click(By.id("btnAdd"));
		isDisplay(By.id("name"));
		setText(By.id("image"),"Anh 2");
		click(By.id("btnAdd"));
		isDisplay(By.id("image"));
		selectFromDropdownByIndex(By.id("pub"),"0");
		click(By.id("btnAdd"));
		isDisplay(By.id("pub"));
		selectFromDropdownByIndex(By.id("author"),"0");
		click(By.id("btnAdd"));
		isDisplay(By.id("author"));
		setText(By.id("desc"),"Mo ta 2");
		clearText(By.id("name"));
		click(By.id("btnAdd"));
		isDisplay(By.id("desc"));
		click(By.xpath("//div[@class='modal-header']/button[@class='close']"));
		waitForPageLoaded(driver);
		click(By.xpath("//button[text()='Tạo mới']"));
		waitForPageLoaded(driver);
		verifyEmpty(By.id("name"));
		verifyEmpty(By.id("image"));
		verifyEmpty(By.id("pub"));
		verifyEmpty(By.id("author"));
		verifyEmpty(By.id("desc"));
	}
}