package excute;

import org.testng.annotations.Test;
import common.CommonBase;

import java.lang.reflect.Method;

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
	public void TC_01_DangKyThanhCong() {
	}

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