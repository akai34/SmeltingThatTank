package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class MapObj extends ElementObj{
//	墙需要血量	
	private int hp;
	private String name;//墙的type  也可以使用枚举
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(),
				this.getX(), this.getY(),
				this.getW(),this.getH(),null);
	}
	
	@Override   // 如果可以传入   墙类型,x,y
	public ElementObj createElement(String str) {
//		System.out.println(str); // 名称,x,y
		//确认数据传输是对的。 因为只要是使用的配置文件，那么配置文件的格式一定要正确。
//		只要是需要做字符串解析，那么一定要保证字符串的格式是符合要求的
		String []arr=str.split(",");
//		先写一个假图片再说
		ImageIcon icon=null;
		switch(arr[0]) { //设置图片信息 图片还未加载到内存中
		case "GRASS": icon=new ImageIcon("image/wall/grass.png");
			this.hp=4;
			name="GRASS";
			break;
		case "BRICK": icon=new ImageIcon("image/wall/brick.png");
			this.hp=4;
			name="BRICK";
			break;
		case "RIVER": icon=new ImageIcon("image/wall/river.png");break;
		case "IRON": icon=new ImageIcon("image/wall/iron.png");
					this.hp=4;
					name="IRON";
					break;
					//新增一个道具类型的墙，这个墙可以被打掉，打掉之后会出现一个道具
		case "Dianabol": icon=new ImageIcon("image/wall/Dianabol.png");
					this.hp=5;
					name="Dianabol";
					break;
		}
//		this.hp = 20;
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=icon.getIconWidth();
		int h=icon.getIconHeight();
		this.setH(h);
		this.setW(w);
		this.setX(x);
		this.setY(y);
		this.setIcon(icon);
		return this;
	}
	@Override  //说明 这个设置扣血等的方法 需要自己思考重新编写。
	public void setLive(double atk) {
//			被调用一次 就减少一次血。
		if ("IRON".equals(name)) {// 水泥墙需要4下
			this.hpNow -= atk;
			if (this.hpNow <= 0) {
				this.live = false;
				return;
			}
		}
		//如果是道具墙，那么需要做特殊判断生产
		if ("Dianabol".equals(name)) {
			this.hpNow -= atk;
			if (this.hpNow > 0) {
				return;
			}
			//如果小于0，触发创建道具的方法
			else if (this.hpNow <= 0) {
				//创建道具
				//输出提示消息：创建了一个道具
				System.out.println("创建了一个道具");
				String DianabolStr="000";
				ElementObj obj= GameLoad.getObj("Dianabol");
				ElementObj element = obj.createElement(DianabolStr);
				ElementManager.getManager().addElement(element, GameElement.DIANABOL);
				System.out.println("已经加入到道具集合中了");
				int x1=this.getX();
				int y1=this.getY();
				String DieStr=x1+";"+y1;

				ElementObj obj1= GameLoad.getObj("die");
				ElementObj element1 = obj1.createElement(DieStr);
				ElementManager.getManager().addElement(element1, GameElement.DIE);
				this.live = false;
			}

		}
		if ("BRICK".equals(name)) {
			this.hpNow -= atk;
			if (this.hpNow > 0) {
				return;
			}
			//如果小于0，触发创建道具的方法
			else if (this.hpNow <= 0) {
				//有一定几率创建道具,写出概率
				Random ran=new Random();
				int probability=ran.nextInt(100);
				if(probability<=10){
				System.out.println("创建了一个道具");
				String DianabolStr="000";
				ElementObj obj= GameLoad.getObj("Dianabol");
				ElementObj element = obj.createElement(DianabolStr);
				ElementManager.getManager().addElement(element, GameElement.DIANABOL);
				System.out.println("已经加入到道具集合中了");
				}
				int x=this.getX();
				int y=this.getY();
				String DieStr=x+";"+y;

				ElementObj obj= GameLoad.getObj("die");
				ElementObj element = obj.createElement(DieStr);
				ElementManager.getManager().addElement(element, GameElement.DIE);
				this.live = false;
			}

		}
		if ("GRASS".equals(name)) {
			this.hpNow -= atk;
			if (this.hpNow > 0) {
				return;
			}
			//如果小于0，触发创建道具的方法
			else if (this.hpNow <= 0) {
				//有一定几率创建道具,写出概率
				Random ran=new Random();
				int probability=ran.nextInt(100);
				if(probability<=10){
					System.out.println("创建了一个道具");
					String DianabolStr="000";
					ElementObj obj= GameLoad.getObj("Dianabol");
					ElementObj element = obj.createElement(DianabolStr);
					ElementManager.getManager().addElement(element, GameElement.DIANABOL);
					System.out.println("已经加入到道具集合中了");
				}
				int x=this.getX();
				int y=this.getY();
				String DieStr=x+";"+y;

				ElementObj obj= GameLoad.getObj("die");
				ElementObj element = obj.createElement(DieStr);
				ElementManager.getManager().addElement(element, GameElement.DIE);
				this.live = false;
			}

		}
		super.setLive(atk);
	}
	
}




