package com.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.main.MainFrame;
import com.tools.Tools;

//....................计数显示区....................

public class FaceJPanel extends JPanel {
	
	// 雷数
	private JLabel labelCountG = new JLabel();	// 个位
	
	private JLabel labelCountS = new JLabel();	// 十位
	
	private JLabel labelCountB = new JLabel();	// 百位
	
	// 笑脸
	private JLabel labelFace = new JLabel();
	
	// 时间
	private JLabel labelTimeG = new JLabel();	// 个位
	
	private JLabel labelTimeS = new JLabel();	// 十位
	
	private JLabel labelTimeB = new JLabel();	// 百位
	
	// // 声明主窗体对象
	MainFrame mainframe;
	
	public FaceJPanel(MainFrame mainframe) {
		this.mainframe = mainframe;
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		init();
	}
	
	// 初始化
	private void init() {
		
		JPanel panel = new JPanel();
		// 布局盒子
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
		panel.setLayout(boxLayout);
		labelFace.addMouseListener(new FacelabelListener()); // 添加表情按钮监听器
		// 添加计数区监听器
		panel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent arg0) {
				if(!Tools.isBoom) {
				labelFace.setIcon(Tools.face2);
				}
			}
			public void mouseReleased(MouseEvent arg0) {
				if(!Tools.isBoom) {
				labelFace.setIcon(Tools.face0);
				}
			}
		}); 
		
		Icon icon0 = new ImageIcon("./image/d0.gif");
		
		// 计算雷的每位数图片
		Icon icon3 = new ImageIcon("./image/d" + Tools.allcount / 100 + ".gif");// 百位
		Icon icon2 = new ImageIcon("./image/d" + Tools.allcount /10%10 + ".gif");	// 十位
		Icon icon1 = new ImageIcon("./image/d" + Tools.allcount % 10 + ".gif"); // 个位
		
		
		Icon iconSmile = new ImageIcon("./image/face0.gif");
		
		// 雷数显示图片
		labelCountG.setIcon(icon1);
		labelCountS.setIcon(icon2);
		labelCountB.setIcon(icon3);
		
		// 时间显示图片
		labelTimeG.setIcon(icon0);
		labelTimeS.setIcon(icon0);
		labelTimeB.setIcon(icon0);
		
		// 表情显示图片
		labelFace.setIcon(iconSmile);
		
		// 添加控件到panel
		panel.add(Box.createHorizontalStrut(2)); // 最左侧
		panel.add(labelCountB);
		panel.add(labelCountS);
		panel.add(labelCountG);
		panel.add(Box.createHorizontalGlue()); // 添加水平方向的构件，占位
		panel.add(labelFace);
		panel.add(Box.createHorizontalGlue());
		panel.add(labelTimeB);
		panel.add(labelTimeS);
		panel.add(labelTimeG);
		panel.add(Box.createHorizontalStrut(2)); // 最右侧
		
		// 实现边框效果
		Border borderLow = BorderFactory.createLoweredBevelBorder();
		
		// 内边框
		Border borderEmpty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border borderCom1 = BorderFactory.createCompoundBorder(borderLow, borderEmpty);
		panel.setBorder(borderCom1);
		panel.setBackground(Color.LIGHT_GRAY);
		
		// 外边框
		Border borderEmpty1 = BorderFactory.createEmptyBorder(5, 5, 0, 5);
		this.setBorder(borderEmpty1);
		this.setBackground(Color.LIGHT_GRAY);

		this.add(panel);
	}
	
	// 计数器 根据当前旗子数计算剩余雷数
	public void setNumber(int count) {
		int b = 0;
		if (count < 0) {
			b = 10;
		} else {
			b = count / 100;
		}
		int g = Math.abs(count) % 10;
		int s = Math.abs(count) / 10 % 10;
		labelCountG.setIcon(Tools.timeCount[g]);
		labelCountS.setIcon(Tools.timeCount[s]);
		labelCountB.setIcon(Tools.timeCount[b]);
	}
	
	
	// 计时器
	public void setTime(int count) {
		int b = 0;
		if (count < 0) {
			b = 10;
		} else {
			b = count / 100;
		}
		int g = Math.abs(count) % 10;
		int s = Math.abs(count) / 10 % 10;
		labelTimeG.setIcon(Tools.timeCount[g]);
		labelTimeS.setIcon(Tools.timeCount[s]);
		labelTimeB.setIcon(Tools.timeCount[b]);
	}
	
	public class FacelabelListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				mainframe.getTimer().stop();
				labelFace.setIcon(Tools.face1);
			}
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				mainframe.getTimer().start();
				mainframe.reStartGame();
				labelFace.setIcon(Tools.face0);
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
}
	

	public JLabel getLabelCountG() {
		return labelCountG;
	}

	public JLabel getLabelCountS() {
		return labelCountS;
	}

	public JLabel getLabelCountB() {
		return labelCountB;
	}

	public JLabel getLabelFace() {
		return labelFace;
	}

	public JLabel getLabelTimeG() {
		return labelTimeG;
	}

	public JLabel getLabelTimeS() {
		return labelTimeS;
	}

	public JLabel getLabelTimeB() {
		return labelTimeB;
	}

	public void setLabelCountG(JLabel labelCountG) {
		this.labelCountG = labelCountG;
	}

	public void setLabelCountS(JLabel labelCountS) {
		this.labelCountS = labelCountS;
	}

	public void setLabelCountB(JLabel labelCountB) {
		this.labelCountB = labelCountB;
	}

	public void setLabelFace(JLabel labelFace) {
		this.labelFace = labelFace;
	}

	public void setLabelTimeG(JLabel labelTimeG) {
		this.labelTimeG = labelTimeG;
	}

	public void setLabelTimeS(JLabel labelTimeS) {
		this.labelTimeS = labelTimeS;
	}

	public void setLabelTimeB(JLabel labelTimeB) {
		this.labelTimeB = labelTimeB;
	}
	
	
}
