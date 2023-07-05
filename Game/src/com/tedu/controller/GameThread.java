package com.tedu.controller;

import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.element.ElementObj;
import com.tedu.element.Play;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

/**
 * @说明 游戏的主线程，用于控制游戏加载，游戏关卡，游戏运行时自动化
 * 		游戏判定；游戏地图切换 资源释放和重新读取。。。
 * @author renjj
 * @继承 使用继承的方式实现多线程(一般建议使用接口实现)
 */
public class GameThread extends Thread{
	private ElementManager em;
	
	public GameThread() {
		em=ElementManager.getManager();
	}
	@Override
	public void run() {//游戏的run方法  主线程
		while(true) { //扩展,可以讲true变为一个变量用于控制结束
//		游戏开始前   读进度条，加载游戏资源(场景资源)
			gameLoad();
//		游戏进行时   游戏过程中
			gameRun();
//		游戏场景结束  游戏资源回收(场景资源)
			gameOver();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 游戏的加载
	 */
	private void gameLoad() {
		load();
	}
	/**
	 * @说明  游戏进行时
	 * @任务说明  游戏过程中需要做的事情：1.自动化玩家的移动，碰撞，死亡
	 *                                 2.新元素的增加(NPC死亡后出现道具)
	 *                                 3.暂停等等。。。。。
	 * 先实现主角的移动
	 * */
	private void gameRun() {
		while(true) {// 预留扩展   true可以变为变量，用于控制管关卡结束等
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
//			GameElement.values();//隐藏方法  返回值是一个数组,数组的顺序就是定义枚举的顺序
			for(GameElement ge:GameElement.values()) {
				List<ElementObj> list = all.get(ge);
				for(int i=0;i<list.size();i++) {
					ElementObj obj=list.get(i);//读取为基类
					obj.model();//调用的模板方法 不是move
				}
			}			
			
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**游戏切换关卡*/
	private void gameOver() {
		
	}
	
	public void load() {
//		图片导入
		ImageIcon icon=new ImageIcon("image/tank/play1/player1_up.png");
		ElementObj obj=new Play(100,100,50,50,icon);//实例化对象
//		讲对象放入到 元素管理器中
//		em.getElementsByKey(GameElement.PLAY).add(obj);
		em.addElement(obj,GameElement.PLAY);//直接添加
		
//	    添加一个敌人类，仿照 玩家类编写，注意：不需要时间 键盘监听
//	    实现敌人的显示，同时实现最简单的移动例如： 坐标100,100 移动到   500,100 然后调头
		
		
		
		
	}
}





