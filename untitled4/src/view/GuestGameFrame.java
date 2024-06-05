package view;

import controller.GameController;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

public class GuestGameFrame extends JFrame implements Serializable {
    private static final long serialVersionUID = 6113042624164284648L;
    private Image image;
    transient private String username;
    transient private GameController controller;
    transient private JButton restartBtn;
    transient private JButton loadBtn;

    transient private JLabel stepLabel;
    transient private JLabel scoreLabel;
    transient private JLabel timeLabel;
    transient private GamePanel gamePanel;

    private TimerTask task;
    private Timer timer;
    private int time;
    private Clip clip;

    public GuestGameFrame(int width, int height,int game_type,int goal,int COUNT,int limitTime) {
        timer=new Timer();
        task= new TimerTask() {
            @Override
            public void run() {
                time++;
                if (game_type == 1) {
                    System.out.println(limitTime);
                    if (limitTime - time > 0)
                        timeLabel.setText(String.format("Last time: %d",limitTime - time));
                    else {
                        JOptionPane.showMessageDialog(null, "游戏结束", "2048", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                } else {
                    timeLabel.setText(String.format("Time: %d", time));
                }
            }
        };
        this.username = username;
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);


        try {
            image = ImageIO.read(new File("src/view/DCS_COM_e019_lp.gif"));
        } catch (IOException e) {
            e.getStackTrace();
        }
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // Handle the case when image is null
                    g.setColor(Color.RED);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        });
        this.setLayout(null);

        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8),COUNT,goal);
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel());

        ImageIcon icon = new ImageIcon("src/view/icons8-restart-70.png");
        JButton button = new JButton(icon);
        button.setLocation(new Point(520, 150));
        button.setSize(70, 70);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false); // 去除焦点框
        this.add(button);
        this.restartBtn = button;


        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 0), 180, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC, 22), new Point(480, 40), 180, 50);
        if(game_type==1)
            this.timeLabel = createLabel("LastTime", new Font("serif", Font.ITALIC, 22), new Point(480, 80), 180, 50);
        else {
            this.timeLabel = createLabel("Time", new Font("serif", Font.ITALIC, 22), new Point(480, 80), 180, 50);
            time=limitTime;
        }
        timer.scheduleAtFixedRate(task,1,1000);
        gamePanel.setTimeLabel(timeLabel);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setScoreLabel(scoreLabel);

        ImageIcon icon1 = new ImageIcon("src/view/icons8-up-arrow-70.png");
        JButton button1 = new JButton(icon1);
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
            playSound("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\合并_merge__爱给网_aigei_com.wav");
            gamePanel.doMoveUp();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        button2.addActionListener(e -> {
            playSound("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\合并_merge__爱给网_aigei_com.wav");
            gamePanel.doMoveDown();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        button3.addActionListener(e -> {
            playSound("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\合并_merge__爱给网_aigei_com.wav");
            gamePanel.doMoveLeft();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        button4.addActionListener(e -> {
            playSound("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\合并_merge__爱给网_aigei_com.wav");
            gamePanel.doMoveRight();
            gamePanel.requestFocusInWindow();//enable key listener
        });



        this.restartBtn.addActionListener(e -> {
            if(game_type==1)timeLabel.setText(String.format("Last time: %d",limitTime - (time=0)));
            else timeLabel.setText(String.format("Time: %d",time=0));
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });



        playBackgroundMusic("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\钢琴曲-奥奇传说（纯音乐）.wav");
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
    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            if (soundFile.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("找不到音效文件: " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    private void playBackgroundMusic(String filePath) {
        try {
            // 打开音频输入流
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // 循环播放
            } else {
                System.out.println("找不到音频文件: " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

}
