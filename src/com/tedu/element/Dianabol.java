package com.tedu.element;


import com.tedu.manager.GameLoad;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;
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

    //效果，分为提升的攻击力和提升的血量
    private int addblood;
    private int addattack;
    @Override
    //重写createElement方法
    public ElementObj createElement(String str) {

        System.out.println("Dianabol.createElement触发");
        //设置位置,打爆以后随机出一个位置的道具
        Random ran=new Random();
        int x=ran.nextInt(800);
        int y=ran.nextInt(500);
        this.setX(x);
        this.setY(y);
        //设置大小
        this.setW(30);
        this.setH(30);
//        50%概率触发addblood,50%触发addattack

        int probability=ran.nextInt(100);
        if(probability<50){
            //设置图片
            this.setIcon(new ImageIcon("image/addattact.png"));
        }else{
            this.setIcon(new ImageIcon("image/addblood.png"));
        }
        return this;
    }

//    @Override
//    protected void updateImage() {
//
//    }

    @Override
    public void showElement(Graphics g) {
        //System.out.println("Dianabol.showElement触发");

        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }
}

