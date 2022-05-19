package com.dialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bean.Own;
import com.main.MainFrame;
import com.tools.Tools;

public class Win extends JDialog {
	MainFrame mainframe;
	private JTextField text;
	TreeSet<Own> LOWER = new TreeSet<Own>();
	TreeSet<Own> MIDDLE = new TreeSet<Own>();
	TreeSet<Own> HEIGHT = new TreeSet<Own>();
	
	public Win(MainFrame mainframe){
		this.mainframe = mainframe;
		this.setIconImage(Tools.getImageIcon().getImage());
		this.setTitle("扫雷成功");
		this.setLocationRelativeTo(null);
		this.setSize(200, 150);
		this.init();
		this.setVisible(true);
	}

	private void init() {
		// 存放记入
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		JLabel label = new JLabel("好厉害！请留下你的大名");
		
		panel.add(label);
		
		text = new JTextField();
		panel.add(text);
//		times = mainframe.getTimer().getTimes();
		JLabel labTime = new JLabel("你所使用的时间："+Tools.timecount);
		panel.add(labTime);
		
		JButton butys = new JButton("保存");
		butys.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {

				if(Tools.currentLevel.equals("初级")){
					if(Tools.time1>=Tools.timecount && Tools.time2>=Tools.timecount && Tools.time3>=Tools.timecount){
						Tools.time3 = Tools.time2;
						Tools.name3 = Tools.name2;
						Tools.time2 = Tools.time1;
						Tools.name2 = Tools.name1;
						
						Tools.time1 = Tools.timecount;
						Tools.name1=text.getText();
						
					}else if(Tools.time2>=Tools.timecount && Tools.time3>=Tools.timecount && Tools.time1<=Tools.timecount){
						Tools.time3 = Tools.time2;
						Tools.name3 = Tools.name2;
						
						Tools.time2 = Tools.timecount;
						Tools.name2=text.getText();
						
					}else if(Tools.time3>=Tools.timecount && Tools.time1<=Tools.timecount && Tools.time2<=Tools.timecount){
						Tools.time3 = Tools.timecount;
						Tools.name3=text.getText();
					}
				}
				
				if(Tools.currentLevel.equals("中级")){				
					if(Tools.time01>=Tools.timecount && Tools.time02>=Tools.timecount && Tools.time03>=Tools.timecount){
						Tools.time03 = Tools.time02;
						Tools.name03 = Tools.name02;
						Tools.time02 = Tools.time01;
						Tools.name02 = Tools.name01;
						
						Tools.time01 = Tools.timecount;
						Tools.name01=text.getText();
						
					}else if(Tools.time02>=Tools.timecount && Tools.time03>=Tools.timecount && Tools.time01<=Tools.timecount){
						Tools.time03 = Tools.time02;
						Tools.name03 = Tools.name02;
						
						Tools.time02 = Tools.timecount;
						Tools.name02=text.getText();
						
					}else if(Tools.time03>=Tools.timecount && Tools.time01<=Tools.timecount && Tools.time02<=Tools.timecount){
						Tools.time03 = Tools.timecount;
						Tools.name03=text.getText();
					}
				}
				
				if(Tools.currentLevel.equals("高级")){				
					if(Tools.time001>=Tools.timecount && Tools.time002>=Tools.timecount && Tools.time003>=Tools.timecount){
						Tools.time003 = Tools.time002;
						Tools.name003 = Tools.name002;
						Tools.time002 = Tools.time001;
						Tools.name002 = Tools.name001;
						
						Tools.time001 = Tools.timecount; 
						Tools.name001=text.getText();
						
					}else if(Tools.time002>=Tools.timecount && Tools.time003>=Tools.timecount && Tools.time001<=Tools.timecount){
						Tools.time003 = Tools.time002;
						Tools.name003 = Tools.name002;
						
						Tools.time002 = Tools.timecount;
						Tools.name002=text.getText();
						
					}else if(Tools.time003>=Tools.timecount && Tools.time001<=Tools.timecount && Tools.time002<=Tools.timecount){
						Tools.time003 = Tools.timecount;
						Tools.name003=text.getText();
					}
				}
				
				Win.this.dispose();
				
			}
		});
		panel.add(butys);
		this.add(panel);
		
	}
	public JTextField getText() {
		return text;
	}

}