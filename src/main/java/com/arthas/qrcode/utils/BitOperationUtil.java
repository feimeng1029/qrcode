package com.arthas.qrcode.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 位运算工具类
 * 
 * @author admin
 *
 */
public class BitOperationUtil {

	/**
	 * 将字节数组转换成int
	 * 
	 * @param b
	 * @return
	 */
	public static int byteArrToInt(byte[] b) {
		byte[] a = new byte[4];
		int i = a.length - 1, j = b.length - 1;
		for (; i >= 0; i--, j--) {
			// 从b的尾部(即int值的低位)开始copy数据
			if (j >= 0)
				a[i] = b[j];
			else
				// 如果b.length不足4,则将高位补0
				a[i] = 0;
		}
		// &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
		int v0 = (a[0] & 0xff) << 24;
		int v1 = (a[1] & 0xff) << 16;
		int v2 = (a[2] & 0xff) << 8;
		int v3 = (a[3] & 0xff);
		return v0 + v1 + v2 + v3;
	}

	/**
	 * int到byte[] 由高位到低位
	 * 
	 * @param i
	 *            需要转换为byte数组的整行值。
	 * @return byte数组
	 */
	public static byte[] intToByteArray(int i) {
		byte[] result = new byte[4];
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * file转换到byte数组
	 * @param file
	 * @return
	 */
	public static byte[] getFileToByte(File file) {
		byte[] by = new byte[(int) file.length()];
		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			byte[] bb = new byte[2048];
			int ch;
			ch = is.read(bb);
			while (ch != -1) {
				bytestream.write(bb, 0, ch);
				ch = is.read(bb);
			}
			by = bytestream.toByteArray();
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return by;
	}

	public static void main(String[] args) {
		int a = 10;
		System.out.println(byteArrToInt(intToByteArray(a)));
		
		List<String> instanceIdList = new ArrayList<String>();
		List<String> instanceIdList1 = new ArrayList<String>();
		instanceIdList.add("1-2");
		instanceIdList.add("1-1");
		instanceIdList.forEach(insId -> {insId = insId.split("\\-").length > 0 ? insId.split("\\-")[0] : insId;
			System.out.println(insId);
			instanceIdList1.add(insId);
		});
		 
		System.out.println(instanceIdList1);
		
	}

}
