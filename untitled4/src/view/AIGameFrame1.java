package view;

import controller.GameController;
import model.GridNumber;
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

public class AIGameFrame1 extends JFrame implements Serializable {
    private Image image;
    private String username;

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;

    private int game_type;
    transient private JLabel stepLabel;
    transient private JLabel scoreLabel;
    transient private JLabel timeLabel;
    public GamePanel gamePanel;
    private TimerTask task;
    private Timer timer;
    private int time;
    int limitTime;
    private Clip clip;
    AI ai;
    public AIGameFrame1(int width, int height,int game_type,int goal,int COUNT,int limitTime) throws InterruptedException {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                time++;
                if (game_type == 1) {
                    System.out.println(limitTime);
                    if (limitTime - time > 0)
                        timeLabel.setText(String.format("Last time: %d", limitTime - time));
                    else{
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
        this.game_type = game_type;

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
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8), COUNT, goal);
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


        if (game_type == 0) {
            ImageIcon icon12 = new ImageIcon("src/view/icons8-save-60.png");
            JButton button12 = new JButton(icon12);
            button12.setLocation(new Point(470, 220));
            button12.setSize(65, 65);
            button12.setContentAreaFilled(false);
            button12.setBorderPainted(false);
            JButton saveBtn = button12;
            button12.setFocusPainted(false); // 去除焦点框1
            this.add(button12);

            saveBtn.addActionListener(e -> {
                File file = new File("src\\" + username + ".txt");

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\" + username + ".txt"));
                    gamePanel.setTime(time);
                    oos.writeObject(this.gamePanel);
                    oos.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                gamePanel.requestFocusInWindow();//enable key listener
            });
        }

        if (game_type == 0) {
            ImageIcon icon123 = new ImageIcon("src/view/icons8-load-60.png");
            JButton button123 = new JButton(icon123);
            button123.setLocation(new Point(570, 220));
            button123.setSize(65, 65);
            button123.setContentAreaFilled(false);
            button123.setBorderPainted(false);
            button123.setFocusPainted(false); // 去除焦点框
            this.add(button123);
            this.loadBtn = button123;
            this.loadBtn.addActionListener(e -> {
                String string = JOptionPane.showInputDialog(this, "Input path:");
                System.out.println(string);
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream(string));
                    GamePanel gamePanel1 = (GamePanel) ois.readObject();

                    ois.close();
                    clip.close();
                    GameFrame gameFrame = new GameFrame(700, 500, username, gamePanel1, 0, 2048, 4, gamePanel1.getTime());

                    gameFrame.setVisible(true);
                    this.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "游戏内容加载失败！", "error", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


            });
        }


        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 0), 180, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC, 22), new Point(480, 40), 180, 50);
        if (game_type == 1)
            this.timeLabel = createLabel("LastTime", new Font("serif", Font.ITALIC, 22), new Point(480, 80), 180, 50);
        else {
            this.timeLabel = createLabel("Time", new Font("serif", Font.ITALIC, 22), new Point(480, 80), 180, 50);
            time = limitTime;
        }
        timer.scheduleAtFixedRate(task, 1, 1000);
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
            if (game_type == 1) timeLabel.setText(String.format("Last time: %d", limitTime - (time = 0)));
            else timeLabel.setText(String.format("Time: %d", time = 0));
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });

        playBackgroundMusic("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\钢琴曲-奥奇传说（纯音乐）.wav");
        //todo: add other button here

//        int count=1;
//        while (count<=1000){
//            ai=new AI(gamePanel.getModel());
//            char op= ai.findBestMove();
//            switch (op){
//                case 'w':gamePanel.doMoveUp();break;
//                case 's':gamePanel.doMoveDown();break;
//                case 'a':gamePanel.doMoveLeft();break;
//                case 'd':gamePanel.doMoveRight();break;
//            }
//            count++;
//            gamePanel.requestFocusInWindow();
//        }
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
    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
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
}