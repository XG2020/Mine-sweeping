package com.listener;

import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.xml.validation.SchemaFactory;

import com.bean.MineLabel;
import com.dialog.Win;
import com.main.MainFrame;
import com.panel.FaceJPanel;
import com.tools.LayMine;
import com.tools.Tools;

public class MouListener implements MouseListener {

	MainFrame mainframe;
	MineLabel[][] labels;
	
	Boolean isDoublePress = false;;
	
	public MouListener(MineLabel[][] labels, MainFrame mainframe) {
		this.labels = labels;
		this.mainframe = mainframe;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	// 鼠标按下时
	@Override
	public void mousePressed(MouseEvent e) {
		MineLabel mineLabel = (MineLabel) e.getSource(); // 获取事件源
		int row = mineLabel.getRowx();
		int col = mineLabel.getColy();
		
			// 判断是否是鼠标双击（左右键）操作
		if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {
				isDoublePress = true;
				doublePress(row, col);
			// 鼠标左键按下事件
		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK && !mineLabel.isFlagTag()) {
				// 对没有被展开或标记的方格
			if (!mineLabel.isExpendTag()) {
				// 鼠标左键按下背景
				mineLabel.setIcon(Tools.mineCount[0]);
			}
				// 表情变惊讶
			mainframe.getFaceJPanel().getLabelFace().setIcon(Tools.face2);
			
			// 鼠标右键按下事件
		} else if (e.getModifiers() == InputEvent.BUTTON3_MASK && !mineLabel.isExpendTag()) {
				// 右键点击数为 0 时
			if (mineLabel.getRightClickCount() == 0) {
				mineLabel.setIcon(Tools.flag); // 设置旗子标记
				mineLabel.setRightClickCount(1);
				mineLabel.setFlagTag(true);
				Tools.bombCount--;
				mainframe.getFaceJPanel().setNumber(Tools.bombCount); // 改变计数区雷数图片
				
				// 右键点击数为 1 时
			} else if (mineLabel.getRightClickCount() == 1) {
				mineLabel.setIcon(Tools.ask); // 设置问号标记
				mineLabel.setRightClickCount(2);
				mineLabel.setFlagTag(false);
				Tools.bombCount++;
				mainframe.getFaceJPanel().setNumber(Tools.bombCount); // 改变计数区雷数图片
				
				// 第3次点击还原回  未标记状态
			} else {
				mineLabel.setIcon(Tools.blank);
				mineLabel.setRightClickCount(0);
				mineLabel.setFlagTag(false);
			}

		}
	}
	
	// 鼠标左右键同时按下
	private void doublePress(int row, int col) {
		for (int x = Math.max(0, row - 1); x <= Math.min(Tools.rows - 1, row + 1); x++) {
			for (int y = Math.max(0, col - 1); y <= Math.min(Tools.cols - 1, col + 1); y++) {
				if (!labels[x][y].isExpendTag() && !labels[x][y].isFlagTag()) {
					int rightClickCount = labels[x][y].getRightClickCount();
					// 对标记旗子或者展开的不做处理
					if (rightClickCount == 1) {
						labels[x][y].setIcon(Tools.flag);
					} else {
						labels[x][y].setIcon(Tools.mineCount[0]);
					}
				}
			}
		}
	}
	
	
	// 左键展开方格
	private void expand(int x, int y) {
		int count = labels[x][y].getCountAround();
		if (!labels[x][y].isExpendTag() && !labels[x][y].isFlagTag()) {
			// 周围雷为0，递归调用继续展开
			if (count == 0) {
				labels[x][y].setIcon(Tools.mineCount[count]);
				labels[x][y].setExpendTag(true);
				for (int i = Math.max(0, x - 1); i <= Math.min(Tools.rows -1, x + 1); i++) {
					for (int j = Math.max(0, y - 1); j <= Math.min(Tools.cols -1, y + 1); j++) {
						expand(i, j);
					}
				}
			} else {
				// 显示周围雷数
				labels[x][y].setIcon(Tools.mineCount[count]);
				labels[x][y].setExpendTag(true);
			}
		}
	}
	
	

	@Override
	public void mouseReleased(MouseEvent e) {
		MineLabel mineLabel= (MineLabel) e.getSource(); // 当前方格
		int row = mineLabel.getRowx();
		int col = mineLabel.getColy();
		
		// 鼠标双击释放（右键不做处理）
		if(isDoublePress) {
			isDoublePress = false;
			if (!mineLabel.isExpendTag() && !mineLabel.isFlagTag()) {
				backIcon(row, col);
			} else {
				boolean isEquals = isEquals(row, col);
				if (isEquals) {
					doubleExpend(row, col);
				} else {
					backIcon(row, col);
				}
			}
			mainframe.getFaceJPanel().getLabelFace().setIcon(Tools.face0);
		// 左键释放
		}else if (e.getModifiers() == InputEvent.BUTTON1_MASK && !mineLabel.isFlagTag()) {
			if(!Tools.isStart) {        // 第一次鼠标左键点击，在弹起事件中进行布雷
				LayMine.lay(labels, row, col); // 布雷
				Tools.isStart = true;   //设置isStart=true,表示不是第一次点击了
				mainframe.getTimer().start();  // 开启计时器
			}
			
			if (mineLabel.isMineTag()) {//判断是否踩到地雷
				bombAction(row, col); //如果踩到地雷，游戏结束，显示全部的地雷
				mineLabel.setIcon(Tools.blood);
				mainframe.getFaceJPanel().getLabelFace().setIcon(Tools.face3);
			} else {
				mainframe.getFaceJPanel().getLabelFace().setIcon(Tools.face0);
				expand(row, col);

			}

		}
		//判断雷是否已全被清除完
		isWin();
	}
	
	
	private void isWin() {
		
		int expendCount = 0;
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				if (labels[i][j].isExpendTag()) {
					expendCount++;
				}
			}
		}
		if (Tools.rows * Tools.cols - expendCount == Tools.allcount) {

			for (int i = 0; i < Tools.rows; i++)
				for (int j = 0; j < Tools.cols; j++) {
					if (mainframe.getBombJPanel().getLabels()[i][j].isMineTag()
							&& !mainframe.getBombJPanel().getLabels()[i][j].isFlagTag()) {
						mainframe.getBombJPanel().getLabels()[i][j].setIcon(Tools.flag);
					}
					// 移除监听
					mainframe.getBombJPanel().getLabels()[i][j]
							.removeMouseListener(mainframe.getBombJPanel()
									.getListener());

				}
			mainframe.getFaceJPanel().getLabelFace().setIcon(Tools.face4);

			mainframe.getFaceJPanel().setNumber(0);

			mainframe.getTimer().stop();
			new Win(mainframe);
			

			//成功后弹出英雄记录版
			 
			Tools.isStart = false;
		}
		
	}

	//判断方格周围雷的数量与周围被标记的方格数是否相等
	private boolean isEquals(int i, int j) {
		int count = labels[i][j].getCountAround();
		int flagCount = 0;
		for (int x = Math.max(0, i - 1); x <= Math.min(Tools.rows - 1, i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(Tools.cols - 1, j + 1); y++) {
				if (labels[x][y].isFlagTag()) {
					flagCount++;
				}
			}
		}
		if (count == flagCount) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	// 左右键双击展开
	private void doubleExpend(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(Tools.rows - 1, i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(Tools.cols - 1, j + 1); y++) {
				if (labels[x][y].isMineTag()) { // 如果是雷
					if (!labels[x][y].isFlagTag()) { // 没有旗子标记
						bombAction(x, y);
					}
				} else {  // 不是雷
					if (!labels[x][y].isFlagTag()) { // 没有旗子标记
						expand(x, y);
					}
				}

			}
		}
	}
	
	// 触雷
	private void bombAction(int row, int col) {
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				if (labels[i][j].isMineTag()) { // 是雷
					if (!labels[i][j].isFlagTag()) { // 没有标记
						labels[i][j].setIcon(Tools.mine0);
					} else {
						labels[i][j].setIcon(Tools.mine1);
					}
				}
			}
		}
		// 修改踩雷状态
		Tools.isBoom = true;
		// 停止计时器
		mainframe.getTimer().stop();
		
		// 取消鼠标监听器
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j].removeMouseListener(this);

			}
		}

	}
	
	//还原方格显示效果（因为鼠标按下时显示背景）
	private void backIcon(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(Tools.rows - 1, i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(Tools.cols - 1, j + 1); y++) {
				if (!labels[x][y].isFlagTag() && !labels[x][y].isExpendTag()) {
					
					int rightClickCount = labels[x][y].getRightClickCount();
					if (rightClickCount == 2) {
						labels[x][y].setIcon(Tools.ask);
					} else {
						if(!labels[x][y].isFlagTag()){
							labels[x][y].setIcon(Tools.blank);
						}
					}
				}
			}
		}

	}
	


	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
