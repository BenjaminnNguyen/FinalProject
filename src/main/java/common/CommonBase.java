package common;
import static common.CT_Account.*;
import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import converter.Excel.ReadExcel;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
//import MODEL.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CommonBase {
	public WebDriver driver;
	protected int DEFAULT_TIMEOUT = 30000;
	protected int WAIT_INTERVAL = 1000;
	public int loopCount = 0;
	public final int ACTION_REPEAT = 5;

	public WebDriver openBrowser(String BrowserName, String url) {
		try {
			if (BrowserName == "FireFox") {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.get(url);
			} else if (BrowserName == "Chrome") {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.get(url);
			} else if (BrowserName == "IE") {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				driver.get(url);
			} else if (BrowserName == "Opera") {
				WebDriverManager.operadriver().setup();
				driver = new InternetExplorerDriver();
				driver.get(url);
			} else {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.get(url);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * login to system
	 * 
	 * @param user
	 * @param pass
	 */
	public void login(String user, String pass) {
		setText(ELEMENT_USERNAME_TEXTBOX, user);
		setText(ELEMENT_PASSWORD_TEXTBOX, pass);
		System.out.println("Login vao he thong voi user " + user);
		click(ELEMENT_LOGIN_BUTTON);
		waitForElementDisappear(ELEMENT_LOGIN_BUTTON);
	}

	// close browser
	public void closeBrowser() {
		driver.close();
	}

	/**
	 * quit driver if driver existed
	 * 
	 * @param dr
	 */
	public void quitDriver() {
		if (driver.toString().contains("null")) {
			System.out.print("All Browser windows are closed ");
		} else {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
	}

	public void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			error.getCause().toString();
		}
	}

	public void waitForElement(Object locator, WebDriver driver) {
		try {
			WebElement el = getElementPresent(locator);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(el));

		} catch (TimeoutException var2) {
			throw new AssertionError(var2.getMessage());
		}
	}

	public void waitPageLoaded(String value) {
		try {
			Thread.sleep(Long.parseLong(value));
		} catch (Exception e) {

		}
	}

	/**
	 * Open page
	 * 
	 * @param pageUrl
	 * @param driver
	 */
	public void navigateURL(String pageUrl, WebDriver driver) {
		driver.get(pageUrl);
		pause(1000);
	}

	public void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifyContain(String s1, String s2) {
		if (s1.trim() == null || s1.trim() == "" || s2.trim() == null || s2.trim() == "") {

			Assert.fail("One of strings is null");
		} else {
			Assert.assertFalse(s1.contains(s2));
		}
	}

	// user via object Text
	public String getElementText(Object locator) {
		return "";
	}

	public WebElement getElement(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		WebElement elem = null;
		try {
			elem = driver.findElement(by);
		} catch (NoSuchElementException e) {
			checkCycling(e, 10);
			pause(WAIT_INTERVAL);
			getElement(locator);
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 10);
			pause(WAIT_INTERVAL);
			getElement(locator);
		}
		return elem;
	}

	public void checkCycling(Exception e, int loopCountAllowed) {
		System.out.println("Co exception xay ra: " + e.getClass().getName());
		if (loopCount > loopCountAllowed) {
			Assert.fail("Qua thoi gian nhung khong thay hoac thay doi tuong " + e.getMessage());
		}
		System.out.println("Lap lai lan thu " + loopCount);
		loopCount++;
	}

	private static void info(String string) {
		System.out.println(string);

	}

	public WebElement getElementPresent(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1] : 1;
		int notDisplayE = opParams.length > 2 ? opParams[2] : 0;
		for (int tick = 0; tick < timeout / WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2) {
				elem = getElement(locator);
			} else {
				elem = getDisplayedElement(locator);
			}
			if (null != elem)
				return elem;
			pause(WAIT_INTERVAL);
		}
		if (isAssert == 1) {
			info("date");
			assert false : ("Qua thoi gian " + timeout + "ma khong tim thay doi tuong " + locator);
			quitDriver();
		}
		return null;
	}

	public void check(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0] : 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = getElementPresent(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
			boolean a = element.getAttribute("class").contains("ui-state-active");
			if (!element.isSelected() && !a) {
				actions.click(element).perform();
			} else {
				info("Element " + locator + " is already checked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			check(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void uncheck(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0] : 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = getElementPresent(locator, DEFAULT_TIMEOUT, 1, notDisplayE);

			if (element.isSelected()) {
				actions.click(element).perform();
			} else {
				info("Element " + locator + " is already unchecked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			pause(1000);
			uncheck(locator);
		} finally {
			loopCount = 0;
		}
	}

	public void doubleClickOnElement(Object locator) {
		Actions actions = new Actions(driver);
		try {
			WebElement element = getElementPresent(locator);
			actions.doubleClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			pause(1000);
			doubleClickOnElement(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * 
	 * @param locator
	 * @param safeToSERE
	 * @param opParams
	 */
	public void mouseOver(Object locator, boolean safeToSERE, Object... opParams) {
		WebElement element;
		Actions actions = new Actions(driver);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			if (safeToSERE) {
				for (int i = 1; i < ACTION_REPEAT; i++) {
					info("Thuc hien mouserover repeat lan thu " + i);
					element = getElementPresent(locator, 2000, 0, notDisplay);
					info("Doi tuong " + element);
					if (element == null) {
						pause(WAIT_INTERVAL);
					} else {
						info("Thuc hien action");
						actions.moveToElement(element).build().perform();
						break;
					}
				}
			} else {
				element = getElementPresent(locator);
				actions.moveToElement(element).build().perform();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			mouseOver(locator, safeToSERE);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * accept unexpected alert
	 */
	public void acceptAlert() {
		try {
			driver.switchTo().alert().accept();
			;

		} catch (org.openqa.selenium.NoAlertPresentException ex) {
			info("No Alert present");
			;
		}
	}

	/**
	 * accept unexpected alert
	 */
	public String getAlertText() {
		String alert = "";
		try {
			alert = driver.switchTo().alert().getText();

		} catch (org.openqa.selenium.NoAlertPresentException ex) {
			info("No Alert present");
			;
		}
		return alert;
	}

	/**
	 * dismiss unexpected alert
	 */
	public void DismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
			;

		} catch (org.openqa.selenium.NoAlertPresentException ex) {
			info("No Alert present");
			;
		}
	}

	/**
	 * switch to a frame
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void switchToFrame(Object locator, Object... opParams) {
		info("Switch to frame " + locator);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			driver.switchTo().frame(getElementPresent(locator, DEFAULT_TIMEOUT, 1, notDisplay));
		} catch (Exception e) {
			switchToFrame(locator, notDisplay);
		}
	}

	public String getAttribute(Object locator, String att, int... opParams) {
		try {
			return getElementPresent(locator, opParams).getAttribute(att);
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	public void back(WebDriver driver) {
		pause(1000);
		driver.navigate().back();
	}

	public void verifyEmpty(Object object) {
		WebElement element = getElement(object);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
		Assert.assertEquals(element.getAttribute("value").toString().trim(), "");

	}

	public void clearText(Object object) {
		WebElement element = getElement(object);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			// WebElement element = getElementPresent(locator, 10000, 0);
			if (element != null) {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.clear();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			clearText(element);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			clearText(element);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			clearText(element);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * input data to element
	 * 
	 * @param locator
	 * @param value
	 * @param validate
	 */
	public void setText(Object object, String value) {
		// WebElement element = getElement(object);
		WebElement element = getElementPresent(object, 30000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			// WebElement element = getElementPresent(locator, 10000, 0);
			if (element != null) {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.clear();
				element.sendKeys(value);
			} else {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.sendKeys(value);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			setText(element, value);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			setText(element, value);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			setText(element, value);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get value of element in web page
	 * 
	 * @param locator
	 * @return
	 */
	public String getValue(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		try {
			return getElementPresent(locator, DEFAULT_TIMEOUT, 1, notDisplay).getAttribute("value");
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get a display element in web page
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getDisplayedElement(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());// co phai 1 kieu dl nao do k
		WebElement e = null;
		try {
			if (by != null)
				e = driver.findElement(by);
			if (e != null) {
				if (isDisplay(by))
					return e;
			}
		} catch (NoSuchElementException ex) {
			checkCycling(ex, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		} finally {
			loopCount = 0;
		}
		return null;
	}

	/**
	 * checking an element is displayed in web page
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try {
			if (e != null)
				bool = e.isDisplayed();
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			isDisplay(locator);
		} finally {
			loopCount = 0;
		}
		return bool;
	}

	/**
	 * click on an element
	 * 
	 * @param locator
	 * @param opParams
	 */
	public void click(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		WebElement element = getElement(locator);
		Actions actions = new Actions(driver);
		try {
			actions.click(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(element, notDisplay);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(element, notDisplay);
		} catch (NoSuchElementException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			click(element, notDisplay);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * get text of element
	 * 
	 * @param locator
	 * @return
	 */
	public String getText(Object locator) {
		WebElement element = null;
		try {
			element = getElementPresent(locator);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return getText(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * compare 2 string
	 * 
	 * @param s1
	 * @param s2
	 */
	public void verifyCompare(String s1, String s2) {
		if (s1 != "" && s1 != null && s2 != null && s2 != "") {
			Assert.assertFalse(!s1.equalsIgnoreCase(s2), "So sanh khong bang nhau: " + s1 + " va " + s2);
		} else if ((s1 == "" || s1 == null) && (s2 == "" || s2 == null)) {
			info("2 truong du lieu can so sanh deu null");
		} else {
			Assert.fail("Du lieu so sanh co 1 truong bi null");
		}
	}

	/*
	 * compare 2 number
	 * 
	 * @param s1
	 * 
	 * @param s2
	 */
	public void verifyCompareNumber(String s1, String s2) {
		if (s1 != "" && s1 != null && s2 != null && s2 != "") {
			try {
				double n1 = Double.parseDouble(s1);
				double n2 = Double.parseDouble(s2);
				info("Compare " + s1 + " with " + s2);
				Assert.assertEquals(n2, n1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if ((s1 == "" || s1 == null) && (s2 == "" || s2 == null)) {
			info("2 truong du lieu can so sanh deu null");
		} else {
			Assert.fail("Du lieu so sanh co 1 truong bi null");
		}
	}

	public boolean verifyTextPresent(String text) {
		if (driver.getPageSource().contains(text)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * verifyElementText
	 * 
	 * @param s1
	 * @param s2
	 */
	public Boolean verifyElementText(Object locator, String text) {
		Boolean check = false;

		try {
			WebElement element = getElementPresent(locator);
			if (element.getText() == text) {
				check = true;
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			pause(WAIT_INTERVAL);
			return false;
		} finally {
			loopCount = 0;
		}
		return check;
	}

	public void switchNewTab(int... index) {
		int tab = index.length > 0 ? index[0] : 1;
		List<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(tab));
	}

	public void verifyCompareNotEqual(String s1, String s2) {
		if (s1 != "" && s1 != null && s2 != null && s2 != "") {
			Assert.assertTrue(!s1.equalsIgnoreCase(s2),
					"Pass neu 2 truong so sanh khong bang nhau: " + s1 + " va " + s2);
		} else if ((s1 == "" || s1 == null) && (s2 == "" || s2 == null)) {
			info("2 truong du lieu can so sanh deu null");
		} else {
			Assert.fail("Du lieu so sanh co 1 truong bi null");
		}
	}

	public void enter(Object locator) {
		if (locator != null) {
			WebElement e = getElementPresent(locator);
			e.sendKeys(Keys.ENTER);
		}
	}

	public void waitForElementDisappear(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		int i = 0;
		while (i < 60) {
			try {
				if (driver.findElement(by).isDisplayed()) {
					pause(1000);
					i++;
				}
			} catch (NoSuchElementException ex) {
				break;
			} catch (StaleElementReferenceException ex) {
				break;
			}
		}
		if (i == 60) {
			Assert.fail("Qua thoi gian doi tuong van display");
		}
	}

	public void selectValueFromDropdown(Object locator, String value) {
		Select dropDown = new Select(getElement(locator));
		dropDown.selectByVisibleText(value);
	}

	public void selectFromDropdownByIndex(Object locator, String index) {
		Select dropDown = new Select(getElement(locator));
		dropDown.selectByIndex(Integer.parseInt(index.trim()));
	}

	public String getFirstSelectedOption(Object locator) {
		Select dropDown = new Select(getElement(locator));
		return dropDown.getFirstSelectedOption().getText();
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getListElement(Object locator) {
		By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
		List<WebElement> elementOptions;
		try {
			elementOptions = driver.findElements(by);
			return elementOptions;
		} catch (NoSuchElementException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getListElement(locator);
		} catch (StaleElementReferenceException ex) {
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getListElement(locator);
		} finally {
			loopCount = 0;
		}
		return null;
	}

	/**
	 * 
	 * @param urlText
	 * @param addParam
	 * @param column
	 * @return
	 */
	public static String getRestService(String urlText, String addParam) {
		String output = "";
		String urlT = "";
		if (urlText != "" && !urlText.contains("http")) {
			urlT = System.getProperty("serviceLink") + urlText;
		} else {
			urlT = urlText;
		}
		try {
			URL url = new URL(urlT);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			if (addParam != "") {
				String[] param = addParam.split(",");
				if (param.length > 0) {
					for (int i = 0; i < param.length; i++) {
						if (param[i] != "") {
							String[] a = param[i].split(":");
							if (a.length > 0) {
								con.setRequestProperty(a[0], a[1]);
							}
						}
					}
				}
			}
			int code = con.getResponseCode();
			info("Result Execute Services is: " + code + " " + con.getResponseMessage());
			if (code == 200) {
				InputStream in = con.getInputStream();
				output = read(in);
			} else {
				InputStream in = con.getErrorStream();
				output = read(in);
			}
			info("Output of service is: " + output);
		} catch (IOException | RuntimeException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static String read(InputStream input) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
			return buffer.lines().collect(Collectors.joining("\n"));
		}
	}

	public Response getAPI(String URL, String... value) throws UnsupportedEncodingException {
		Response res = null;
		if (value.length == 1) {
			String token = value[0];
			res = given().header("Authorization", "Bearer " + token).when().get(URL).then()
					.contentType(ContentType.JSON).log().all().extract().response();
		}
		if (value.length == 2) {
			String token = value[0];
			int status = 0;
			try {
				status = Integer.parseInt(value[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			res = given().header("Authorization", "Bearer " + token).when().get(URL).then()
					.contentType(ContentType.JSON).statusCode(status).log().all().extract().response();
		} else {
			res = given().when().get(URL).then().contentType(ContentType.JSON).log().all().extract().response();
		}
		return res;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getJSONPath(Response res) {
		String JSONPath = "";
		try {
			res.jsonPath().toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JSONPath;
	}

	public void scrollToElement(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
		WebElement element = getElementPresent(locator, DEFAULT_TIMEOUT, 1, notDisplay);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String parseStringToObject(String xpathOption, String option) {
		String xpath = xpathOption.replaceAll("&option", option);
		return xpath;
	}

	/**
	 * get absolute path of file
	 * 
	 * @param relativeFilePath
	 * @return
	 */
	public String getAbsoluteFilePath(String relativeFilePath) {
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + relativeFilePath;
		return absolutePath;
	}

	/**
	 * 
	 * @param file
	 * @param filePath
	 */
	public void uploadFile(Object file, String filePath) {
		WebElement e = getElement(file);
		String plaForm = System.getProperty("platForm") != null ? System.getProperty("platForm") : "Window";
		if (plaForm.equalsIgnoreCase("Window")) {
			e.sendKeys(getAbsoluteFilePath("\\file_to_upload\\" + filePath));
		} else {
			e.sendKeys(getAbsoluteFilePath("/file_to_upload/" + filePath));
		}
	}

	/* Evaluates the given expression and returns the result in String format. */
	public String evaluate(String expression) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String result = null;
		try {
			result = engine.eval(expression).toString();
			if (result.endsWith(".0")) {
				info("Ket qua cua bieu thuc " + expression + " la: " + result + " Return phan nguyen");
				for (int i = 0; i < result.length(); i++) {
					if (result.charAt(i) == '.') {
						return result.substring(0, i);
					}
				}
			}
			info("Ket qua cua bieu thuc " + expression + " la: " + result);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void verifyNotContains(String s1, String s2) {
		if (s1 != null && s2 != null && s2.contains(s1)) {
			info("Fail do chuoi " + s2 + "  van chua chuoi " + s1);
			Assert.assertFalse(s2.contains(s1));
		}
	}
	public void verifyAttributeValue(Object obj, String attribute, String value) {
		WebElement e = getElementPresent(obj);
		Assert.assertTrue(e.getAttribute(attribute).contains(value)); 

	}

}
