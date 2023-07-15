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

    public MainMenuJFrame()  {
        setTitle("坦克大战");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // 将窗口居中显示

        // 创建自定义面板
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 绘制背景图片
                ImageIcon backgroundImage = new ImageIcon("image/login_background.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // 设置面板透明
        buttonPanel.setLayout(new GridBagLayout());

        // 创建按钮
        startButton = new JButton("开始游戏");
        quitButton = new JButton("退出游戏");
        showCreditsButton = new JButton("制作人员显示");

        // 添加按钮到面板
        buttonPanel.add(startButton);
        buttonPanel.add(showCreditsButton);
        buttonPanel.add(quitButton);

        // 设置按钮样式
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
        startButton.setFont(buttonFont);
        showCreditsButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);

        // 将按钮面板添加到自定义面板的中间
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // 设置自定义面板为内容面板
        setContentPane(backgroundPanel);



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

                //打开制作人员信息窗口
                dispose();//关闭主菜单界面
                CreditsJFrame creditsJFrame = new CreditsJFrame();
                creditsJFrame.setVisible(true);



            }
        });
    }

    public static void Start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenuJFrame mainMenuJFrame = new MainMenuJFrame();
                mainMenuJFrame.setVisible(true);
            }
        });
    }


}
