package com.tedu.show;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.tedu.element.ElementObj;
import com.tedu.element.Play;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

/**
 * @说明 游戏主面板
 * @author ASUS
 * @功能 元素显示，界面刷新
 */
public class GameMainJPanel extends JPanel{
//管理器
	private ElementManager em;
	
	public GameMainJPanel() {
		init();
		//测试代码
		load();
	}
	
	public void load() {
		//图片导入
		ImageIcon icon = new ImageIcon("图片路径");
		//实例化
		ElementObj obj=new Play(100, 100, 50, 50, icon);
		//添加对象
//		em.getElementsByKey(GameElement.PLAY).add(obj);
		em.addElement(obj, GameElement.PLAY);
	}
	
	public void init() {
		em=ElementManager.getManager();//得到对象
	}
	@Override //用于绘画 Graphics画笔
	public void paint(Graphics g) {
		super.paint(g);
		
		Map<GameElement, List<ElementObj>> all = em.getGameElements();
		Set<GameElement> set = all.keySet();
		//迭代器
		for (GameElement ge:set) {
			List<ElementObj> list = all.get(ge);
			for (int i=0; i<list.size(); i++) {
				ElementObj obj=list.get(i);
				obj.showElement(g);
			}
		}
		
//		g.setColor(new Color(255, 0, 0));
//		g.setFont(new Font("微软雅黑", Font.BOLD, 48));
//		g.drawString("I Hat ", 200, 200);
//		
//		g.fillOval(300, 300, 100, 100);//填充圆
//		g.drawOval(300, 300, 100, 200);//无填充
	}
	
	
}
