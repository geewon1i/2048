package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestModeSelectionFrame extends JFrame {

    public GuestModeSelectionFrame() {
        initJFrame();
    }

    private void initJFrame() {
        // 设置框架的标题
        this.setTitle("Game menus");

        // 设置默认的关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置框架大小
        setSize(500, 300);

        // 设置框架居中显示
        setLocationRelativeTo(null);

        // 创建主面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1, 10, 10));

        // 创建单选按钮
        JRadioButton classicModeButton = new JRadioButton("Classic Mode");
        JRadioButton timeModeButton = new JRadioButton("Time Mode");
        JRadioButton customModeButton = new JRadioButton("Custom Mode");
        classicModeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        timeModeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        customModeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        // 创建按钮组，将单选按钮添加到按钮组中
        ButtonGroup group = new ButtonGroup();
        group.add(classicModeButton);
        group.add(timeModeButton);
        group.add(customModeButton);

        // 默认选择第一个单选按钮
        classicModeButton.setSelected(true);

        // 创建确认按钮
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));

        // 创建输入字段和标签
        JLabel gridSizeLabel = new JLabel("Grid Size:");
        gridSizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField gridSizeField = new JTextField();
        gridSizeField.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel targetNumberLabel = new JLabel("Target Number:");
        targetNumberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField targetNumberField = new JTextField();
        targetNumberField.setFont(new Font("Arial", Font.PLAIN, 16));
        gridSizeLabel.setVisible(false);
        gridSizeField.setVisible(false);
        targetNumberLabel.setVisible(false);
        targetNumberField.setVisible(false);

        // 添加单选按钮到面板
        panel.add(new JLabel());
        panel.add(classicModeButton);
        panel.add(timeModeButton);
        panel.add(customModeButton);
        panel.add(gridSizeLabel);
        panel.add(gridSizeField);
        panel.add(targetNumberLabel);
        panel.add(targetNumberField);
        panel.add(confirmButton);

        classicModeButton.addActionListener(e -> {
            gridSizeLabel.setVisible(false);
            gridSizeField.setVisible(false);
            targetNumberLabel.setVisible(false);
            targetNumberField.setVisible(false);
        });

        timeModeButton.addActionListener(e -> {
            gridSizeLabel.setVisible(false);
            gridSizeField.setVisible(false);
            targetNumberLabel.setVisible(false);
            targetNumberField.setVisible(false);
        });

        customModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridSizeLabel.setVisible(true);
                gridSizeField.setVisible(true);
                targetNumberLabel.setVisible(true);
                targetNumberField.setVisible(true);
            }
        });
        // 添加确认按钮的事件监听器
        confirmButton.addActionListener(e -> {
            if (classicModeButton.isSelected()) {
                this.dispose();
                GuestGameFrame gameFrame = new GuestGameFrame(700, 500,0,2048,4);
                gameFrame.setVisible(true);
                // 添加切换到经典模式的逻辑
            }else if (timeModeButton.isSelected()){
                //限时模式
                this.dispose();
                GuestGameFrame gameFrame = new GuestGameFrame(700, 500,1,2048,4);
                gameFrame.setVisible(true);
            }
            else if (customModeButton.isSelected()) {
                String gridSizeText = gridSizeField.getText();
                String targetNumberText = targetNumberField.getText();
                if (gridSizeText.isEmpty() || targetNumberText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入大小或目标数字");
                } else {
                    this.dispose();
                    GuestGameFrame gameFrame = new GuestGameFrame(700, 500,2,Integer.parseInt(targetNumberText),Integer.parseInt(gridSizeText));
                    gameFrame.setVisible(true);
                    // 创建自定义的游戏
                }
            }
        });


        // 添加面板到框架
        add(panel);

        // 设置框架可见
        setVisible(true);


    }

}
