package com.arthas.qrcode.main;

import java.io.File;

public class CalcUnHandleMain {

	public static void main(String[] args) {
		
		StringBuffer sb = new StringBuffer();
		
		for(int i=1;i<=4484;i++) {
			File file = new File("C:\\github\\qrcode\\test\\result");
			File[] fileList = file.listFiles();
			
			int count = 0;
			for(File f : fileList) {
				if(f.getName().equals(i+"")) {
					count ++;
				}
			}
			if(count == 0 ) {
				System.out.println(i);
				sb.append(i).append("|");
			}
		}
		System.out.println(sb.toString());
		System.out.println(sb.length());
	}
}
