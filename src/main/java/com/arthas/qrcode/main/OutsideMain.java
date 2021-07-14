package com.arthas.qrcode.main;

import java.io.File;

import com.arthas.qrcode.file.FileSplitCombinUtil;
import com.arthas.qrcode.parse.QRCodeParseHandle;

/**
 * 婢舵牜缍夐崙鑺ユ殶
 * 娑撴槒顩﹂弰顖澬掗弸鎰癌缂佸鐖滈崪灞芥値楠炶埖鏋冩禒锟�
 * @author admin
 *
 */
public class OutsideMain {

	public static void main(String[] args) throws Exception {
		// 1.parse qrcode to file
		String inputDir = "C:\\github\\qrcode\\test\\qccode";
		String outputDir= "C:\\github\\qrcode\\test\\result";
//		File[] fileList = new File(inputDir).listFiles();
//		
//		for(File f : fileList) {
//			QRCodeParseHandle.decodeQRCode(f.getPath(), outputDir);
//		}
		
		// 2.combin file
		int partFileLength = 1024;// 閹稿洤鐣鹃崚鍡楀閻ㄥ嫬鐡欓弬鍥︽婢堆冪毈娑擄拷200鐎涙濡�
		String splitFilePath = outputDir;
		int fileNum = 4488;
		String fileName = "PTS-PTS_code-develop0610-2.zip";
		String directoryPath = outputDir;
		FileSplitCombinUtil.combineFile(splitFilePath, directoryPath, fileName, fileNum, partFileLength);
	}
	
}
