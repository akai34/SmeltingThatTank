package com.tedu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tedu.element.ElementObj;

/**
 * @说明 元素管理器
 * @author ASUS
 * @ 一：存放
 * @ 二：管理器单列
 */
public class ElementManager {
	/*
	 * key(枚举)->list<元素基类>
	 */
	private Map<GameElement, List<ElementObj>> gameElements;

	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
	
	/*
	 * 单例
	 * 1静态属性
	 * 2静态方法
	 * 3私有化构造
	 */
	
	private static ElementManager EM=null;
	// 线程锁
	public static synchronized ElementManager getManager() {
		if (EM == null) {
			EM=new ElementManager();
		}
		return EM;
	}
	//添加元素
	public void addElement(ElementObj obj, GameElement ge) {
//		List<ElementObj> list=gameElements.get(ge);
//		list.add(obj);
		//等价于
		gameElements.get(ge).add(obj);
	}
	//用key返回list
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	private ElementManager() {
		init();
	}
	//加载实例化
//	static {
//		EM=new ElementManager();
//	}
	public void init() {
		gameElements = new HashMap<GameElement, List<ElementObj>>();
		gameElements.put(GameElement.PLAY, new ArrayList<ElementObj>());
		//...
	}
	
}
