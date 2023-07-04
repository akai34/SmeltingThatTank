package com.tedu.game;

import com.tedu.show.GameJFrame;
import com.tedu.show.GameMainJPanel;

public class GameStart {
	/**
	 * 程序的唯一入口
	 */
	public static void main(String[] args) {
		GameJFrame gj=new GameJFrame();
		/**实例化面板，注入到jframe中*/
		GameMainJPanel jp=
				new GameMainJPanel();
		
//		注入
		gj.setjPanel(jp);
		gj.start();
		
	}

}
