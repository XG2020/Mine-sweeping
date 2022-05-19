package com.tools;

import java.util.Random;

import com.bean.MineLabel;


//....................布雷功能区....................

public class LayMine {
	
   /**
	* labels：存储方格的二维数据
	* row：当前鼠标点击的x值
	* col：当前鼠标点击的y值
	*/
	public static void lay(MineLabel[][] labels, int row, int col) {
		
		int count = 0;
		Random random = new Random(); // 随机
		
		while (count<Tools.allcount) {
			int x = random.nextInt(Tools.rows);
			int y = random.nextInt(Tools.cols);
			
			if(!labels[x][y].isMineTag() && (x !=row && y!= col)) {
				// 布雷
				labels[x][y].setMineTag(true);
				count++;				
			}
		}
		countBomb(labels);
		
	}
	
	
	/**
	 * |计算周围雷数
	 */
	public static void countBomb(MineLabel[][] labels) {
		int count = 0;
		for (int i = 0; i < Tools.rows; i++) {
			for (int j = 0; j < Tools.cols; j++) {
				count = 0;
				// 当前方格不是雷才计算周围雷的数量
				if (!labels[i][j].isMineTag()) {
					for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, Tools.rows - 1); x++) {
						for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, Tools.cols - 1); y++) {
							if (labels[x][y].isMineTag()) {
								count++;
							}
						}
					}
					labels[i][j].setCountAround(count);
				}		
			}
		}
		
	}
	
}
