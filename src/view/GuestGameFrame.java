package view;

import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GuestGameFrame extends JFrame implements Serializable {
    private static final long serialVersionUID = 6113042624164284648L;
    transient private String username;
    transient private GameController controller;
    transient private JButton restartBtn;
    transient private JButton loadBtn;

    transient private JLabel stepLabel;
    transient private GamePanel gamePanel;

    public GuestGameFrame(int width, int height) {
        this.username = username;
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8));
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel());
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50);

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

