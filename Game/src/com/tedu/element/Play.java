package com.tedu.element;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.manager.GameLoad;

public class Play extends ElementObj /* implements Comparable<Play>*/{
	/**
	 * 移动属性:
	 * 1.单属性  配合  方向枚举类型使用; 一次只能移动一个方向 
	 * 2.双属性  上下 和 左右    配合boolean值使用 例如： true代表上 false 为下 不动？？
	 *                      需要另外一个变来确定是否按下方向键
	 *                约定    0 代表不动   1代表上   2代表下  
	 * 3.4属性  上下左右都可以  boolean配合使用  true 代表移动 false 不移动
	 * 						同时按上和下 怎么办？后按的会重置先按的
	 * 说明：以上3种方式 是代码编写和判定方式 不一样
	 * 说明：游戏中非常多的判定，建议灵活使用 判定属性；很多状态值也使用判定属性
	 *      多状态 可以使用map<泛型,boolean>;set<判定对象> 判定对象中有时间
	 *      
	 * @问题 1.图片要读取到内存中： 加载器  临时处理方式，手动编写存储到内存中     
	 *       2.什么时候进行修改图片(因为图片是在父类中的属性存储)
	 *       3.图片应该使用什么集合进行存储
	 */
	private boolean left=false; //左
	private boolean up=false;   //上
	private boolean right=false;//右
	private boolean down=false; //下
	

//	变量专门用来记录当前主角面向的方向,默认为是up
	private String fx="up";
	
	public Play(int x, int y, int w, int h, ImageIcon icon) {
		super(x, y, w, h, icon);
	}
	/**
	 * 面向对象中第1个思想： 对象自己的事情自己做
	 */
	@Override
	public void showElement(Graphics g) {
//		绘画图片
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}
	/*
	 * @说明 重写方法： 重写的要求：方法名称 和参数类型序列 必须和父类的方法一样
	 * @重点 监听的数据需要改变状态值
	 */
	@Override   // 注解 通过反射机制，为类或者方法或者属性 添加的注释(相当于身份证判定)
	public void keyClick(boolean bl,int key) { //只有按下或者鬆開才會 调用这个方法
//		System.out.println("测试："+key);
		if(bl) {//按下
			switch(key) {  //怎么优化 大家中午思考;加 监听会持续触发；有没办法触发一次
			case 37: 
				this.down=false;this.up=false;
				this.right=false;this.left=true; this.fx="left"; break;
			case 38: 
				this.right=false;this.left=false;
				this.down=false; this.up=true;   this.fx="up"; break;
			case 39: 
				this.down=false;this.up=false;
				this.left=false; this.right=true; this.fx="right";break;
			case 40: 
				this.right=false;this.left=false;
				this.up=false; this.down=true;  this.fx="down";break;
			}
		}else {
			switch(key) {
			case 37: this.left=false;  break;
			case 38: this.up=false;    break;
			case 39: this.right=false; break;
			case 40: this.down=false;  break;
			}
		//a a
		}	
	}
	
	
//	@Override
//	public int compareTo(Play o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	@Override
	public void move() {
		if (this.left && this.getX()>0) {
			this.setX(this.getX() - 1);
		}
		if (this.up  && this.getY()>0) {
			this.setY(this.getY() - 1);
		}
		if (this.right && this.getX()<900-this.getW()) {  //坐标的跳转由大家来完成
			this.setX(this.getX() + 1);
		}
		if (this.down && this.getY()<600-this.getH()) {
			this.setY(this.getY() + 1);
		}
	}
	protected void updateImage() {
//		ImageIcon icon=GameLoad.imgMap.get(fx);
//		System.out.println(icon.getIconHeight());//得到图片的高度
//		如果高度是小于等于0 就说明你的这个图片路径有问题
		this.setIcon(GameLoad.imgMap.get(fx));
	}
}







