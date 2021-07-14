package com.arthas.qrcode.parse;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.arthas.qrcode.screen.RobotScreen;
import com.arthas.qrcode.utils.BitOperationUtil;
import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

public class QRCodeParseHandle {
	
	public static final int QRCODE_SIZE = 2048;

	public QRCodeParseHandle() {

	}

	public static void main(String[] args) throws Exception {
		
		//testMakeQRCode();
		testParseQRCode();
		
	}
	
	/**
	 * 二维码生成测试
	 */
	public static void testMakeQRCode() {
		String qcCodeDir = "C:\\github\\qrcode\\test\\spilt";
		String picType = "png";
		
		QRCodeParseHandle.encoderQRCode(BitOperationUtil.getFileToByte(new File("C:\\github\\qrcode\\test\\src\\qrcode_test.txt")), 1, qcCodeDir, picType);
	}
	
	/**
	 * 二维码解析测试
	 */
	public static void testParseQRCode() {
		String outputDir = "C:\\github\\qrcode\\test\\spilt";
		QRCodeParseHandle.decodeQRCode("C:\\github\\qrcode\\test\\qccode\\1.png", outputDir);
	}
	
	/**
	 * 生成二维码
	 * @param contentBytes 需要生成二维码的文件
	 * @param seqNo 序号--多文件组合时使用
	 * @param inputDir 二维码生成目录
	 * @param picType 图片类型--png|jpg
	 */
    public static void encoderQRCode(byte[] contentBytes, int seqNo, String inputDir, String picType) {
        try {

            Qrcode qrcodeHandler = new Qrcode();
            qrcodeHandler.setQrcodeErrorCorrect('Q');
            qrcodeHandler.setQrcodeEncodeMode('B');
            qrcodeHandler.setQrcodeVersion(40);

            byte[] head = BitOperationUtil.intToByteArray(seqNo);
            
            byte[] target = new byte[contentBytes.length + head.length];
            
            System.arraycopy(head, 0, target, 0, head.length);
            System.arraycopy(contentBytes, 0, target, head.length, contentBytes.length);
            
            int width = 67 + 12 * (40 - 1);
            int height = 67 + 12 * (40 - 1);
            
            BufferedImage bufImg = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);

            Graphics2D gs = bufImg.createGraphics();

            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, width, height);

            // 设定图像颜色 > BLACK
            gs.setColor(Color.BLACK);

            // 设置偏移量 不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容 > 二维码
            if (target.length > 0 && target.length <= QRCODE_SIZE) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(target);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                System.err.println("QRCode content bytes length = "
                        + target.length + " not in [ 0,"+QRCODE_SIZE+" ]. ");
            }

            gs.dispose();
            bufImg.flush();

            File imgFile = new File(inputDir + File.separator + seqNo + "." + picType);

            // 生成二维码QRCode图片
            ImageIO.write(bufImg, picType, imgFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 二维码解析
     * 没有输出的文件名，文件名是根据拆分文件的前2个字节来定义的
     * @param fileName 二维码的图片 
     * @param outPutDir 要输出的文件路径
     */
	public static void decodeQRCode(String fileName, String outputDir) {
		QRCodeDecoder decoder = new QRCodeDecoder();
		File imageFile = new File(fileName);

		BufferedImage image = null;

		try {

			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.out
					.println("GucasQRCodeDecoder.execute() ImageIO.read Error: "
							+ fileName + " ... " + e.getMessage());
			// executeException(imageFile);
			return;
		}

		byte[] b = decoder.decode(new J2SEImageGucas(image));

		byte[] head = Arrays.copyOfRange(b, 0, 4);
		int headName = BitOperationUtil.byteArrToInt(head);
		
		File file = new File("C:\\github\\qrcode\\test\\result\\"+headName);
		if(file.exists()) {
			System.out.println("exists headname:=========="+fileName + ":" + headName);
			return;
		}
		
		System.out.println("parse headname:=========="+fileName + ":" + headName);
		
		byte[] content = Arrays.copyOfRange(b, 4, b.length);

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(
					new File(outputDir + File.separator + headName));
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out
					.println("GucasQRCodeDecoder.execute() FileNotFoundException ... "
							+ e.getMessage());
			return;
		} catch (IOException e) {
			System.out.println("GucasQRCodeDecoder.execute() IOException ... "
					+ e.getMessage());
			return;
		}
	}

}