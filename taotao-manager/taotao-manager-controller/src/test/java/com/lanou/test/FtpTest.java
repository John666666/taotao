package com.lanou.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.taotao.util.FtpUtil;
import com.taotao.util.IDUtils;

public class FtpTest {

	@Test
	public void upload() {
		// 文件转换成输入流（字节输入流）
		try {
			File f = new File("C:"+File.separator+"2.png");
			FileInputStream in  = new FileInputStream
					(f);
			//basePath   一定要提前建好
			boolean result = FtpUtil.uploadFile("10.90.85.233", 21, "Administrator", "lisai$123",
					"/taotao", dateStr(),
					IDUtils.genImageName()+".png", in);
			System.out.println(result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String dateStr() {
		Date  now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		// 获取年
		int year = cal.get(Calendar.YEAR);
		// 获取月
		int month = cal.get(Calendar.MONTH)+1;
		//获取天
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return year+"/"+month+"/"+day;
	}
	
	@Test
	public void test02() {
		//1546565796281758
		//1546565814306598
		System.out.println(dateStr());
		System.out.println(IDUtils.genImageName());
	}
	
}
