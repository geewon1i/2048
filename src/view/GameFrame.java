package view;

import controller.GameController;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame {
    private BufferedImage image;
    private String username;

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;

    private JLabel stepLabel;
    private GamePanel gamePanel;

    public GameFrame(int width, int height, String username) {

        this.username = username;
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);

//        try {
//            image= ImageIO.read(new File("src\\view\\triangle.png"));
//        }catch (IOException e){
//            e.getStackTrace();
//        }
//        setContentPane(new JPanel(){
//            @Override
//            protected void paintComponent(Graphics g){
//                super.paintComponent(g);
//                if (image != null) {
//                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//                } else {
//                    // Handle the case when image is null
//                    g.setColor(Color.RED);
//                    g.fillRect(0, 0, getWidth(), getHeight());
//                }
//            }
//        });

        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8));
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel());

        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50);
        restartBtn.setBorderPainted(false);

        JButton saveBtn = createButton("save", new Point(460, 220), 80, 50);
        saveBtn.setBorderPainted(false);

        this.loadBtn = createButton("Load", new Point(570, 220), 80, 50);
        loadBtn.setBorderPainted(false);

        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 0), 180, 50);
        gamePanel.setStepLabel(stepLabel);

        ImageIcon icon = new ImageIcon("src/view/icons8-up-arrow-70.png");
        JButton button1 = new JButton(icon);
        button1.setLocation(520, 290);
        button1.setSize(70, 70);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);

        ImageIcon icon2 = new ImageIcon("src\\view\\icons8-down-70.png");
        JButton button2 = new JButton(icon2);
        button2.setLocation(520, 370);
        button2.setSize(70, 70);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);

        ImageIcon icon3 = new ImageIcon("src\\view\\icons8-left-arrow-70.png");
        JButton button3 = new JButton(icon3);
        button3.setLocation(440, 370);
        button3.setSize(70, 70);
        button3.setContentAreaFilled(false);
        button3.setBorderPainted(false);

        ImageIcon icon4 = new ImageIcon("src/view/icons8-right-70.png");
        JButton button4 = new JButton(icon4);
        button4.setLocation(600, 370);
        button4.setSize(70, 70);
        button4.setContentAreaFilled(false);
        button4.setBorderPainted(false);
        add(button1);
        add(button2);
        add(button3);
        add(button4);

        button1.addActionListener(e -> {
            gamePanel.doMoveUp();
        });
        button2.addActionListener(e -> {
            gamePanel.doMoveDown();
        });
        button3.addActionListener(e -> {
            gamePanel.doMoveLeft();
        });
        button4.addActionListener(e -> {
            gamePanel.doMoveRight();
        });

        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });


        //todo: add other button here
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        button.setBackground(new Color(135,206,250));
//        button.setMargin(new Insets(0,0,0,0));//去除文字与按钮的边沿
//        button.setBorder(new RoundBorder());//圆角矩形边界
//        button.setContentAreaFilled(false);//取消原先画矩形的设置
//        //setBorderPainted(false);//会导致按钮没有明显边界
//        button.setFocusPainted(false);//去除文字周围的虚线框

        button.setForeground(new Color(127,255,170));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false); // 去除焦点框
        this.add(button);
        return button;
    }

    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }

}