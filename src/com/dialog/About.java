package com.dialog;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.main.MainFrame;
import com.tools.Tools;

public class About extends JDialog {
	
	MainFrame mainframe;
	
	public About(MainFrame mainframe){
		this.setIconImage(Tools.getImageIcon().getImage());
		this.mainframe = mainframe;
		this.setTitle("关于扫雷");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setSize(200, 130);
		this.init();
		this.setVisible(true);
	}
	private void init() {
		Box box = Box.createVerticalBox();
		JPanel jpanel = new JPanel();
		JLabel jlabel = new JLabel("扫雷 ©2022");
		JLabel jlabel1 = new JLabel("作者：XG.孤梦");
		JLabel jlabel2 = new JLabel("我的博客：https://www.xggm.top");
		box.add(jlabel);
		box.add(Box.createVerticalStrut(10));
		box.add(jlabel1);
		box.add(Box.createVerticalStrut(10));
		box.add(jlabel2);

		jpanel.add(box);
		this.add(jpanel);
	}
	
}
