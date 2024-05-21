package controller;

import model.GridNumber;
import view.GamePanel;

import java.io.Serializable;


/**
 * This class is used for interactive with JButton in GameFrame.
 */
public class GameController implements Serializable {
    private static final long serialVersionUID = 6113042624164284648L;
    private GamePanel view;
    private GridNumber model;


    public GameController(GamePanel view, GridNumber model) {
        this.view = view;
        this.model = model;

    }
    public void restartGame() {
        System.out.println("Do restart game here");
        model.initialNumbers();
        view.updateGridsNumber();
        view.updateSteps();
    }

    //todo: add other methods such as loadGame, saveGame...

}
