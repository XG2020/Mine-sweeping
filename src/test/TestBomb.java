package test;

import com.bean.MineLabel;
import com.main.MainFrame;
import com.tools.LayMine;
import com.tools.Tools;

public class TestBomb {
	
	/**
	 * 测试类
	 */
	
	public static void main(String[] args) {
		MainFrame mainframe = new MainFrame();
		MineLabel[][] labels = mainframe.getBombJPanel().getLabels();
		LayMine.lay(labels, 3, 3); // 假设当前鼠标点击位置【3,3】
		LayMine.countBomb(labels);
		for (int i = 0; i < Tools.rows; i++) {
			for (int j = 0; j < Tools.cols; j++) {
				if (labels[i][j].isMineTag()) {
						labels[i][j].setIcon(Tools.mine);
				}else {
					int count = labels[i][j].getCountAround();
					labels[i][j].setIcon(Tools.mineCount[count]);
				}
				
			}
		}
		
		
	}
}
