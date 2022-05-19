package com.bean;

import javax.swing.JLabel;

public class MineLabel extends JLabel {
	private boolean mineTag = false; 	 // 判断是否是雷
	private boolean expendTag = false; 	 // 判断雷块是否展开
	private boolean flagTag = false; 	 // 判断雷块是否插了旗子
	private int rowx; 			 // 雷块所在的行
	private int coly; 			 // 雷块所在的列
	private int countAround; 	 // 计算雷块周围的雷数
	private int rightClickCount; // 记录右键点击次数
	
	// 创建构造函数，方便计算周围雷数量
	public MineLabel(int rowx, int coly) {
		this.rowx = rowx;
		this.coly = coly;
	}
	
	//一向是对应的一些get和set方法
	public boolean isMineTag() {
		return mineTag;
	}
	public boolean isExpendTag() {
		return expendTag;
	}
	public boolean isFlagTag() {
		return flagTag;
	}
	public int getRowx() {
		return rowx;
	}
	public int getColy() {
		return coly;
	}
	public int getCountAround() {
		return countAround;
	}
	public int getRightClickCount() {
		return rightClickCount;
	}
	public void setMineTag(boolean mineTag) {
		this.mineTag = mineTag;
	}
	public void setExpendTag(boolean expendTag) {
		this.expendTag = expendTag;
	}
	public void setFlagTag(boolean flagTag) {
		this.flagTag = flagTag;
	}
	public void setRowx(int rowx) {
		this.rowx = rowx;
	}
	public void setColy(int coly) {
		this.coly = coly;
	}
	public void setCountAround(int countAround) {
		this.countAround = countAround;
	}
	public void setRightClickCount(int rightClickCount) {
		this.rightClickCount = rightClickCount;
	}


}
