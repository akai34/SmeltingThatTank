package com.tedu.manager;

import com.tedu.element.ElementObj;

import java.util.List;

public class EnemyCreate {
    //这个方法配合加载器使用，用于调整敌人的加载和BOSS的生成
    public static double HP;
    public static double ATK;
    public static int rounds;//生成轮次 每轮生成5个
    public static int frequency;//生成频率 每隔frequency次时间生成一次
    public static int nowRound = 0;//当前轮次
    public static double intensity;//生成强度 每次生成的敌人的血量和攻击力的倍数
    public static double BossIntensity;//boss生成强度 每次生成的敌人的血量和攻击力的倍数
    public static int BossNum;//boss生成数量

    //    ENEMY=100,20,5,2,1.1;
//    BOSS=4,2;
//    加载器加载敌人的时候，会调用这个方法，用于调整敌人的加载和BOSS的生成
    public static void EnemyCreate(String A) {
//        将数据100,20,5,2,1.1拆分并存入
        String[] a = A.split(",");
        HP = Double.parseDouble(a[0]);
        ATK = Double.parseDouble(a[1]);
        rounds = Integer.parseInt(a[2]);
        frequency = Integer.parseInt(a[3]);
        intensity = Double.parseDouble(a[4]);
    }

    public static void BossCreate(String B) {
//        将数据100,20,5,2,1.1拆分并存入
        String[] b = B.split(",");
        BossIntensity = Double.parseDouble(b[0]);
        BossNum = Integer.parseInt(b[1]);
    }

    //    生成敌人的方法
    private static long time = 0;

    public static void createEnemy(long gametime) {
//        获取em对象
        ElementManager em = ElementManager.getManager();
//        如果相隔时间超过10s或者敌人全部死亡，则加载敌人
//        获取敌人数量
        List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);
        if (nowRound > rounds || gametime != 0 && gametime - time <= 100 && enemys.size() != 0) {
            return;
        }
        time = gametime;
//        创建字符串,包含x y 血量 攻击力 敌人类型
//        如果轮次数达到了，就生成BOSS
        if (nowRound >= rounds) {
            ElementObj obj = GameLoad.getObj("boss");
            nowRound++;
            HP = HP * BossIntensity;
            ATK = ATK * BossIntensity;
            String enemyType = "400,0," + HP + "," + ATK;
            for (int i = 0; i < BossNum; i++) {
                ElementObj element = obj.createElement(enemyType);
                em.addElement(element, GameElement.BOSS);
            }
        } else {
            ElementObj obj = GameLoad.getObj("enemy");
            nowRound++;
            HP = HP * intensity;
            ATK = ATK * intensity;
            String enemyType = "400,0," + HP + "," + ATK;
            ElementObj element = obj.createElement(enemyType);
            em.addElement(element, GameElement.ENEMY);
        }
    }
}

