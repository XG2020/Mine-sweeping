package com.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.dialog.About;
import com.dialog.UserDefined;
import com.main.MainFrame;
import com.tools.Tools;


 // 等级菜单监听器
public class MenuListener implements ActionListener {
	JMenuItem jMenuItem;

	JTextField jTextField=new JTextField();
	MainFrame mainframe;
	
	public MenuListener(MainFrame mainframe){
		this.mainframe = mainframe;
		
	}
	
	
public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("开局(N)")){
			this.mainframe.reStartGame();
		}
		if(e.getActionCommand().equals("初级(B)")){
				Tools.rows = 9;
				Tools.cols = 9;
				Tools.allcount = 10;
				Tools.currentLevel = Tools.LOWER_LEVEL;
				this.mainframe.reStartGame();
		}
		if(e.getActionCommand().equals("中级(I)")){

				Tools.rows = 16;
				Tools.cols = 16;
				Tools.allcount = 40;
				Tools.currentLevel = Tools.MIDDLE_LEVEL;
				this.mainframe.reStartGame();
			}
		if(e.getActionCommand().equals("高级(E)")){
					Tools.rows = 16;
					Tools.cols = 30;
					Tools.allcount = 99;
					Tools.currentLevel = Tools.HEIGHT_LEVEL;
					this.mainframe.reStartGame();
				}
		 if(e.getActionCommand().equals("自定义(C)")){
			 		new UserDefined(mainframe);

				}
		 if(e.getActionCommand().equals("关于扫雷(A)")){
		 		new About(mainframe);
			}
		 }
}
		
