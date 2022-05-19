package com.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.management.ListenerNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import com.listener.*;
import com.bean.MineLabel;
import com.main.MainFrame;
import com.tools.Tools;

//....................雷区....................

public class BombJPanel extends JPanel {
	
//	MineLabel[][] labels = new MineLabel[9][9];
	MineLabel[][] labels = new MineLabel[Tools.rows][Tools.cols];

	// 声明主窗体对象
	MainFrame mainframe;
	
	MouListener listener;
	
	public BombJPanel(MainFrame mainframe) {
		this.mainframe = mainframe;
		//定义布局方式，网格布局
		this.setLayout(new GridLayout(Tools.rows, Tools.cols));

		listener = new MouListener(labels,mainframe);
		
		init();
	}
	
	public MineLabel[][] getLabels() {
		return labels;
	}

	public MainFrame getMainframe() {
		return mainframe;
	}

	public void setLabels(MineLabel[][] labels) {
		this.labels = labels;
	}

	public void setMainframe(MainFrame mainframe) {
		this.mainframe = mainframe;
	}
	
	public MouListener getListener() {
		return listener;
	}

	public void setListener(MouListener listener) {
		this.listener = listener;
	}
	
	
	// 初始化
	private void init() {
		// 实例化小方格
		for(int i = 0;i<labels.length;i++) {
			for(int j = 0; j<labels[i].length;j++) {
				labels[i][j] = new MineLabel(i, j);
				labels[i][j].setIcon(Tools.blank);
				this.add(labels[i][j]);
				labels[i][j].addMouseListener(listener);
			}
		}
		
		// 实现边框效果
		Border lowerBorder = BorderFactory.createLoweredBevelBorder();
		Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5); //边框大小
		CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, lowerBorder)	;
		this.setBorder(compoundBorder);
		this.setBackground(Color.LIGHT_GRAY);
	}
	
}

