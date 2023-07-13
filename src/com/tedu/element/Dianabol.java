package com.tedu.element;


import java.awt.*;

//继承自ElementObj
public class Dianabol extends ElementObj {
    //位置
    private int x;
    private int y;
    //大小
    private int w;
    private int h;

    //是否存活，也就是有没有被吃掉
    private boolean live = true;
    //产生的概率
    private int probability;
//    //图片
//    private ImageIcon icon;







    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }
}

