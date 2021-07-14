package com.arthas.qrcode.screen;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class PhotoRotationFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MyJPanel mp;
	int index;
	static ImageIcon[] imgs = null;

	public PhotoRotationFrame() {
		mp = new MyJPanel();
		this.add(mp);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("窗口");
		this.setVisible(true);
		Timer timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.repaint();
			}
		});
		timer.start();
	}
	
	public static void init() {
		File file = new File("C:\\github\\qrcode\\test\\qccode");
		File[] fileList = file.listFiles();
		int i = 0;
		imgs = new ImageIcon[fileList.length];
		for(File f : fileList) {
			imgs[i] = new ImageIcon(f.getPath());
			i++;
		}
	}

	public static void main(String[] args) {
		init();
		new PhotoRotationFrame();
	}

	class MyJPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(imgs[index % imgs.length].getImage(), 0, 0, this);
			index++;
		}
	}
}