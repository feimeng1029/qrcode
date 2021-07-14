package com.arthas.qrcode.screen;

import java.io.File;

import javax.swing.ImageIcon;

public class UnHandleQrCode {

	public static void main(String[] args) {
		File f = new File("C:\\github\\qrcode\\test\\result");
		
		File[] fileList = f.listFiles();
		
		int index = 0;
		
		for(int i=1;i<=3868;i++) {

				for(File f1 : fileList) {
					if(f1.getName().equals(String.valueOf(i))) {
						index++;
					}
						
				}
				if(index == 0) {
					System.out.print(i+"|");	
				}
				index = 0;
				
		}
	}
}
