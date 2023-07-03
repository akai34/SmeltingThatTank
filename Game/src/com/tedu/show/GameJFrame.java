package com.tedu.show;

import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tedu.element.ElementObj;
import com.tedu.element.Play;

/**
 * @说明 游戏窗体
 * @author ASUS
 * @功能说明:
 * 	    嵌入面板，启动主线程等等
 * @窗体说明 swing awt 大小（记录上次大小）
 * 
 * @ 分析 1.面板绑定窗体
 * 		  2.监听绑定
 *        3.主线程启动
 *        4.显示窗体
 */
public class GameJFrame extends JFrame{
	public static int GameX = 500;
	public static int GameY = 600;
	private JPanel jPanel = null;//面板
	private KeyListener KeyListener=null;
	private MouseMotionListener mouseMotionListener=null;
	private MouseMotionListener mouseListener=null;
	private Thread thead = null;
	
	public GameJFrame() {
		init();
		
	}
	
	public void init() {
		this.setSize(GameX, GameY);
		this.setTitle("测试游戏");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
		this.setLocationRelativeTo(null);//居中
	}
	/*
	 * 窗体布局
	 */
	public void addButton() {
//		this.setLayout(manager);//添加控件
	}
	
	/*
	 * 启动
	 */
	public void start() {
		if (jPanel!=null) {
			this.add(jPanel);
		}
		if (KeyListener!=null) {
			this.addKeyListener(KeyListener);
		}
		if (thead!=null) {
			thead.start();
		}
		//界面刷新
		this.setVisible(true);
	}
	
	/* set注入 */
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public void setKeyListener(KeyListener keyListener) {
		KeyListener = keyListener;
	}

	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}

	public void setMouseListener(MouseMotionListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public void setThead(Thread thead) {
		this.thead = thead;
	}
	
	 
}
