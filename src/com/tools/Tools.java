package com.tools;

import java.awt.Image;

import javax.swing.ImageIcon;

//....................工具类....................

public class Tools {
	// 窗口图标
	public static ImageIcon imageIcon = new ImageIcon("./image/icon.gif");

	public static ImageIcon getImageIcon() {
		return imageIcon;
	}

	public static int rows = 9; 	// 雷区行数
	public static int cols = 9;		// 雷区列数
	public static int timecount = 0; // 计时
	public static int allcount = 10; // 所有雷的数量
	public static int bombCount = allcount; // 剩余为未被标记雷数
	public static boolean isBoom = false;  // 是否踩雷
	public static boolean isStart = false; // 是否开始
	public static boolean isHole = false;  // 是否开启后门外挂
	
	// 排行榜
	public static int time = 0;
	// 初级
	public static int time1= 999;
	public static int time2= 999;
	public static int time3= 999;
	public static String name1="匿名";
	public static String name2="匿名";
	public static String name3="匿名";
	
	// 中级
	public static int time01= 999;
	public static int time02= 999;
	public static int time03= 999;
	public static String name01="匿名";
	public static String name02="匿名";
	public static String name03="匿名";
	
	// 高级
	public static int time001= 999;
	public static int time002= 999;
	public static int time003= 999;
	public static String name001="匿名";
	public static String name002="匿名";
	public static String name003="匿名";
	
	
	// 游戏等级
	public static final String LOWER_LEVEL = "初级";
	public static final String MIDDLE_LEVEL = "中级";
	public static final String HEIGHT_LEVEL = "高级";
	public static final String CUSTOM_LEVEL = "自定义";
	
	// 游戏当前等级
	public static String currentLevel = LOWER_LEVEL;
	
	// 用来存放0-8地雷数字标签图片
	public static ImageIcon mineCount[];
	// 用来存放时间数字标签图片
	public static ImageIcon timeCount[];

	// 静态块
	static {
		mineCount = new ImageIcon[9];
		for (int i = 0; i <= 8; i++) {
			mineCount[i] = new ImageIcon("./image/" + i + ".gif");
		}

		timeCount = new ImageIcon[11];
		for (int i = 0; i < 10; i++) {
			timeCount[i] = new ImageIcon("./image/d" + i + ".gif");
		}
		timeCount[10] = new ImageIcon("./image/d10.gif");
	}
	
	// 笑脸表情标签图片
	public static ImageIcon face0 = new ImageIcon("./image/face0.gif");

	public static ImageIcon face1 = new ImageIcon("./image/face1.gif");

	public static ImageIcon face2 = new ImageIcon("./image/face2.gif");

	public static ImageIcon face3 = new ImageIcon("./image/face3.gif");

	public static ImageIcon face4 = new ImageIcon("./image/face4.gif");

	public static ImageIcon face5 = new ImageIcon("./image/face5.gif");

	public static ImageIcon face6 = new ImageIcon("./image/face6.gif");

	public static ImageIcon face7 = new ImageIcon("./image/face7.gif");

	public static ImageIcon face8 = new ImageIcon("./image/face8.gif");


	
	// 分别对应 mine雷标签图片
	public static ImageIcon mine = new ImageIcon("./image/mine.gif");

	public static ImageIcon mine0 = new ImageIcon("./image/mine0.gif");

	public static ImageIcon mine1 = new ImageIcon("./image/mine1.gif");


	
	//分别对应 ask问号标签图片
	public static ImageIcon ask = new ImageIcon("./image/ask.gif");

	public static ImageIcon ask1 = new ImageIcon("./image/ask1.gif");

	public static ImageIcon iconTemp = new ImageIcon("./image/icon.gif");

	public static Image icon = iconTemp.getImage();

	public static ImageIcon blank = new ImageIcon("./image/blank.gif");

	public static ImageIcon blood = new ImageIcon("./image/blood.gif");

	public static ImageIcon error = new ImageIcon("./image/error.gif");

	// 旗子标签图片
	public static ImageIcon flag = new ImageIcon("./image/flag.gif");

	
	// hole外挂标签图片
	public static ImageIcon hole = new ImageIcon("./image/hole.gif");

	
}