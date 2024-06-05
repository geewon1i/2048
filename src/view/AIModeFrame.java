package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AIModeFrame extends JFrame{
    private BufferedImage image;

    public AIModeFrame(){
        initJFrame();

    }

    private void initJFrame() {
        this.setTitle("Game Menus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        try {
            image= ImageIO.read(new File("view\\triangle.png"));
        }catch (IOException e){
            e.getStackTrace();
        }
        setContentPane(new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 10, 10));
        ImageIcon icon = new ImageIcon("view\\icons8-game-controller-100.png");  // 替换为实际图标路径
        JButton newGameButton = new JButton(icon);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
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
