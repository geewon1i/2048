package view;

import model.GridNumber;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class GamePanel extends ListenerPanel implements Serializable {
    private static final long serialVersionUID = -567330989481496467L;
    private  int COUNT = 4;
    private GridComponent[][] grids;
    private int goal;
    private int score;
    private GridNumber model;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private int steps;
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
    public GamePanel(int size,int COUNT,int goal) {
        this.COUNT=COUNT;
        this.goal=goal;
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
public void setModel(GridNumber model){
        this.model=model;
}
    public GridNumber getModel() {
        return model;
    }

    public void initialGame() {
        this.steps = 0;
        this.score=0;
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
        boolean win=false;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j].setNumber(model.getNumber(i, j));
                if(grids[i][j].getNumber()>=goal)win=true;
            }
        }
        repaint();
        return win;
    }

public void updateSteps(){
    this.steps=0;
    this.stepLabel.setText(String.format("Step: %d", this.steps));
    this.scoreLabel.setText(String.format("Score: %d", this.score));
}
    /**
     * Implement the abstract method declared in ListenerPanel.
     * Do move right.
     */
    @Override
    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        score+=this.model.moveRight();
        this.afterMove();
        if(this.updateGridsNumber())System.exit(0);
    }
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        score+=this.model.moveLeft();
        this.afterMove();
        if(this.updateGridsNumber())System.exit(0);
    }
    public void doMoveUp() {
        System.out.println("Click VK_UP");
        score+=this.model.moveUp();
        this.afterMove();
        if(this.updateGridsNumber())System.exit(0);
    }
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        score+=this.model.moveDown();
        this.afterMove();
        if(this.updateGridsNumber())System.exit(0);
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
}
