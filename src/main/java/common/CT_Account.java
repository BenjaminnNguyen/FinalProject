package common;

import org.openqa.selenium.By;

public class CT_Account {
	public static By ELEMENT_USERNAME_TEXTBOX = By.xpath("//*[@id='UserName']|//*[@class='user-name']/input");
	public static By ELEMENT_PASSWORD_TEXTBOX = By.xpath("//*[@id='PassWord']|//*[@class='password']/input");
	public static By ELEMENT_LOGIN_BUTTON = By.xpath("//*[@value='ĐĂNG NHẬP' or @value='Log in' or @value='Login']|//button[contains(text(),'Đăng nhập') or text()='Login']");

}
