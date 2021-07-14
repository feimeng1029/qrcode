package com.arthas.qrcode.screen;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 截图程序
 * @author admin
 *
 */
public class RobotScreen {
	//public static String pathname = "F://my_work//pic//template.jpg";
	
	public static void execute(final int x, final int y, final int witdh, final int hight, final String fileName) {
		//System.out.println("start" + System.currentTimeMillis());
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice[] screen = environment.getScreenDevices();
				for (int i = 0; i < screen.length; i++) {
					//System.out.println(screen[i]);
				}
				//System.out.println(screen.toString());
				
				try{
					Robot robot = new Robot(screen[0]);
					exeucteRobot(robot, x, y, witdh, hight, fileName);
				} catch (Exception e) {
					System.out.println("RobotScreen.execute() ... " + e.getMessage());
				}
			}
		});
		//System.out.println("end" + System.currentTimeMillis());
	}
	
	public static void main(String[] args) {
		int x = 20;
		int y = 67;
		int len = 0;
		int width = 560;
		int hight = 560;
		execute(x, y, width, hight, "C:\\github\\qrcode\\test\\tmp\\template1.jpg");
		//execute(x + width + len, y, width, hight, "F://my_work//pic//template2.jpg");
		//execute(x, y + hight + len, width, hight, "F://my_work//pic//template3.jpg");
		//execute(x + width + len, y + hight + len, width, hight, "F://my_work//pic//template4.jpg");
	}
	
	public static void exeucteRobot(Robot robot, int x, int y, int witdh, int hight, String fileName) {
		
		BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, witdh, hight));
		File output = new File(fileName);
		try {
			ImageIO.write(image, "jpg", output);
		} catch (IOException e) {
			System.out.println("RobotScreen.exeucteRobot() ... " + e.getMessage());
		}
		return; 	
		
	}
}
