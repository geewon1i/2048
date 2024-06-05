package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UserModeFrame extends JFrame {
    public String username;
    private BufferedImage image;

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
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        ImageIcon icon = new ImageIcon("src\\view\\icons8-game-controller-100.png");  // 替换为实际图标路径
        JButton newGameButton = new JButton(icon);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
        newGameButton.setFocusPainted(false);
        ImageIcon icon1 = new ImageIcon("src\\view\\icons8-next-page-100.png");  // 替换为实际图标路径
        JButton continueGameButton = new JButton(icon1);
        continueGameButton.setContentAreaFilled(false);
        continueGameButton.setBorderPainted(false);
        continueGameButton.setFocusPainted(false);

        panel.add(newGameButton);
        panel.add(continueGameButton);



        newGameButton.addActionListener(e -> {
            this.dispose();
            ModeSelectionFrame modeSelectionFrame = new ModeSelectionFrame(username);
            modeSelectionFrame.setVisible(true);
        });

        continueGameButton.addActionListener(e -> {

                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream("src\\"+username+".txt"));
                    GamePanel gamePanel1 = (GamePanel) ois.readObject();
                    ois.close();
                    GameFrame gameFrame = new GameFrame(700, 500, username, gamePanel1,0,2048,4,gamePanel1.getTime());
                    gameFrame.setVisible(true);
                    this.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "游戏内容加载失败！", "error", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                // 这里可以添加继续游戏的逻辑

    });

        // 添加面板到框架
        this.getContentPane().add(panel);

        // 设置框架可见
        setVisible(true);
    }
public String getUsername(){
        return username;
}


}
