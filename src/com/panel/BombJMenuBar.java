package com.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.bean.MineLabel;
import com.dialog.Hero;
import com.dialog.Hero1;
import com.dialog.Hero2;
import com.listener.MenuListener;
import com.main.MainFrame;
import com.tools.Tools;


public class BombJMenuBar extends JMenuBar {
	
	// 菜单项
	JMenu menuGame = new JMenu("游戏(G)");
	JMenuItem menuItemStart = new JMenuItem("开局(N)");
	JMenuItem menuItemLow = new JMenuItem("初级(B)");
	JMenuItem menuItemMid = new JMenuItem("中级(I)");
	JMenuItem menuItemHigh = new JMenuItem("高级(E)");
	

	JMenu menuHero = new JMenu("英雄榜(T)");
	JMenuItem menuHeroLow = new JMenuItem("初级英雄榜");
	JMenuItem menuHeroMid = new JMenuItem("中级英雄榜");
	JMenuItem menuHeroHigh = new JMenuItem("高级英雄榜");
	
	JMenuItem menuItemOrder = new JMenuItem("自定义(C)");
	JMenuItem menuItemExit = new JMenuItem("退出(X)");
	
	
	JMenu menuHelp = new JMenu("帮助(H)");
	JMenuItem menuItemAbout = new JMenuItem("关于扫雷(A)");
	JMenuItem menuItemHole = new JMenuItem("外挂(W)");
	
	// 声明主窗体对象
	MainFrame mainframe;

	public BombJMenuBar(MainFrame mainframe) {
		this.mainframe = mainframe;
		init();
	}
	
	// 初始化
	public void init() {
		// 设置快捷键
		menuGame.setMnemonic('G'); // Alt + G
		menuItemStart.setMnemonic('N');
		menuItemLow.setMnemonic('B');
		menuItemMid.setMnemonic('I');
		menuItemHigh.setMnemonic('E');
		menuHero.setMnemonic('T');
		menuItemOrder.setMnemonic('C');
		menuItemExit.setMnemonic('X');
		
		menuHelp.setMnemonic('H');
		menuItemAbout.setMnemonic('A');
		menuItemHole.setMnemonic('W');
		
		// 把菜单添加到菜单栏
		// 游戏菜单
		this.add(menuGame);
		
		menuGame.add(menuItemStart);
		menuGame.addSeparator(); // 添加分隔线
		menuGame.add(menuItemLow);
		menuGame.add(menuItemMid);
		menuGame.add(menuItemHigh);
		
		menuGame.addSeparator();
		menuGame.add(menuHero);
		menuHero.add(menuHeroLow);
		menuHero.add(menuHeroMid);
		menuHero.add(menuHeroHigh);
		
		menuGame.addSeparator();
		menuGame.add(menuItemOrder);
		
		menuGame.addSeparator();
		menuGame.add(menuItemExit);
		// 帮助菜单
		this.add(menuHelp);
		menuHelp.add(menuItemAbout);
		menuHelp.addSeparator();
		menuHelp.add(menuItemHole);

		// 开局事件处理
		menuItemStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainframe.reStartGame();

			}
		});

		// 添加事件监听器
		MenuListener listener = new MenuListener(mainframe);
		menuItemStart.addActionListener(listener);
		menuItemLow.addActionListener(listener);
		menuItemMid.addActionListener(listener);
		menuItemHigh.addActionListener(listener);
		menuItemOrder.addActionListener(listener);

		menuHeroLow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Hero(mainframe);
			}});
		menuHeroMid.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Hero1(mainframe);
			}});
		menuHeroHigh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Hero2(mainframe);
			}});

		menuItemAbout.addActionListener(listener);

		// 后门外挂方便测试
		menuItemHole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Tools.isStart) {
					Tools.isHole = true;
					// 判断每一个雷块是否是雷
					for (MineLabel[] mineLabel : BombJMenuBar.this.mainframe
							.getBombJPanel().getLabels()) {
						for (MineLabel m : mineLabel) {
							if (m.isMineTag()) {
								m.setIcon(Tools.hole);
							}
						}
					}
				}
			}
		});

		menuItemExit.addActionListener(new ActionListener() {    //加一个系统退出的处理监听
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}

	

}
