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
        //设置位置,打爆以后随机出一个位置的道具
        Random ran=new Random();
        int x=ran.nextInt(800);
        int y=ran.nextInt(500);
        this.setX(x);
        this.setY(y);
        //设置大小
        this.setW(50);
        this.setH(50);
        //50%概率触发addblood,50%触发addattack
//        int probability=ran.nextInt(100);
//        if(probability<50){
//            //设置图片
//            this.setIcon(new ImageIcon("/image/addattack.png"));
//        }else{
//            this.setIcon(new ImageIcon("/image/addblood.png"));
//        }
//        //设置效果
//        this.setAddblood(10);
//        this.setAddattack(10);

        return this;
    }

//    @Override
//    protected void updateImage() {
////		ImageIcon icon=GameLoad.imgMap.get(fx);
////		System.out.println(icon.getIconHeight());//得到图片的高度
////		如果高度是小于等于0 就说明你的这个图片路径有问题
//        this.setIcon(GameLoad.imgMap.get(addblood));
//    }

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }
}

