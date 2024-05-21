package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserModeFrame extends JFrame {
    public String username;
    public UserModeFrame(){
        initJFrame();

    }
    public UserModeFrame(String username){
        this.username=username;
        initJFrame();

    }

    private void initJFrame() {
        this.setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        JButton newGameButton = new JButton("新游戏");
        JButton continueGameButton = new JButton("继续游戏");

        panel.add(newGameButton);
        panel.add(continueGameButton);


        newGameButton.addActionListener(e -> {
            this.dispose();
            ModeSelectionFrame modeSelectionFrame = new ModeSelectionFrame(username);
            modeSelectionFrame.setVisible(true);
        });

        continueGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 这里可以添加继续游戏的逻辑
            }
        });

        // 添加面板到框架
        add(panel);

        // 设置框架可见
        setVisible(true);
    }
public String getUsername(){
        return username;
}


}
