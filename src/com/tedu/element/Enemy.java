package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

import java.awt.Graphics;
import java.util.Objects;
import java.util.Random;

import javax.swing.ImageIcon;
import com.tedu.manager.GameLoad;

public class Enemy extends ElementObj{
	private boolean left=false; //左
	private boolean up=false;   //上
	private boolean right=false;//右
	private boolean down=false; //下
	private String fx="enemyUp";
	private boolean pkType=false;//攻击状态 true 攻击  false停止

	private String l = "enemyLeft";
	private String r = "enemyRight";
	private String u = "enemyUp";
	private String d = "enemyDown";
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		//获取敌人的血量和攻击力，转化为double.赋值给敌人
		this.setHp(Double.parseDouble(split[2]));
		this.setAttack(Double.parseDouble(split[3]));
		fx = "enemyLeft";
		ImageIcon icon2 = GameLoad.imgMap.get(fx);
		System.out.println(icon2.getIconWidth()+" "+icon2.getIconHeight());
		this.setW(icon2.getIconWidth()-10);
		this.setH(icon2.getIconHeight()-10);
		this.setIcon(icon2);
		this.setSpeed(1);
		return this;
	}
	@Override
	public void move() {
		changeMove();
		if (this.left && this.getX()>0) {
			this.setX(this.getX() - getSpeed());
		}
		if (this.up  && this.getY()>0) {
			this.setY(this.getY() - getSpeed());
		}
		if (this.right && this.getX()<780-this.getW()) {  //坐标的跳转由大家来完成
			this.setX(this.getX() + getSpeed());
		}
		if (this.down && this.getY()<580-this.getH()) {
			this.setY(this.getY() + getSpeed());
		}
	}
	private Long moveTime = 0L;
	//设置时间限制，每一秒判断一次掉头，随机掉头
	/**
	 * @author L
	 * @说明： 加入了AI随机上下左右掉头
	 */
	private void changeMove() {
		moveTime++;
		if (moveTime % 100 == 0) {//移动了一秒
			Random ran=new Random();
			setF(ran.nextInt(100)%4);
		}
	}
	
	@Override
	protected void updateImage() {
//		ImageIcon icon=GameLoad.imgMap.get(fx);
//		System.out.println(icon.getIconHeight());//得到图片的高度
//		如果高度是小于等于0 就说明你的这个图片路径有问题
		this.setIcon(GameLoad.imgMap.get(fx));
	}
	/**
	 * @author L
	 * @说明 设置AI方向
	 * x=0,1,2,3 上下左右
	 */
	private void setF(int x) {
		if (x==0) {
			fx=u;
			up=true;
			down=false;
			left=false;
			right=false;
		}
		if (x==1) {
			fx=d;
			up=false;
			down=true;
			left=false;
			right=false;
		}
		if (x==2) {
			fx=l;
			up=false;
			down=false;
			left=true;
			right=false;
		}
		if (x==3) {
			fx=r;
			up=false;
			down=false;
			left=false;
			right=true;
		}
	}
	

	
	
	//如果撞到了，那就自己回退
	@Override
	/**
	 * @author L
	 * @改变 加入了碰撞后方向随机变成3个方向
	 */
	public void returnMove() {
//		System.out.println("撞到了");
		int speed = getSpeed() + 1;
		if (up) this.setY(this.getY() + speed);
		if (down) this.setY(this.getY() - speed);
		if (left) this.setX(this.getX() + speed);
		if (right) this.setX(this.getX() - speed);
		//方向改变
		Random ran=new Random();
		int nxt=ran.nextInt(100)%4;//下一个方向
		if (up && nxt==0) {
			nxt=(nxt+(ran.nextInt()%3)+1)%4;
		}
		if (down && nxt==1) {
			nxt=(nxt+(ran.nextInt()%3)+1)%4;
		}
		if (left && nxt==2) {
			nxt=(nxt+(ran.nextInt()%3)+1)%4;
		}
		if (right && nxt==3) {
			nxt=(nxt+(ran.nextInt()%3)+1)%4;
		}
		setF(nxt);
	}
	@Override   //添加子弹
	public void add(long gameTime) {//有啦时间就可以进行控制
		//70论发射一次子弹（前面不发先
		if (moveTime % 90 != 20)
			return;
		ElementObj obj=GameLoad.getObj("file");
		ElementObj element = obj.createElement(this.toString());
		element.setType(2);
//		System.out.println("子弹是否为空"+element);
		//设置子弹的攻击力
		element.setAttack(this.getAttack());
		//设置子弹的速度
		element.setMoveNum(7);
//		装入到集合中
		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
//		如果要控制子弹速度等等。。。。还需要代码编写
	}
	@Override
	public String toString() {// 这里是偷懒，直接使用toString；建议自己定义一个方法
		//  {X:3,y:5,f:up,t:A} json格式
		int x=this.getX();
		int y=this.getY();
		String fxx = null;
		switch(this.fx) { // 子弹在发射时候就已经给予固定的轨迹。可以加上目标，修改json格式
			case "enemyUp": x+=10;fxx = "up";break;
			// 一般不会写20等数值，一般情况下 图片大小就是显示大小；一般情况下可以使用图片大小参与运算
			case "enemyLeft": y+=10;fxx = "left"; break;
			case "enemyRight": x+=25;y+=10;fxx="right"; break;
			case "enemyDown": y+=25;x+=10;fxx="down"; break;
		}//个人认为： 玩游戏有助于 理解面向对象思想;不能专门玩，需要思考，父类应该怎么抽象，子类应该怎么实现
//		学习技术不犯法，但是不要用技术做犯法的事.
		return "x:"+x+",y:"+y+",f:"+fxx;
	}
//	设置fx
	public void setFx(String fx) {
		this.fx = fx;
	}
//	获取fx
	public String getFx() {
		return fx;
	}
//	private String l = "enemyLeft";
//	private String r = "enemyRight";
//	private String u = "enemyUp";
//	private String d = "enemyDown";
//	设置字符串
	public void setL(String l) {
		this.l = l;
	}
	public void setR(String r) {
		this.r = r;
	}
	public void setU(String u) {
		this.u = u;
	}
	public void setD(String d) {
		this.d = d;
	}
}
