package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class RegisterFrame extends JFrame {


    private BufferedImage image;

    public RegisterFrame() {
        initJFrame();
        initview();

    }

    private void initJFrame() {
        this.setTitle("Register");
        this.setLayout(null);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);

    }

    private void initview() {

        JLabel usernameText = new JLabel("用户名");
        Font font1 = new Font("宋体", Font.BOLD, 22);
        usernameText.setFont(font1);
        usernameText.setLocation(180, 100);
        usernameText.setSize(100, 100);
        this.getContentPane().add(usernameText);

        JTextField username = new JTextField();
        username.setLocation(280, 138);
        username.setSize(250, 30);
        this.getContentPane().add(username);

        JLabel passwordText = new JLabel("密码");
        Font font2 = new Font("宋体", Font.BOLD, 22);
        passwordText.setFont(font2);
        passwordText.setLocation(180, 150);
        passwordText.setSize(100, 100);
        this.getContentPane().add(passwordText);


        JPasswordField password = new JPasswordField();
        password.setLocation(280, 185);
        password.setSize(250, 30);
        this.getContentPane().add(password);

        JLabel passwordTextag = new JLabel("确认密码");
        Font font3 = new Font("宋体", Font.BOLD, 22);
        passwordTextag.setFont(font3);
        passwordTextag.setLocation(180, 200);
        passwordTextag.setSize(100, 100);
        this.getContentPane().add(passwordTextag);


        JPasswordField passwordag = new JPasswordField();
        passwordag.setLocation(280, 235);
        passwordag.setSize(250, 30);
        this.getContentPane().add(passwordag);

        ImageIcon icon2 = new ImageIcon("src\\view\\icons8-registered-trademark-64.png");
        JButton registerButton = new JButton(icon2);
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        registerButton.setLocation(270, 270);
        registerButton.setSize(150, 150);
        this.getContentPane().add(registerButton);
        registerButton.addActionListener(e -> {
            String usernamestr = username.getText();
            String passwordstr1 = password.getText();
            String passwordstr2 = passwordag.getText();

            if (usernamestr.equals("")) {
                JOptionPane.showMessageDialog(this, "用户名不能为空", "用户名", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (passwordstr1.equals("")) {
                JOptionPane.showMessageDialog(this, "密码不能为空", "密码", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (passwordstr2.equals("")) {
                JOptionPane.showMessageDialog(this, "密码不能为空", "密码", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!checkpassword(passwordstr1, passwordstr2)) {
                JOptionPane.showMessageDialog(this, "两次输入的密码不一致", "密码不一致", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                writeString(usernamestr, passwordstr1);
                this.dispose();
                JOptionPane.showMessageDialog(this, "注册成功！", "注册", JOptionPane.INFORMATION_MESSAGE);
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }

        });


    }

    private boolean checkpassword(String passwordstr1, String passwordstr2) {
        if (passwordstr1.equals(passwordstr2)) {
            return true;
        }
        return false;
    }

    public void writeString(String usenamestr, String passwordstr) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\userinfo.txt", true));
            writer.write(usenamestr + "&" + passwordstr + "\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


