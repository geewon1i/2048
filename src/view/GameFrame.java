package view;

import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private String username;

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;

    private JLabel stepLabel;
    private GamePanel gamePanel;
    public GameFrame(int width, int height,String username) {
        this.username=username;
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8));
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel());
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50);
        JButton saveBtn = createButton("save", new Point(460, 220), 80, 50);
        this.loadBtn = createButton("Load", new Point(570, 220), 80, 50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 0), 180, 50);
        gamePanel.setStepLabel(stepLabel);

        JButton button1 = createButton("up", new Point(520, 290), 70, 70);
        JButton button2 = createButton("down", new Point(520, 370), 70, 70);
        JButton button3 = createButton("left", new Point(440, 370), 70, 70);
        JButton button4 = createButton("right", new Point(600, 370), 70, 70);

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
