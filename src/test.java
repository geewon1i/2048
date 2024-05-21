import view.GameFrame;
import view.LoginFrame;
import view.ModeSelectionFrame;
import view.UserModeFrame;

import javax.swing.*;

public class test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame(700, 500,"123");
            gameFrame.setVisible(true);
        });
    }
}
