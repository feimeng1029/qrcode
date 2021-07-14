package com.arthas.qrcode.main;

import java.io.File;

import com.arthas.qrcode.parse.QRCodeParseHandle;
import com.arthas.qrcode.screen.RobotScreen;

/**
 * 截屏函数
 * @author admin
 *
 */
public class ScreenMain {

	public static void main(String[] args) {
		
		String tmpPic = "C:\\github\\qrcode\\test\\tmp\\template1.jpg";
		
		int x = 20;
		int y = 67;
		//int x = 40;
		//int y = 205;
		int len = 0;
		int width = 560;
		int hight = 560;
		
		while(true) {
			long s1 = System.currentTimeMillis();
			try {
				
				// 1.screen 
				RobotScreen.execute(x, y, width, hight, tmpPic);
				// 2.decode qrcode
				// 1.parse qrcode to file
				String outputDir= "C:\\github\\qrcode\\test\\result";
				QRCodeParseHandle.decodeQRCode(tmpPic, outputDir);
				
			} catch(Exception e) {
				System.out.println("error decode continue ... " + e.getMessage());
				e.printStackTrace();
			}
			long s2 = System.currentTimeMillis();
			System.out.println(s2-s1);
		}
		
		
	}
	
}
