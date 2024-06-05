package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ModeSelectionFrame extends JFrame {
    public String username;
    private BufferedImage image;

    public ModeSelectionFrame() {
        initJFrame();
    }

    public ModeSelectionFrame(String username) {
        this.username = username;
        initJFrame();
    }

    private void initJFrame() {
        // 设置框架的标题
        this.setTitle("Game menus");

        // 设置默认的关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置框架大小
        setSize(500, 400);

        // 设置框架居中显示
        setLocationRelativeTo(null);

        try {
            image = ImageIO.read(new File("src/view/1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLayeredPane layeredPane = getLayeredPane();

        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setBounds(0, 0, 500, 400);

        

        // 创建主面板
        JPanel panel = new JPanel();
        panel.setBounds(50, 50, 400, 300);
        panel.setLayout(new GridLayout(11, 1, 10, 10));
        panel.setOpaque(false);


        // 创建单选按钮
        JRadioButton classicModeButton = new JRadioButton("Classic Mode");
        classicModeButton.setFocusPainted(false);
        JRadioButton AIModeButton = new JRadioButton("AI Mode");
        AIModeButton.setFocusPainted(false);
        AIModeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        JRadioButton timeModeButton = new JRadioButton("Time Mode");
        timeModeButton.setFocusPainted(false);
        JRadioButton customModeButton = new JRadioButton("Custom Mode");
        classicModeButton.setFocusPainted(false);
        classicModeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        timeModeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        customModeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        // 创建按钮组，将单选按钮添加到按钮组中
        ButtonGroup group = new ButtonGroup();
        group.add(classicModeButton);
        group.add(AIModeButton);
        group.add(timeModeButton);
        group.add(customModeButton);


        // 默认选择第一个单选按钮
        classicModeButton.setSelected(true);

        // 创建确认按钮
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));

        // 创建输入字段和标签
        JLabel timeLabel = new JLabel("Time :");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField timeLabelField = new JTextField();
        timeLabelField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel gridSizeLabel = new JLabel("Grid Size:");
        gridSizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField gridSizeField = new JTextField();
        gridSizeField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel targetNumberLabel = new JLabel("Target Number:");
        targetNumberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField targetNumberField = new JTextField();
        targetNumberField.setFont(new Font("Arial", Font.PLAIN, 16));
        timeLabelField.setVisible(false);
        timeLabel.setVisible(false);
        gridSizeLabel.setVisible(false);
        gridSizeField.setVisible(false);
        targetNumberLabel.setVisible(false);
        targetNumberField.setVisible(false);

        // 添加单选按钮到面板
        panel.add(classicModeButton);
        panel.add(AIModeButton);

        panel.add(timeModeButton);
        panel.add(timeLabel);
        panel.add(timeLabelField);
        panel.add(customModeButton);
        panel.add(gridSizeLabel);
        panel.add(gridSizeField);
        panel.add(targetNumberLabel);
        panel.add(targetNumberField);
        panel.add(confirmButton);

        classicModeButton.addActionListener(e -> {
            timeLabel.setVisible(false);
            timeLabelField.setVisible(false);
            gridSizeLabel.setVisible(false);
            gridSizeField.setVisible(false);
            targetNumberLabel.setVisible(false);
            targetNumberField.setVisible(false);
        });
        AIModeButton.addActionListener(e -> {
            timeLabel.setVisible(false);
            timeLabelField.setVisible(false);
            gridSizeLabel.setVisible(false);
            gridSizeField.setVisible(false);
            targetNumberLabel.setVisible(false);
            targetNumberField.setVisible(false);
        });

        timeModeButton.addActionListener(e -> {
            timeLabel.setVisible(true);
            timeLabelField.setVisible(true);
            gridSizeLabel.setVisible(false);
            gridSizeField.setVisible(false);
            targetNumberLabel.setVisible(false);
            targetNumberField.setVisible(false);
        });

        customModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setVisible(false);
                timeLabelField.setVisible(false);
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
                GameFrame gameFrame = new GameFrame(700, 500,username,0,2048,4,0);
                gameFrame.setVisible(true);
                // 添加切换到经典模式的逻辑
            }else if (timeModeButton.isSelected()){
                //限时模式
                String time=timeLabelField.getText();
                this.dispose();
                GameFrame gameFrame = new GameFrame(700, 500,username,1,2048,4,Integer.parseInt(time));
                gameFrame.setVisible(true);
            }
            else if (customModeButton.isSelected()) {
                String gridSizeText = gridSizeField.getText();
                String targetNumberText = targetNumberField.getText();
                if (gridSizeText.isEmpty() || targetNumberText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入大小或目标数字");
                } else {
                    this.dispose();
                    GameFrame gameFrame = new GameFrame(700, 500,username,2,Integer.parseInt(targetNumberText),Integer.parseInt(gridSizeText),0);
                    gameFrame.setVisible(true);
                    // 创建自定义的游戏
                }
            }
            else if (AIModeButton.isSelected()) {
                this.dispose();
                AIGameFrame1 gameFrame1 = null;
                try {
                    gameFrame1 = new AIGameFrame1(700, 500, 0, 2048, 4, 0);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                gameFrame1.setVisible(true);

                AIGameFrame1 finalGameFrame = gameFrame1;
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        int count = 1;
                        while (count <= 10000) {
                            try {
                                finalGameFrame.ai = new AI(finalGameFrame.gamePanel.getModel());
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }

                            char op = finalGameFrame.ai.findBestMove();
                            switch (op) {
                                case 'w':
                                    finalGameFrame.gamePanel.doMoveUp();
                                    break;
                                case 's':
                                    finalGameFrame.gamePanel.doMoveDown();
                                    break;
                                case 'a':
                                    finalGameFrame.gamePanel.doMoveLeft();
                                    break;
                                case 'd':
                                    finalGameFrame.gamePanel.doMoveRight();
                                    break;
                            }
                            count++;

                            // Request UI update
                            finalGameFrame.gamePanel.requestFocusInWindow();

                            // Ensure UI updates
                            try {
                                Thread.sleep(50); // Small delay to allow UI to update
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                };

                worker.execute();
            }
        });

        layeredPane.add(backgroundLabel, 1);
        layeredPane.add(panel, 0);



        // 设置框架可见
        setVisible(true);


    }

}
