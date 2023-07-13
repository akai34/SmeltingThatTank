package com.tedu.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import com.tedu.element.ElementObj;
import com.tedu.element.Enemy;
import com.tedu.element.MapObj;
import com.tedu.element.Play;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.show.GameMainJPanel;


//引入playerMoveNum
import static com.tedu.element.Play.playerMoveNum;
/**
 * @author renjj
 * @说明 游戏的主线程，用于控制游戏加载，游戏关卡，游戏运行时自动化
 * 游戏判定；游戏地图切换 资源释放和重新读取。。。
 * @继承 使用继承的方式实现多线程(一般建议使用接口实现)
 */
public class GameThread extends Thread {
	private ElementManager em;
	//初始关卡
	private int checkPoint = 1;
	private int killCount = 0;

	//杀敌数
	public GameThread() {
		em = ElementManager.getManager();
	}

	@Override
	public void run() {//游戏的run方法  主线程
		while (true) { //扩展,可以讲true变为一个变量用于控制结束
//		游戏开始前   读进度条，加载游戏资源(场景资源)
			gameLoad();
//		游戏进行时   游戏过程中
			gameRun();
//		游戏场景结束  游戏资源回收(场景资源)
			gameOver();
//			展示游戏结果
			//弹窗统计杀敌记录，休眠5秒
			JFrame resultFrame = new JFrame("关卡结果");
			JLabel killCountLabel = new JLabel("杀敌数: " + killCount);
			resultFrame.getContentPane().add(killCountLabel);
			resultFrame.setBounds(400, 400, 200, 200);
			resultFrame.setLocationRelativeTo(null);//屏幕居中显示
			resultFrame.setVisible(true);
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
//			System.out.println("-------qchu--------");
			resultFrame.dispose();
			if (checkPoint == 3)
				break;
		}
	}

