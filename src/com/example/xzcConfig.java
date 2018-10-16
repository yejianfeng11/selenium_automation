package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 加载baseConfig.properties配置文件信息
 * 
 */
public class xzcConfig {

	/**
	 * xzcConfig.properties文件加载器
	 */
	static Properties prop = new Properties();

	static {
		/**
		 * 加载xzcConfig.properties文件
		 */
		String baseConfigPath = "./config/xzcConfig.properties";
		try {
			InputStream inputStream = new FileInputStream(baseConfigPath);
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			prop.load(bf);
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("xzcConfig.properties文件不存在！系统退出！");
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("加载xzcConfig.properties文件失败！系统退出！");
			System.exit(-1);
		}
	}

	/**
	 * 获取鑫资产测试地址
	 * 
	 * @return
	 */
	public static String gethomePageURL() {
		String homePageURL = null;
		homePageURL = prop.getProperty("homePageURL");
		if (StringUtils.isEmpty(homePageURL)) {
			System.out
					.println("获取鑫资产测试地址失败！请检查xzcConfig.properties配置文件是否正确！系统退出！");
			System.exit(-1);
		}
		return homePageURL;
	}
	/*
	 * 获取七桥金服测试环境地址
	 * 
	 * 
	 */
	public static String getqqjfhomePageURL() {
		String qqjfhomePageURL = null;
		qqjfhomePageURL = prop.getProperty("qqjfhomePageURL");
		if (StringUtils.isEmpty(qqjfhomePageURL)) {
			System.out
					.println("获取七桥金服测试地址失败！请检查xzcConfig.properties配置文件是否正确！系统退出！");
			System.exit(-1);
		}
		return qqjfhomePageURL;
	}
	/**
	 * 获取新增产品名称
	 * 
	 * @return
	 */
	public static String getpdt_Name() {
		String pdt_Name = null;
		pdt_Name = prop.getProperty("pdt_Name");
		if (StringUtils.isEmpty(pdt_Name)) {
			System.out
					.println("获取产品名称失败！请检查鑫资产Config.properties配置文件是否正确！系统退出！");
			System.exit(-1);
		}
		return pdt_Name;
	}
	/**
	 * 获取新增产品系列名称
	 * 
	 * @return
	 */
	public static String getpdtList() {
		String pdtList = null;
		pdtList = prop.getProperty("pdtList");
		if (StringUtils.isEmpty(pdtList)) {
			System.out
					.println("获取产品系列失败！请检查鑫资产Config.properties配置文件是否正确！系统退出！");
			System.exit(-1);
		}
		return pdtList;
	}
	/*
	 * 获取七桥金服账号
	 * 
	 */
	public static String getqqjfid() {
		String qqjfid = null;
		qqjfid = prop.getProperty("qqjfid");
		if (StringUtils.isEmpty(qqjfid)) {
			System.out
					.println("获取账号失败！请检查鑫资产Config.properties配置文件是否正确！系统退出！");
			System.exit(-1);
		}
		return qqjfid;
	}
	/*
	 * 获取七桥金服密码
	 * 
	 */
	public static String getqqjfpwd() {
		String qqjfpwd = null;
		qqjfpwd = prop.getProperty("qqjfpwd");
		if (StringUtils.isEmpty(qqjfpwd)) {
			System.out
					.println("获取密码失败！请检查鑫资产Config.properties配置文件是否正确！系统退出！");
			System.exit(-1);
		}
		return qqjfpwd;
	}
	/**
	 * 获得客户名称
	 * @return
	 */
	public static String getcustName() {
		String custName = null;
		custName = prop.getProperty("custName");
		if (StringUtils.isEmpty(custName)) {
				System.out.println("读取客户名称失败，使用默认客户，叶剑锋。");
				return "叶剑锋";
			}
		else 
			return custName;
		}
	/**
	 * 获得产品名称
	 * @return
	 */
	public static String getpdtName() {
		String pdtName = null;
		pdtName = prop.getProperty("pdtName");
		if (StringUtils.isEmpty(pdtName)) {
			try{
				return pdtName;
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("读取产品名称失败，使用默认产品，叶剑锋。");
			}
		}
		return "叶剑锋";
	}
	/**
	 * 读取等待页面加载时间(秒)
	 * 
	 * @return
	 */
	public static String getWaitPageLoadTime() {
		String time = prop.getProperty("waitPageLoadTime");
		if (!StringUtils.isEmpty(time)) {
			try {
				return time;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("读取等待页面加载时间(秒)失败，使用默认配置，30秒。");
			}
		}
		return "30";// 如果没有配置，默认为30秒
	}

	/**
	 * 读取等待加载元素时间(秒)
	 * 
	 * @return
	 */
	public static String getWaitElementLoadTime() {
		String time = prop.getProperty("waitElementLoadTime");
		if (!StringUtils.isEmpty(time)) {
			try {
				return time;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("读取等待加载元素时间(秒)失败，使用默认配置，10秒。");
			}
		}
		return "10";// 如果没有配置，默认为10秒
	}
}
