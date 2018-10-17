package com.cases;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.Key;
import com.example.operate;
import com.example.xzcConfig;

public class qqjf {
	WebDriver driver = null;
	WebElement msg_24g = null;
	Select sle = null;

	@BeforeTest
	public void setUp() {
		// 打开谷歌浏览器
		System.setProperty("webdriver.chrome.driver", "./res/chromedriver2.exe");
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("no-sandbox");
		options.addArguments("disable-extensions");
		options.addArguments("no-default-browser-check");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@Test
	public void test() throws Exception {
		operate qqjf = new operate(driver);
		String qqjf_no = null;
		String qqjfhomePageURL = xzcConfig.getqqjfhomePageURL();
		String homePageURL = xzcConfig.gethomePageURL();
		String qqjfid = xzcConfig.getqqjfid();
		String qqjfpwd = xzcConfig.getqqjfpwd();

		driver.get(qqjfhomePageURL);
		qqjf.clickByText("登录");
		qqjf.setValue(By.id("loginId"), qqjfid);
		qqjf.setValue(By.id("loginPwd"), qqjfpwd);
		qqjf.click(By.xpath("/html/body/div[3]/div/div/div/div[3]"));
		qqjf.waitElementToBeClickable(By.xpath("//*[text()='发起项目']"));
		qqjf.clickByText("发起项目");
		qqjf.click(By
				.xpath("//*[@id=\"container\"]/div[2]/div[2]/div[2]/div[2]/div/div/div/div/a[1]/div"));
		Thread.sleep(2 * 1000);
		qqjf.clickByText(" 立即申请");
		qqjf.click(By.xpath("//*[@id=\"loaner\"]/div/div[2]/div/div"));
		Thread.sleep(500);
		qqjf.click(By.xpath("/html/body/div[2]/div/div/div/ul/li[4]"));
		qqjf.setValueById("applyAmount", "80000");
		qqjf.click(By
				.xpath("//*[@id=\"borrowNeed\"]/div/div/div[2]/div[1]/div[2]/div/div[1]/div/div/div/span/div"));
		qqjf.click(By
				.xpath("/html/body/div[3]/div/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[1]/div"));
		qqjf.click(By
				.xpath("//*[@id=\"borrowNeed\"]/div/div/div[2]/div[1]/div[2]/div/div[2]/div/div/div/span/div"));
		qqjf.click(By
				.xpath("/html/body/div[4]/div/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[7]/div"));
		qqjf.click(By.id("bankTotalSingleModel"));
		Thread.sleep(500);
		qqjf.click(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]"));
		qqjf.click(By
				.xpath("//*[@id=\"borrowDetail\"]/div/div[1]/div[2]/div[3]/div[2]/div/div[2]"));
		Thread.sleep(500);
		qqjf.click(By.xpath("/html/body/div[6]/div/div/div/ul/li[1]"));
		qqjf.clickByText("同还款银行");
		qqjf.click(By.id("addGuaranteeBtn"));
		qqjf.click(By
				.xpath("//*[@id=\"borrowDetail\"]/div/div[2]/div[2]/div/div/div[3]/div[1]/div/div/div[2]/div/div[1]/div[1]"));
		Thread.sleep(500);
		qqjf.click(By.xpath("/html/body/div[7]/div/div/div/ul/li[17]"));
		qqjf.click(By
				.xpath("//*[@id=\"borrowDetail\"]/div/div[2]/div[2]/div/div/div[3]/div[1]/div/div/div[2]/div/div[1]/div[2]"));
		Thread.sleep(500);
		qqjf.click(By.xpath("/html/body/div[8]/div/div/div/ul/li[1]"));
		qqjf.click(By
				.xpath("//*[@id=\"borrowDetail\"]/div/div[2]/div[2]/div/div/div[3]/div[1]/div/div/div[2]/div/div[1]/div[3]"));
		Thread.sleep(500);
		qqjf.click(By.xpath("/html/body/div[9]/div/div/div/ul/li[11]"));
		qqjf.setValue(By.id("pawnDetail[0].addDetail"), "1");
		qqjf.click(By.xpath("//*[@id=\"checkboxName\"]/div/label[1]/span[1]"));
		qqjf.click(By.id("addGuaranteeConfirmBtn"));
		qqjf.clickByText("提 交");
		Thread.sleep(8000); // 等待后台创建项目
		qqjf.clickAByText("查看详情");
		qqjf_no = qqjf
				.getText(By
						.xpath("//*[@id=\"container\"]/div[2]/div[2]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]"));
		System.out.println(qqjf_no);
		operate.openExe("./res/test2login.exe");
		driver.get(homePageURL);
		qqjf.switchtodefault();
		Thread.sleep(2000);
		// 进入项目列表页面
		qqjf.click(By.id("HomeTabLink"));
		Thread.sleep(1000);
		qqjf.click(By.id("ProjectManagement"));
		qqjf.click(By.id("ubg_project"));
		qqjf.switchToFrame("contentIFrame0");
		qqjf.setValue(By.id("crmGrid_findCriteria"), qqjf_no + Keys.ENTER);
		Thread.sleep(1000);
		qqjf.switchToFrame("contentIFrame0");
		qqjf.click(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[2]"));
		qqjf.switchToFrame("contentIFrame1");
		qqjf.click(By.id("ubg_custname"));
		qqjf.sendKeys(Key.DELETE);
		qqjf.setValue(By.id("ubg_custname_ledit"), "叶剑锋" + Keys.ENTER);
		Thread.sleep(500);
		qqjf.sendKeys(Key.ENTER);
		qqjf.click(By.id("ubg_quote"));
		qqjf.setValue(By.id("ubg_quote_i"), "6" + Keys.ENTER);
		qqjf.switchtodefault();
		qqjf.click(By
				.id("ubg_project|NoRelationship|Form|Mscrm.Form.ubg_project.Save"));
		Thread.sleep(5000);
		qqjf.switchToFrame("contentIFrame1");
		qqjf.clickAByText(qqjf_no + "-1");
		Thread.sleep(4000);
		qqjf.switchToFrame("contentIFrame1");
		// 填写用款计划
		qqjf.click(By.id("ubg_costmoney"));
		qqjf.setValue(By.id("ubg_costmoney_i"), "10000");
		qqjf.click(By.id("ubg_unit"));
		Thread.sleep(500);
		qqjf.selectByText(By.id("ubg_unit_i"), "按天");
		qqjf.click(By.id("ubg_actualborrower"));
		qqjf.setValue(By.id("ubg_actualborrower_i"), "叶剑锋");
		qqjf.click(By.id("ubg_useoffund"));
		qqjf.selectByText(By.id("ubg_useoffund_i"), "其他");
		qqjf.click(By.id("ubg_repaymentorigin"));
		qqjf.selectByText(By.id("ubg_repaymentorigin_i"), "其他");
		qqjf.click(By.id("ubg_province"));
		qqjf.setValue(By.id("ubg_province_ledit"), "浙江" + Keys.ENTER);
		Thread.sleep(500);
		qqjf.sendKeys(Key.ENTER);
		qqjf.click(By.id("ubg_city"));
		qqjf.setValue(By.id("ubg_city_ledit"), "台州" + Keys.ENTER);
		Thread.sleep(500);
		qqjf.sendKeys(Key.ENTER);
		qqjf.click(By.id("ubg_district"));
		qqjf.setValue(By.id("ubg_district_ledit"), "三门" + Keys.ENTER);
		Thread.sleep(500);
		qqjf.sendKeys(Key.ENTER);
		qqjf.moveToElement(By.id("capitalpath_gridBar"));
		qqjf.click(By.id("capitalpath_addImageButtonImage"));
		Thread.sleep(2000);
		// 填写资金路径
		qqjf.switchToFrame("NavBarGloablQuickCreate");
		if (!qqjf.isElementVisible(By.id("ubg_pathorder_i"))) {
			qqjf.click(By.id("ubg_pathorder"));
		}
		qqjf.setValue(By.id("ubg_pathorder_i"), "1");
		qqjf.click(By.id("ubg_openaccountorganization"));
		qqjf.selectByText(By.id("ubg_openaccountorganization_i"), "银行");
		qqjf.click(By.id("ubg_account"));
		qqjf.setValue(By.id("ubg_account_i"), "123321");
		qqjf.click(By.id("ubg_organizationname"));
		qqjf.setValue(By.id("ubg_organizationname_i"), "杭州工商银行");
		qqjf.click(By.id("ubg_organizationname2"));
		qqjf.setValue(By.id("ubg_organizationname2_i"), "杭州工商银行支行");
		qqjf.click(By.id("ubg_accountname"));
		qqjf.setValue(By.id("ubg_accountname_i"), "66668888");
		qqjf.switchtodefault();
		qqjf.click(By
				.id("globalquickcreate_save_button_NavBarGloablQuickCreate"));
		Thread.sleep(2000);
		qqjf.switchToFrame("contentIFrame1");
		// 填写费用明细
//		qqjf.moveToElement(By.id("feedetails_gridBar"));
//		qqjf.click(By.id("feedetails_addImageButtonImage"));
//		Thread.sleep(2000);
//		qqjf.switchToFrame("NavBarGloablQuickCreate");
//		qqjf.setValue(By.id("ubg_legalcontract_ledit"), "南通盈华" + Keys.ENTER);
//		Thread.sleep(1000);
//		qqjf.sendKeys(Key.ENTER);
//		Thread.sleep(1000);
//		qqjf.click(By.id("ubg_legalrole"));
//		qqjf.selectByText(By.id("ubg_legalrole_i"), "服务");
//		qqjf.click(By.id("ubg_paymentdetailtype"));
//		qqjf.selectByText(By.id("ubg_paymentdetailtype_i"), "咨询服务费");
//		qqjf.click(By.id("ubg_sum"));
//		qqjf.setValue(By.id("ubg_sum_i"), "1600");
//		qqjf.switchtodefault();
//		qqjf.click(By
//				.id("globalquickcreate_save_button_NavBarGloablQuickCreate"));
//		qqjf.switchtodefault();
//		Thread.sleep(2000);
//		qqjf.click(By
//				.id("ubg_projectplan|NoRelationship|Form|Mscrm.Form.ubg_projectplan.Save"));
//		Thread.sleep(2000);
//		qqjf.record_prj(qqjf_no, "./test-output/test.txt");
		qqjf.switchtodefault();
		qqjf.click(By
				.id("ubg_projectplan|NoRelationship|Form|Mscrm.Form.ubg_projectplan.SaveAndClose"));
		Thread.sleep(2000);
		qqjf.switchToFrame("contentIFrame1");
		driver.switchTo().frame("IFRAME_fileinfomation");
		// //测试环境
		qqjf.waitElementToBeClickable(By.id("filebody"));
		List<WebElement> rows = driver.findElement(By.id("filebody"))
				.findElements(By.tagName("tr"));
		int i = 1;
		for (; i < rows.size(); i++) {
			String tempxpath = "//*[@id=\"filebody\"]/tr[" + i + "]/td[3]";
			String temp = qqjf.getText(By.xpath(tempxpath));
			if (temp.equals("尽职调查报告")) {
				break;
			}
		}
		;
		String filexpath = "//*[@id=\"filebody\"]/tr[" + i
				+ "]/td[6]/div/span/label";
		qqjf.click(By.xpath(filexpath));
		Thread.sleep(5000);
		operate.openExe("./res/upload.exe");
		Thread.sleep(2000);
//		driver.quit(); // 关闭浏览器
	}

}