	/**
	 * 游戏的加载
	 */
	private void gameLoad() {
		GameLoad.loadImg(); //加载图片
		GameLoad.loadPlay();//也可以带参数，单机还是2人
//		加载敌人NPC等
		GameLoad.MapLoad(checkPoint);//可以变为 变量，每关卡重新加载  加载地图
//		加载敌人NPC
		List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);
		//获取主角信息
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		//设置敌人和玩家血量为100，攻击力为20
		for (ElementObj enemy : enemys) {
			Enemy e = (Enemy) enemy;
			e.setHp(100);
			e.setAttack(20);
		}
		//设置主角血量为100，攻击力为20
		for (ElementObj p : play) {
			Play play1 = (Play) p;
			play1.setHp(100);
			play1.setAttack(5);
		}
		//设置砖块血量为100
		List<ElementObj> maps = em.getElementsByKey(GameElement.MAPS);
		for (ElementObj map : maps) {
			MapObj m = (MapObj) map;
			m.setHp(100);
		}
		killCount += enemys.size();
//		全部加载完成，游戏启动
	}

	/**
	 * @说明 游戏进行时
	 * @任务说明 游戏过程中需要做的事情：1.自动化玩家的移动，碰撞，死亡
	 * 2.新元素的增加(NPC死亡后出现道具)
	 * 3.暂停等等。。。。。
	 * 先实现主角的移动
	 */

	private void gameRun() {
		long gameTime = 0L;//给int类型就可以啦
		while (!isFinished() && !isDead()) {// 预留扩展   true可以变为变量，用于控制管关卡结束等
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
			List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);
			List<ElementObj> files = em.getElementsByKey(GameElement.PLAYFILE);
			List<ElementObj> maps = em.getElementsByKey(GameElement.MAPS);
			List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
			//加一个Dianabol，道具类
			List<ElementObj> dianabols = em.getElementsByKey(GameElement.DIANABOL);
			moveAndUpdate(all, gameTime);//	游戏元素自动化方法

			ElementPK(enemys, files); //敌人和玩家子弹的碰撞
			ElementPK(maps, files); //子弹和地图的碰撞
			ElementPK(play, files); //主角和子弹的碰撞
			tankPkBlock(enemys, maps); //敌人和地图的碰撞
			tankPkBlock(play, maps);	//主角和地图的碰撞
			tankPkDianabol(play, dianabols); //主角和道具的碰撞
			gameTime++;//唯一的时间控制
			try {
				sleep(10);//默认理解为 1秒刷新100次
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void tankPkDianabol(List<ElementObj> play, List<ElementObj> dianabols) {
		System.out.println("pkDianabol触发");
		for (int i = 0; i < play.size(); i++) {
			ElementObj player = play.get(i);//定义玩家的对象
			for (int j = 0; j < dianabols.size(); j++) {
				//定义道具箱的对象
				ElementObj dianabol = dianabols.get(j);
				//如果玩家和道具箱碰撞，或者玩家处于道具的范围内，玩家的攻击速度变化
				if (player.pk(dianabol)) {
					//攻击速度变化
					//playerMoveNum+1 playerMoveNum是全局变量
					//输出玩家的移动速度
					playerMoveNum+=2;
					System.out.println("玩家的移动速度为：" + playerMoveNum);
					//设置玩家的攻击力
					for (ElementObj p : play) {
						Play play1 = (Play) p;
						play1.setAttack(play1.getAttack()+1);
					}

					dianabol.setLive(999999);
				}
			}
			}
		}

	public void ElementPK(List<ElementObj> listA, List<ElementObj> listB) {
//		请大家在这里使用循环，做一对一判定，如果为真，就设置2个对象的死亡状态
		for (int i = 0; i < listA.size(); i++) {
			ElementObj A = listA.get(i);
			for (int j = 0; j < listB.size(); j++) {
				ElementObj B = listB.get(j);
				if (A.pk(B)) {
					//输出文字提醒谁射到了谁
//					System.out.println(A + "射到了" + B);
//					为什么不能直接设置为false，因为子弹发射在体内，特别难改
//					当收攻击方法里执行时，如果血量减为0 再进行设置生存为 false
//					扩展 留给大家
					//如果是玩家子弹且承受者是敌人
					if (B.getType()	== 1 && A.getClass() == Enemy.class) {
						A.setLive(B.getAttack());
						B.setLive(A.getAttack());
					}//如果是敌人子弹且承受着是玩家
					else if (B.getType() == 2 && A.getClass() == Play.class) {
						A.setLive(B.getAttack());
						B.setLive(A.getAttack());
					}//如果承受者是墙体
					else if (A.getClass() == MapObj.class) {
						A.setLive(B.getAttack());
						B.setLive(A.getAttack());
					}
					break;
				}
			}
		}
	}

	//人跟墙的碰撞
	public void tankPkBlock(List<ElementObj> listA, List<ElementObj> listB) {
		for (int i = 0; i < listA.size(); i++) {
			ElementObj tank = listA.get(i);
			for (int j = 0; j < listB.size(); j++) {
				ElementObj block = listB.get(j);
				if (tank.pk(block) || tank.getX() < 0 || tank.getX() > 800 ||
						tank.getY() < 0 || tank.getY() > 600) {
//					问题： 如果是boos，那么也一枪一个吗？？？？
//					将 setLive(false) 变为一个受攻击方法，还可以传入另外一个对象的攻击力
//					当收攻击方法里执行时，如果血量减为0 再进行设置生存为 false
//					扩展 留给大家
//					System.out.println(listB);
					tank.returnMove();
					break;
				}
			}
		}
	}


	//	游戏元素自动化方法
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all, long gameTime) {
//		GameElement.values();//隐藏方法  返回值是一个数组,数组的顺序就是定义枚举的顺序
		for (GameElement ge : GameElement.values()) {
			List<ElementObj> list = all.get(ge);
//			编写这样直接操作集合数据的代码建议不要使用迭代器。
//			for(int i=0;i<list.size();i++) {
			for (int i = list.size() - 1; i >= 0; i--) {
				ElementObj obj = list.get(i);//读取为基类
				if (!obj.isLive()) {//如果死亡
//					list.remove(i--);  //可以使用这样的方式
//					启动一个死亡方法(方法中可以做事情例如:死亡动画 ,掉装备)
					obj.die();//需要大家自己补充
					//输出提示，xx被移除
					//System.out.println(obj + "被移除");
					list.remove(i);
					continue;
				}
				obj.model(gameTime);//调用的模板方法 不是move
			}
		}
	}


	/**
	 * 游戏切换关卡
	 */
	private void gameOver() {
		//清除所有东西
		List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);
		List<ElementObj> files = em.getElementsByKey(GameElement.PLAYFILE);
		List<ElementObj> maps = em.getElementsByKey(GameElement.MAPS);
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		enemys.clear();
		files.clear();
		maps.clear();
		play.clear();


	}

	private boolean isFinished() { //判断是否通关
//		System.out.println("看看是不是空的");
		List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);
		if (enemys.isEmpty()) {
			System.out.println("空的！！");
			checkPoint++;
			return true;
		}
		return false;
	}
	private boolean isDead() { //判断是否死亡
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		if (play.isEmpty()) {
			System.out.println("死了！！");
			return true;
		}
		return false;
	}
}





