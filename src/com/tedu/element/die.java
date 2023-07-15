package com.tedu.element;


import com.tedu.manager.GameLoad;

import java.awt.*;
import javax.swing.Timer;
import java.util.Random;
import javax.swing.ImageIcon;
//继承自ElementObj
public class die extends ElementObj {
    //位置
    private int x;
    private int y;
    //大小
    private int w;
    private int h;
    //是否存活，也就是有没有被吃掉
    private boolean live = true;

    private Timer timer;  // 计时器对象
    private int elapsedTime;  // 经过的时间（以毫秒为单位）

    public die() {
        // 其他初始化代码...
        // 创建计时器，设置为0.5秒（500毫秒）延迟，并注册一个监听器
        timer = new Timer(200, e -> endLife());
        elapsedTime = 0;
    }
    private void endLife() {
        //输出生命已结束
        System.out.println("生命已结束");
        //设置live为false
        live = false;

        timer.stop();  // 停止计时器
    }

    @Override
    //重写createElement方法

    public ElementObj createElement(String str) {

        //设置位置,打爆以后出现一个当前位置的爆炸特效,位置参数由str传入，格式为200;200.代表着x=200，y=200
        String[] split = str.split(";");
        int x=Integer.parseInt(split[0]);
        int y=Integer.parseInt(split[1]);
        this.setX(x);
        this.setY(y);
        //设置大小
        this.setW(30);
        this.setH(30);
        this.setIcon(new ImageIcon("image/boom/boom.png"));
        timer.start();
        elapsedTime = 0;
        return this;
    }

    @Override
    public void showElement(Graphics g) {
        System.out.println("die.showElement触发");
        if(live != false){


        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
        }
    }
}

