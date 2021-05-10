package excute;
<<<<<<< HEAD
=======

import org.testng.annotations.Test;
>>>>>>> 6c7a845b04087acdfabd6d1e77acd60b1136c24b
import common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public final class TS03 extends CommonBase {
	@BeforeMethod
	public void beforeMethod() {
		openBrowser("Chrome", "https://bookbuy.vn/");
		waitForPageLoaded(driver);
	}

	@AfterMethod
	public void afterMethod() {
		quitDriver(driver);
	}

	@Test
<<<<<<< HEAD
	public void TC_01() {
		waitForPageLoaded(driver);
		setText(By.id("Term"),"Dac Nhan Tam");
		click(By.xpath("//input[@id='Term']//following-sibling::div[@class='input-group-btn']/button[@class='search button-search']"));
		click(By.id("Term"));
		waitForPageLoaded(driver);}
=======
	public void TC_01_DangKyThanhCong() {
	}

>>>>>>> 6c7a845b04087acdfabd6d1e77acd60b1136c24b
	@Test
	public void TC_02_DangKyKhongThanhCong_BoTrongAll() {

	}
	@Test
	public void TC_03_DangKyKhongThanhCong_BoTrongTruongDL() {
	}

	@Test
	public void TC_04_DangKyKhongThanhCong_TrungEmail() {
	}

	@Test
	public void TC_05_DangKyKhongThanhCong_TrungUserName() {
	}

	@Test
	public void TC_06_DangKyKhongThanhCong_SaiEmail() {
	}

	@Test
	public void TC_07_DangKy_DaCoTaiKhoan_DangNhap() {
	}

	@Test
	public void TC_08_DangKy_QuenMatKhau() {
	}

}