package com.tedu.show;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditsJFrame extends JFrame {
    private JButton backButton;

    public CreditsJFrame() {
        setTitle("制作人员显示");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null); // 将窗口居中显示

        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE); // 设置面板背景颜色

        // 添加标题标签
        JLabel titleLabel = new JLabel("冶炼那fish小组");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // 添加垂直间距
        panel.add(titleLabel);

        // 添加制作人员名字标签
        JLabel nameLabel = new JLabel("制作人员：林杨超（组长），陈锐敬，庄宇霖，陈书朗，李梓健");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // 添加垂直间距
        panel.add(nameLabel);

        // 添加返回按钮
        backButton = new JButton("返回主菜单");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // 添加垂直间距
        panel.add(backButton);

        // 给返回按钮添加点击事件处理
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭制作人员显示窗口，返回主菜单
                dispose();
                MainMenuJFrame mainMenuJFrame = new MainMenuJFrame();
                mainMenuJFrame.setVisible(true);
            }
        });

        // 将面板添加到窗口的中央
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreditsJFrame creditsJFrame = new CreditsJFrame();
                creditsJFrame.setVisible(true);
            }
        });
    }

}
