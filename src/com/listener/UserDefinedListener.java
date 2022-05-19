package com.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dialog.UserDefined;
import com.main.MainFrame;
import com.tools.Tools;


// ©自定义窗口监听器
public class UserDefinedListener implements ActionListener {
	
	UserDefined userDefined;

	MainFrame mainFrame;

	public UserDefinedListener(UserDefined userDefined, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.userDefined = userDefined;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == userDefined.getButtonCancer()) {
			userDefined.dispose();
			mainFrame.reStartGame();
		} else if (e.getSource() == userDefined.getButtonSure()) {
			String highT = userDefined.getjTextFieldHigh().getText();
			Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
			Matcher matcher = pattern.matcher(highT);
			int row = 0;
			if (!matcher.matches()) {
				userDefined.getjLabelMessage()
						.setText("输入的行数范围应在9-30之间");
				return;
			} else {
				row = Integer.parseInt(highT);
				if (row < 9 || row > 30) {
					userDefined.getjLabelMessage().setText(
							"输入的行数范围应在9-30之间");
					return;
				}

			}
			String colT = userDefined.getjTextFieldWide().getText();
			int col = 0;
			try {
				col = Integer.parseInt(colT);
				if (col < 9 || col > 30) {
					userDefined.getjLabelMessage().setText(
							"输入的列数范围应在9-30之间");
					return;
				}
			} catch (Exception e2) {
				userDefined.getjLabelMessage().setText(
						"列数应该为数字且范围应在9-30之间");
				return;
			}

			String mineT = userDefined.getjTextFieldBomb().getText();
			int mine = 0;
			try {
				mine = Integer.parseInt(mineT);
				if (mine < 10) {
					mine = 10;
				} else {
					mine = Math.min(mine, Tools.rows * Tools.cols * 4 / 5);
				}
			} catch (Exception e3) {
				userDefined.getjLabelMessage().setText("雷数应该为数字");
				return;
			}
			userDefined.dispose();
			Tools.rows = row;
			Tools.cols = col;
			Tools.allcount = mine;

			mainFrame.reStartGame();
		}

	}

}