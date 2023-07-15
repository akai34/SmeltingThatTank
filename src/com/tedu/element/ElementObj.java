package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import com.tedu.element.die;

/**
 * @author renjj
 * @说明 所有元素的基类。
 */
public abstract class ElementObj {

    private int x;
    private int y;
    private int w;
    private int h;
    private ImageIcon icon;
    //	还有。。。。 各种必要的状态值，例如：是否生存.
    boolean live = true; //生存状态 true 代表存在，false代表死亡
    // 可以采用枚举值来定义这个(生存，死亡，隐身，无敌)
//	注明：当重新定义一个用于判定状态的变量，需要思考：1.初始化 2.值的改变 3.值的判定
    private int type = 0;//拓展了一个叫做type的类型用来表示子弹的类型，1为自己的，2为敌人的
    //血量、攻击、当前血量、速度hp,hpNow、attack、moveNum
    private double hp;
    double hpNow;
    private double attack;
    private int moveNum;
    private int speed;

    public ElementObj() {    //这个构造其实没有作用，只是为继承的时候不报错写的
    }

    /**
     * @param x    左上角X坐标
     * @param y    左上角y坐标
     * @param w    w宽度
     * @param h    h高度
     * @param icon 图片
     * @说明 带参数的构造方法; 可以由子类传输数据到父类
     */
    public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.icon = icon;
    }

    /**
     * @param g 画笔 用于进行绘画
     * @说明 抽象方法，显示元素
     */
    public abstract void showElement(Graphics g);

    public void showBloodNow(Graphics g) { //显示当前血量
        //如果没有掉血，就不显示、
        if (this.getHp() == this.getHpNow()) {
            return;
        }
        //如果为子弹，就不显示
        if (this instanceof PlayFile) {
            return;
        }
        //设置颜色为红色
        g.setColor(java.awt.Color.RED);
        g.drawRect(this.getX(), this.getY() - 10, this.getW(), 5);
        g.fillRect(this.getX(), this.getY() - 10, (int) (this.getW() * this.getHpNow() / this.getHp()), 5);
    }

    /**
     * @param bl  点击的类型  true代表按下，false代表松开
     * @param key 代表触发的键盘的code值
     * @说明 使用父类定义接收键盘事件的方法
     * 只有需要实现键盘监听的子类，重写这个方法(约定)
     * @说明 方式2 使用接口的方式;使用接口方式需要在监听类进行类型转换
     * @题外话 约定  配置  现在大部分的java框架都是需要进行配置的.
     * 约定优于配置
     * @扩展 本方法是否可以分为2个方法？1个接收按下，1个接收松开(给同学扩展使用)
     */
    public void keyClick(boolean bl, int key) {  //这个方法不是强制必须重写的。
        System.out.println("测试使用");
    }

    /**
     * @说明 移动方法; 需要移动的子类，请 重写这个方法
     */
    protected void move() {
    }

    /**
     * @设计模式 模板模式;在模板模式中定义 对象执行方法的先后顺序,由子类选择性重写方法
     * 1.移动  2.换装  3.子弹发射
     */
    public final void model(long gameTime) {
//		先换装
        updateImage();
//		在移动
        move();
//		在发射子弹
        add(gameTime);
    }

    //	 long ... aaa  不定长的 数组,可以向这个方法传输 N个 long类型的数据
    protected void updateImage() {
    }

    protected void add(long gameTime) {
    }

    //	死亡方法  给子类继承的
    public void die() {  //死亡也是一个对象

    }


    public ElementObj createElement(String str) {

        return null;
    }

    /**
     * @return
     * @说明 本方法返回 元素的碰撞矩形对象(实时返回)
     */
    public Rectangle getRectangle() {
//		可以将这个数据进行处理 
        return new Rectangle(x, y, w, h);
    }

    /**
     * @param obj
     * @return boolean 返回true 说明有碰撞，返回false说明没有碰撞
     * @说明 碰撞方法
     * 一个是 this对象 一个是传入值 obj
     */
    public boolean pk(ElementObj obj) {
        return this.getRectangle().intersects(obj.getRectangle());
    }

    public void returnMove() {
    }//额外添加的方法，用于返回移动方向，


    /**
     * 只要是 VO类 POJO 就要为属性生成 get和set方法
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(double atk) { //被调用了就减少其声明
//			被调用一次 就减少一次血。
		this.hpNow -= atk;
		if (this.hpNow <= 0) {

//            int x=this.getX();
//            int y=this.getY();
//            String DieStr=x+";"+y;
//            ElementObj obj= GameLoad.getObj("die");
//            ElementObj element = obj.createElement(DieStr);
//            ElementManager.getManager().addElement(element, GameElement.DIE);
            this.live = false;
		}
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //血量、攻击、当前血量、速度hp，hpNow、attack、moveNum的get和set方法
	public double getHp() {
		return hp;
	}
	public void setHp(double hp) {//设置血量的同时，将当前血量也设置为血量
		this.hp = hp;
        this.hpNow = hp;
	}
	public double getHpNow() {
		return hpNow;
	}
	public void setHpNow(double hpNow) {
		this.hpNow = hpNow;
	}

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getAttack() {
        return attack;
    }
    public void setMoveNum(int moveNum) {
        this.moveNum = moveNum;
    }

    public int getMoveNum() {
        return moveNum;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}










