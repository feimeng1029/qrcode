package com.arthas.qrcode.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class FileSplitCombinUtil {
	public static final String sourceFilePath = "C:\\github\\qrcode\\test\\target";
	public static final String splitFilePath = "C:\\github\\qrcode\\test\\spilt";
	public static final String targetFilePath = "C:\\github\\qrcode\\test\\";
	public static final String fileName = "common_command.txt";
	public static final String configFilePath = "C:\\github\\qrcode\\test\\src\\config.properties"; 
	
	public static void main(String[] args) throws Exception {
		/*
		 * 将一个文件分割为多个文件,然后再组合成一个文件
		 * 找到文件,读入一个1M的buffer中,然后写入一个Part文件中,循环此操作直到文件读取完毕
		 */

		int partFileLength = 2044;// 指定分割的子文件大小为200字节
		//splitFile(sourceFilePath, fileName, splitFilePath, partFileLength);// 将文件分割
		combineFile(splitFilePath, targetFilePath, fileName, 3, partFileLength);;// 将分割后的文件合并
	}

	/**
	 * 文件合并
	 * @param directoryPath 文件合并目录
	 * @param fileNum 文件数量
	 * @param partFileLength 文件合并大小
	 * @throws Exception
	 */
	public static void combineFile(String splitFilePath, String directoryPath, String fileName, int fileNum, int partFileLength) throws Exception {

		OutputStream eachFileOutput = null;
		eachFileOutput = new FileOutputStream(new File(directoryPath + File.separator + fileName));

		for(int i=1;i<=fileNum;i++) {
			// System.out.println("debug---");
			String fileNumber = String.valueOf(i);
			String filePath = splitFilePath + File.separator + fileNumber;

			// 循环读取文件 --> 依次写入
			InputStream eachFileInput = null;

			eachFileInput = new FileInputStream(new File(filePath));

			byte[] buffer = new byte[partFileLength];// 缓冲区文件大小为1M
			int len = 0;
			while ((len = eachFileInput.read(buffer, 0, partFileLength)) != -1) {
				/*for (int i = 0; i < len; i++) {
					System.out.println(buffer[i]);
				}*/
				eachFileOutput.write(buffer, 0, len);
			}
			
			eachFileInput.close();
		}

		eachFileOutput.close();
	}

	/**
	 * 文件拆分
	 * @param sourceFilePath 源文件目录
	 * @param fileName 源文件名称
	 * @param splitFilePath 文件拆分目录
	 * @param partFileLength 文件拆分大小
	 * @throws Exception
	 */
	public static void splitFile(String sourceFilePath, String fileName, String splitFilePath, int partFileLength)
			throws Exception {
		File sourceFile = null;
		File targetFile = null;
		InputStream ips = null;
		OutputStream ops = null;
		//OutputStream configOps = null;// 该文件流用于存储文件分割后的相关信息，包括分割后的每个子文件的编号和路径,以及未分割前文件名
		//Properties partInfo = null;// properties用于存储文件分割的信息
		byte[] buffer = null;
		int partNumber = 1;
		sourceFile = new File(sourceFilePath + File.separator + fileName);// 待分割文件
		ips = new FileInputStream(sourceFile);// 找到读取源文件并获取输入流
		//configOps = new FileOutputStream(new File(configFilePath));
		buffer = new byte[partFileLength];// 开辟缓存空间
		int tempLength = 0;
		//partInfo = new Properties();// key:1开始自动编号 value:文件路径

		while ((tempLength = ips.read(buffer, 0, partFileLength)) != -1) {
			String targetFilePath = splitFilePath + File.separator + "" +  (partNumber);// 分割后的文件路径+文件名
			//partInfo.setProperty((partNumber++) + "", targetFilePath);// 将相关信息存储进properties
			partNumber++;
			targetFile = new File(targetFilePath);
			ops = new FileOutputStream(targetFile);// 分割后文件
			ops.write(buffer, 0, tempLength);// 将信息写入碎片文件

			ops.close();// 关闭碎片文件
		}
		//partInfo.setProperty("name", sourceFile.getName());// 存储源文件名
		//partInfo.store(configOps, "ConfigFile");// 将properties存储进实体文件中
		ips.close();// 关闭源文件流
		
		System.out.println("splitFile is split file num :" + --partNumber);
	}
}