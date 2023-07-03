package com.tedu.game;

import com.tedu.show.GameJFrame;
import com.tedu.show.GameMainJPanel;

public class GameStart {
	/**
	 * 程序唯一入口
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		GameJFrame gj=new GameJFrame();
		/*
		 * 实例化面板
		 */
		GameMainJPanel jp=new GameMainJPanel();
		gj.setjPanel(jp);
		gj.start();
	}

}
