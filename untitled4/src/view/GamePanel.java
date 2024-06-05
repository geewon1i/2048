package view;

import model.GridNumber;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GamePanel extends ListenerPanel implements Serializable {
    private static final long serialVersionUID = -567330989481496467L;
    private int COUNT = 4;
    private GridComponent[][] grids;
    private int goal;
    private int score;
    private GridNumber model;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private int steps;
    private int time;
    private final int GRID_SIZE;

    public GamePanel(int size) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(size, size);
        this.GRID_SIZE = size / COUNT;
        this.grids = new GridComponent[COUNT][COUNT];
        this.model = new GridNumber(COUNT, COUNT);
        initialGame();

    }

    public GamePanel(int size, int COUNT, int goal) {
        this.COUNT = COUNT;
        this.goal = goal;
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(size, size);
        this.GRID_SIZE = size / COUNT;
        this.grids = new GridComponent[COUNT][COUNT];
        this.model = new GridNumber(COUNT, COUNT);
        initialGame();
    }

    public void setModel(GridNumber model) {
        this.model = model;
    }

    public GridNumber getModel() {
        return model;
    }

    public void initialGame() {
        this.steps = 0;
        this.score = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new GridComponent(i, j, model.getNumber(i, j), this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE, i * GRID_SIZE);
                this.add(grids[i][j]);
            }
        }
        model.printNumber();//check the 4*4 numbers in game
        this.repaint();
    }

    public boolean updateGridsNumber() {
        boolean win = false;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j].setNumber(model.getNumber(i, j));
                if (grids[i][j].getNumber() >= goal) win = true;
            }
        }
        repaint();
        return win;
    }

    public void updateSteps() {
        this.steps = 0;
        this.score = 0;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
        this.scoreLabel.setText(String.format("Score: %d", this.score));
    }

    /**
     * Implement the abstract method declared in ListenerPanel.
     * Do move right.
     */
    @Override
    public void doMoveRight() {
        playSound("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\合并_merge__爱给网_aigei_com.wav");
        System.out.println("Click VK_RIGHT");
        score += this.model.moveRight();
        this.afterMove();
        if (this.updateGridsNumber()) {
            JOptionPane.showMessageDialog(null, "游戏成功！", "2048", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void doMoveLeft() {
        playSound("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\合并_merge__爱给网_aigei_com.wav");
        System.out.println("Click VK_LEFT");
        score += this.model.moveLeft();
        this.afterMove();
        if (this.updateGridsNumber()) {
            JOptionPane.showMessageDialog(null, "游戏成功！", "2048", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void doMoveUp() {
        playSound("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\合并_merge__爱给网_aigei_com.wav");
        System.out.println("Click VK_UP");
        score += this.model.moveUp();
        this.afterMove();
        if (this.updateGridsNumber()) {
            JOptionPane.showMessageDialog(null, "游戏成功！", "2048", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void doMoveDown() {
        playSound("C:\\Users\\94875\\IdeaProjects\\untitled4\\src\\view\\合并_merge__爱给网_aigei_com.wav");
        System.out.println("Click VK_DOWN");
        score += this.model.moveDown();
        this.afterMove();
        if (this.updateGridsNumber()) {
            JOptionPane.showMessageDialog(null, "游戏成功！", "2048", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void afterMove() {


        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
        this.scoreLabel.setText(String.format("Score: %d", this.score));
    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public JLabel getStepLabel() {
        return stepLabel;
    }

    public int getSteps() {
        return steps;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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