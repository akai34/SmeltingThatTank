package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.util.Objects;
import java.util.Random;

public class Boss extends Enemy{

    @Override
    public ElementObj createElement(String str) {
        System.out.println("Boss");
//        设置字符串
        this.setL("botLeft");
        this.setR("botRight");
        this.setU("botUp");
        this.setD("botDown");
        String[] split = str.split(",");
        this.setX(Integer.parseInt(split[0]));
        this.setY(Integer.parseInt(split[1]));
        //获取敌人的血量和攻击力，转化为double.赋值给敌人
        this.setHp(Double.parseDouble(split[2]));
        this.setAttack(Double.parseDouble(split[3]));
        setFx("botUp");
        ImageIcon icon2 = GameLoad.imgMap.get(getFx());
        this.setW(icon2.getIconWidth()-10);
        this.setH(icon2.getIconHeight()-10);
        this.setIcon(icon2);
        return this;
    }
    @Override
    public String toString() {// 这里是偷懒，直接使用toString；建议自己定义一个方法
        //  {X:3,y:5,f:up,t:A} json格式
        int x=this.getX();
        int y=this.getY();
        String fxx = null;
        switch(getFx()) { // 子弹在发射时候就已经给予固定的轨迹。可以加上目标，修改json格式
            case "botUp": x+=10;fxx = "up";break;
            // 一般不会写20等数值，一般情况下 图片大小就是显示大小；一般情况下可以使用图片大小参与运算
            case "botLeft": y+=10;fxx = "left"; break;
            case "botRight": x+=25;y+=10;fxx="right"; break;
            case "botDown": y+=25;x+=10;fxx="down"; break;
        }//个人认为： 玩游戏有助于 理解面向对象思想;不能专门玩，需要思考，父类应该怎么抽象，子类应该怎么实现
//		学习技术不犯法，但是不要用技术做犯法的事.
        return "x:"+x+",y:"+y+",f:"+fxx;
    }

}
