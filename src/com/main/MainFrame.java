package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.panel.*;
import com.timer.Timers;
import com.tools.Tools;

// ....................主窗体....................

public class MainFrame extends JFrame{
	
	private BombJMenuBar menuBar = new BombJMenuBar(this);
	
	BombJPanel bombJPanel = new BombJPanel(this);	
	FaceJPanel faceJPanel = new FaceJPanel(this);
	
	private Timer timer = new Timer(1000, new Timers(this));
	
	public MainFrame() {
		init();
		
		this.setIconImage(Tools.getImageIcon().getImage());	// 设置图标
		this.setTitle("扫雷");	// 设置标题
		this.setSize(new Dimension(220,300));	// 窗口大小
		this.setResizable(false); // 这样让窗口不可放大
		this.setLocationRelativeTo(null);
		this.setLocation(new Point(800,300));	// 设置窗口位置
		this.setVisible(true);	// 设置窗口显示
		this.pack(); // 使控件更紧凑，窗口自动适应大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 关闭窗口
		
	}
	
	public void reStartGame() {
		// 游戏重新开始方法
		this.remove(faceJPanel);
		this.remove(bombJPanel);

		Tools.bombCount = Tools.allcount;
		Tools.timecount = 0;
		Tools.isStart = false;
		Tools.isBoom = false;

		faceJPanel = new FaceJPanel(this);
		bombJPanel = new BombJPanel(this);
		this.add(faceJPanel, BorderLayout.NORTH);
		this.add(bombJPanel);
		this.pack();
		this.validate();

		getTimer().stop();

	}
	
	private void init() {
		// 菜单栏
		this.setJMenuBar(menuBar);
		BorderLayout layout = new BorderLayout();
		
		this.setLayout(layout);
		// 计数区
		this.add(faceJPanel,layout.NORTH);
		
		// 雷区
		this.add(bombJPanel,layout.CENTER);
		
	}
	



	public BombJPanel getBombJPanel() {
		return bombJPanel;
	}

	public void setMenuBar(BombJMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public void setBombJPanel(BombJPanel bombJPanel) {
		this.bombJPanel = bombJPanel;
	}

	public static void main(String[] args) {
		new MainFrame();
	}
	
	public FaceJPanel getFaceJPanel() {
		return faceJPanel;
	}

	public void setFaceJPanel(FaceJPanel faceJPanel) {
		this.faceJPanel = faceJPanel;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
}
