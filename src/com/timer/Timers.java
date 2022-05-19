package com.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.main.MainFrame;
import com.tools.Tools;

 // 计时器
public class Timers implements ActionListener {
	private int times;
	MainFrame mainfame;
	public Timers(MainFrame mainfame){
		this.mainfame = mainfame;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Tools.timecount++;
		if(Tools.timecount>999){
			Tools.timecount=999;
		}else{	
			mainfame.getFaceJPanel().setTime(Tools.timecount);
		}
	}
}
