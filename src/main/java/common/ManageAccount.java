package common;

import org.openqa.selenium.WebDriver;

import static common.CT_Account.*;


public class ManageAccount extends CommonBase {
	public ManageAccount (WebDriver dr) {
		driver = dr;
	}
	/**
	 * login to system
	 * @param user
	 * @param pass
	 */
	public void login(String user, String pass){
		setText(ELEMENT_USERNAME_TEXTBOX, user);
		setText(ELEMENT_PASSWORD_TEXTBOX, pass);
		System.out.println("Login vao he thong voi user " + user);
		click(ELEMENT_LOGIN_BUTTON);
		waitForElementDisappear(ELEMENT_LOGIN_BUTTON);
	}
	

}
