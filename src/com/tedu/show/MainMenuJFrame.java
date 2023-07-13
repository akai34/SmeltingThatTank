package com.tedu.show;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuJFrame extends JFrame {
    private JButton startButton;
    private JButton quitButton;
    private JButton showCreditsButton;

    public MainMenuJFrame() {
        setTitle("坦克大战");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // 创建按钮
        startButton = new JButton("开始游戏");
        quitButton = new JButton("退出游戏");
        showCreditsButton = new JButton("制作人员显示");

        // 添加按钮到面板
        panel.add(startButton);
        panel.add(showCreditsButton);
        panel.add(quitButton);

        // 添加面板到窗口
        add(panel);

        // 给开始按钮添加点击事件处理
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加开始游戏的逻辑
                // 可以打开新的游戏界面，或者执行其他相关操作
                System.out.println("点击了开始游戏按钮");

                // 创建游戏界面的实例
                GameJFrame gameJFrame = new GameJFrame();

                // 设置游戏界面的内容和监听器等
                GameMainJPanel gamePanel = new GameMainJPanel();
                GameListener gameListener = new GameListener();
                GameThread gameThread = new GameThread();

                gameJFrame.setjPanel(gamePanel);
                gameJFrame.setKeyListener(gameListener);
                gameJFrame.setThead(gameThread);

                // 启动游戏界面
                gameJFrame.start();

                // 关闭主菜单界面
                dispose();
            }
        });

        // 给退出按钮添加点击事件处理
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加退出游戏的逻辑
                // 可以关闭窗口，或执行其他相关操作
                System.out.println("点击了退出游戏按钮");
                System.exit(0);
            }
        });

        // 给制作人员显示按钮添加点击事件处理
        showCreditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加显示制作人员信息的逻辑
                // 可以打开一个新的窗口或者显示对话框等
                System.out.println("点击了制作人员显示按钮");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenuJFrame mainMenuJFrame = new MainMenuJFrame();
                mainMenuJFrame.setVisible(true);
            }
        });
    }


}
