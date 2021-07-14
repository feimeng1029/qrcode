package com.arthas.qrcode.main;

import java.io.File;

import com.arthas.qrcode.file.FileSplitCombinUtil;
import com.arthas.qrcode.parse.QRCodeParseHandle;
import com.arthas.qrcode.utils.BitOperationUtil;

/**
 * 内网函数 
 * 主要功能是拆分文件和二维码生成
 * @author admin
 *
 */
public class InsideMain {

	public static void main(String[] args) throws Exception {
		// 1.spilt file
		String sourceFilePath = "C:\\github\\qrcode\\test\\target";
		String fileName = "test007.txt";
		String splitFilePath = "C:\\github\\qrcode\\test\\spilt";
		
		int partFileLength = 2044;// 指定分割的子文件大小为200字节
		FileSplitCombinUtil.splitFile(sourceFilePath, fileName, splitFilePath, partFileLength);// 将文件分割
		
		// 2.make spilt file to qrcode
		File[] fileList = new File(splitFilePath).listFiles();
		
		String picType = "jpg";
		String inputDir = "C:\\github\\qrcode\\test\\qccode";
		
		for(File f : fileList) {
			QRCodeParseHandle.encoderQRCode(BitOperationUtil.getFileToByte(f), Integer.valueOf(f.getName()), inputDir, picType);
		}
		
	}
}
