package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestModeFrame extends JFrame{
    public GuestModeFrame(){
        initJFrame();

    }

    private void initJFrame() {
        this.setTitle("Game Menus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 10, 10));

        JButton newGameButton = new JButton("新游戏");
        panel.add(newGameButton);

        newGameButton.addActionListener(e -> {
            this.dispose();
            GuestModeSelectionFrame modeSelectionFrame = new GuestModeSelectionFrame();
            modeSelectionFrame.setVisible(true);
        });

        add(panel);
        setVisible(true);
    }
}
