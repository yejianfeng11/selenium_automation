package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class operate {
	public WebDriver driver = null;

	public WebDriverWait wait = null;

	JavascriptExecutor javascriptExecutor = null;

	Actions action = null;

	/**
	 * 构造函数
	 * 
	 * @param d
	 */
	public operate(WebDriver driver) {
		this.driver = driver;
		int waitTime = Integer.parseInt(xzcConfig.getWaitElementLoadTime());
		this.wait = new WebDriverWait(driver, waitTime);
		javascriptExecutor = (JavascriptExecutor) this.driver;
		action = new Actions(driver);
	}

	/**
	 * 等待页面加载完毕
	 */
	public void waitPageLoad() throws Exception {
		String timeout = xzcConfig.getWaitPageLoadTime();
		driver.manage().timeouts()
				.pageLoadTimeout(Long.parseLong(timeout), TimeUnit.SECONDS);
	}

	/**
	 * 通过id点击相应元素
	 * 
	 * @param id
	 */
	public void id_click(String id) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
			Thread.sleep(1 * 1000);
			driver.findElement(By.id(id)).click();
		} catch (Exception e) {
			System.out.println("id为：" + id + "的元素不可点击,请修改id！");
		}
	}

	/**
	 * 点击元素
	 * 
	 * @param by
	 */
	public void click(By by) throws Exception {
		waitElementToBeClickable(by);
		WebElement element = driver.findElement(by);
		Thread.sleep(400);
		element.click();
	}

	/**
	 * 双击元素
	 * 
	 * @param by
	 * @throws Exception
	 */
	public void doubleclick(By by) throws Exception {
		waitElementToBeClickable(by);
		Actions action = new Actions(driver);
		Thread.sleep(500);
		action.doubleClick(driver.findElement(by)).perform();
	}

	/**
	 * 通过文本选择
	 * 
	 * @param by
	 * @param text
	 */
	public void selectByText(By by, String text) throws Exception {
		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(text);
	}

	/**
	 * 获取文本
	 * 
	 * @param by
	 * @return
	 */
	public String getText(By by) throws Exception {
		isElementVisible(by);
		WebElement element = driver.findElement(by);
		if (null != element) {
			return element.getText();
		}
		return null;
	}

	/**
	 * 输入值
	 * 
	 * @param by
	 * @param value
	 * @return
	 */
	public void setValue(By by, String value) throws Exception {
		isElementVisible(by);
		WebElement element = driver.findElement(by);
		element.clear();
		Thread.sleep(500);
		element.sendKeys(value);
	}

	/**
	 * 判断元素对象在一定的时间内可见，超时则认为不可见
	 * 
	 * @param by
	 */
	public boolean isElementVisible(By by) {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			System.out.println("[" + by + "],元素不可见!");
			return false;
		}

	}

	/**
	 * 判断元素对象是否存在
	 * 
	 * @param by
	 * @return
	 */
	public boolean isElementExist(By by) {
		try {
			Thread.sleep(1000);
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 通过id输入文字
	 * 
	 * @param id
	 * @param input
	 */
	public void setValueById(String id, String input) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			driver.findElement(By.id(id)).clear();
			Thread.sleep(1 * 1000);
			driver.findElement(By.id(id)).sendKeys(input);
		} catch (Exception e) {
			System.out.println("id为：" + id + "的元素无法输入，请确认元素是否可以直接输入！");
		}
	}

	/**
	 * 通过xpath点击相应元素
	 * 
	 * @param xpath
	 */
	public void xpath_click(String xpath) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			Thread.sleep(1 * 1000);
			driver.findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			System.out.println("Xpath为：" + xpath + "的元素不可以点击，请修改！");
		}
	}

	/**
	 * 通过xpath双击相应元素
	 * 
	 * @param xpath
	 */
	public void xpath_doubleclick(String xpath) {
		try {
			Actions action = new Actions(driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.xpath(xpath)));
			Thread.sleep(1 * 1000);
			WebElement s = driver.findElement(By.xpath(xpath));
			action.doubleClick(s).perform();
		} catch (Exception e) {
			System.out.println("xpath为：" + xpath + "的元素无法双击，请确认元素是否存在！");
		}
	}

	/**
	 * 刷新页面
	 */
	public void refreshPage() {
		driver.navigate().refresh();
	}

	/**
	 * 根据frameId切换frame
	 * 
	 * @param frameId
	 */
	public void switchToFrame(String frameId) throws Exception {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(frameId);
	}

	/**
	 * 获取当前driver驱动的所有窗口的handle然后进行比对筛选，如果窗口的title符合预期 则切换，并返回true，反之则返回false
	 * 
	 * @param windowTitle
	 * @return
	 */
	public boolean switchToWindow(String windowTitle) throws Exception {
		boolean flag = false;
		String currentHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String s : handles) {
			if (s.equals(currentHandle))
				continue;
			else {
				driver.switchTo().window(s);
				if (driver.getTitle().contains(windowTitle)) {
					flag = true;
					break;
				} else
					continue;
			}
		}
		return flag;
	}

	/**
	 * 将鼠标移动到指定元素上
	 * 
	 * @param by
	 * @throws Exception
	 */
	public void moveToElement(By by) throws Exception {
		WebElement webElement = driver.findElement(by);
		action.moveToElement(webElement).perform();
	}

	/**
	 * 回到主frame
	 * 
	 * @throws Exception
	 */
	public void switchtodefault() throws Exception {
		driver.switchTo().defaultContent();
	}

	/**
	 * 通过xpath输入文字
	 * 
	 * @param xpath
	 * @param input
	 */
	public void xpath_sendkeys(String xpath, String input) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.xpath(xpath)));
			Thread.sleep(1000);
			driver.findElement(By.xpath(xpath)).clear();
			Thread.sleep(1 * 1000);
			driver.findElement(By.xpath(xpath)).sendKeys(input);
		} catch (Exception e) {
			System.out.println("xpath为：" + xpath + "的元素无法输入，请确认元素是否可以直接输入！");
		}
	}

	/**
	 * 等待，直到元素对象可点击
	 * 
	 * @param by
	 */
	public void waitElementToBeClickable(By by) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * 根据文本内容点击DIV
	 * 
	 * @param text
	 */
	public void clickDivByText(String text) throws Exception {
		By by = By.xpath("//div[text()='" + text + "']");
		waitElementToBeClickable(by);
		WebElement element = driver.findElement(by);
		element.click();
	}

	/**
	 * 根据文本内容点击
	 * 
	 * @param text
	 * @throws Exception
	 */
	public void clickByText(String text) throws Exception {
		By by = By.xpath("//*[text()='" + text + "']");
		waitElementToBeClickable(by);
		WebElement element = driver.findElement(by);
		element.click();
	}

	/**
	 * 根据文本内容点击超链接
	 * 
	 * @param text
	 * @throws Exception
	 */
	public void clickAByText(String text) throws Exception {
		By by = By.xpath("//a[text()='" + text + "']");
		waitElementToBeClickable(by);
		WebElement element = driver.findElement(by);
		element.click();
		// log.info("Click on [" + by + "]");
	}

	/**
	 * 模拟键盘按键
	 * 
	 * @param key
	 */
	public void sendKeys(Key key) {
		switch (key) {
		case PAGE_DOWN:
			action.sendKeys(Keys.PAGE_DOWN).perform();
			break;
		case PAGE_UP:
			action.sendKeys(Keys.PAGE_UP).perform();
			break;
		case END:
			action.sendKeys(Keys.END).perform();
			break;
		case ENTER:
			action.sendKeys(Keys.ENTER).perform();
			break;
		case ESCAPE:
			action.sendKeys(Keys.ESCAPE).perform();
			break;
		case BACK_SPACE:
			action.sendKeys(Keys.BACK_SPACE).perform();
			break;
		case HOME:
			action.sendKeys(Keys.HOME).perform();
			break;
		case EQUALS:
			action.sendKeys(Keys.EQUALS).perform();
			break;
		case ALT:
			action.sendKeys(Keys.ALT).perform();
			break;
		case CONTROL:
			action.sendKeys(Keys.CONTROL).perform();
			break;
		case TAB:
			action.sendKeys(Keys.TAB).perform();
			break;
		case CANCEL:
			action.sendKeys(Keys.CANCEL).perform();
			break;
		case F5:
			action.sendKeys(Keys.F5).perform();
			break;
		case LEFT:
			action.sendKeys(Keys.LEFT).perform();
			break;
		case RETURN:
			action.sendKeys(Keys.RETURN).perform();
			break;
		case RIGHT:
			action.sendKeys(Keys.RIGHT).perform();
			break;
		case UP:
			action.sendKeys(Keys.UP).perform();
			break;
		case DELETE:
			action.sendKeys(Keys.DELETE).perform();
			break;
		case DOWN:
			action.sendKeys(Keys.DOWN).perform();
			break;
		default:
			break;
		}
	}

	/**
	 * 打开外部文件
	 */
	public static void openExe(String x) {
		// Runtime rn = Runtime.getRuntime();
		// Process p = null;
		try {
			Runtime.getRuntime().exec(x);
			// p.waitFor();
			// Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("打开文件失败，请确认文件路径");
		}
	}

	public  void record_prj(String prj, String directory)
			throws IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File file = new File(directory);
		FileOutputStream fos = new FileOutputStream(file, true);
		String content = "项目创建成功：" + prj + " " + df.format(new Date()) + "\n";
		fos.write(content.getBytes());
		fos.flush();
		fos.close();
	}

	/**
	 * 执行js代码
	 * 
	 * @param js
	 */
	public void executeJs(String js) throws Exception {
		if (null != js && js.length() > 0) {
			javascriptExecutor.executeScript(js);
		}
	}

	/**
	 * 执行js代码
	 * 
	 * @param js
	 *            : return_js
	 * 
	 * @return Object
	 */
	public Object execJSR(String js) {
		return ((JavascriptExecutor) this.driver).executeScript(js);
	}
}
