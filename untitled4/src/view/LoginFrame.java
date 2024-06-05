package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginFrame extends JFrame {

    private Image image;

    public LoginFrame() {
        initJFrame();
        initview();
    }

    private void initview() {
        try {
            image= ImageIO.read(new File("src/view/1.png"));
        }catch (IOException e){
            e.getStackTrace();
        }
        setContentPane(new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
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

        ImageIcon iconl = new ImageIcon("src/view/icons8-user-48.png");
        JLabel usernameText = new JLabel(iconl);
        usernameText.setLocation(160, 90);
        usernameText.setSize(100, 100);
        this.getContentPane().add(usernameText);

        JTextField username = new JTextField();
        username.setLocation(260, 128);
        username.setSize(250, 35);
        this.getContentPane().add(username);

        ImageIcon iconp = new ImageIcon("src/view/icons8-password-50.png");
        JLabel passwordText = new JLabel(iconp);
        passwordText.setLocation(160, 160);
        passwordText.setSize(100, 100);
        this.getContentPane().add(passwordText);


        JPasswordField password = new JPasswordField();
        password.setLocation(260, 195);
        password.setSize(250, 35);
        this.getContentPane().add(password);

        //登录

        ImageIcon icon = new ImageIcon("src\\view\\icons8-login-64.png");  // 替换为实际图标路径
        JButton login = new JButton(icon);
        login.setLocation(200, 250);
        login.setSize(100, 100);
        login.setContentAreaFilled(false);
        login.setBorderPainted(false);


        this.getContentPane().add(login);
        login.addActionListener(e -> {
            System.out.println("login");
            String usernamestr = username.getText();
            String passwordstr = password.getText();
            login(usernamestr, passwordstr);
        });

        //注册
        ImageIcon icon2 = new ImageIcon("src\\view\\icons8-registered-trademark-64.png");  // 替换为实际图标路径
        JButton register = new JButton(icon2);
        register.setContentAreaFilled(false);
        register.setBorderPainted(false);
        register.setLocation(360, 250);
        register.setSize(100, 100);
        this.getContentPane().add(register);
        register.addActionListener(e -> {
            System.out.println("register");
            register();
        });

        ImageIcon icon3 = new ImageIcon("src\\view\\icons8-traveler-100.png");  // 替换为实际图标路径
        JButton guestLogin = new JButton(icon3);
        guestLogin.setContentAreaFilled(false);
        guestLogin.setBorderPainted(false);
        guestLogin.setLocation(430, 330);
        guestLogin.setSize(200, 100);
        guestLogin.setBorderPainted(false);
        this.getContentPane().add(guestLogin);
        guestLogin.addActionListener(e -> {
            this.dispose();
            GuestModeFrame gameFrame = new GuestModeFrame();
            gameFrame.setVisible(true);
        });

        ImageIcon icon4 = new ImageIcon("src\\view\\icons8-traveler-100.png");  // 替换为实际图标路径
        JButton AILogin = new JButton(icon4);
        AILogin.setContentAreaFilled(false);
        AILogin.setBorderPainted(false);
        AILogin.setLocation(430, 330);
        AILogin.setSize(200, 100);
        AILogin.setBorderPainted(false);
        this.getContentPane().add(AILogin);
        AILogin.addActionListener(e -> {
            this.dispose();
            Game2048 gameFrame = new Game2048();

        });

    }

    private void initJFrame() {
        this.setTitle("Login");
        this.setLayout(null);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
    }


    private void login(String usernamestr, String passwordstr) {
        File file = new File("src\\userinfo.txt");
        ArrayList<String> list = readString(file);
        System.out.println(list);
        if (usernamestr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入用户名", "用户名", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (passwordstr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入密码", "密码", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String[] strings = list.get(i).split("&");
            if (strings[0].equals(usernamestr) && strings[1].equals(passwordstr)) {
                this.dispose();
                UserModeFrame userModeFrame = new UserModeFrame(usernamestr);
                userModeFrame.setVisible(true);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "用户名或密码错误", "用户名或密码错误", JOptionPane.ERROR_MESSAGE);


    }

    private void register() {
        this.dispose();
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.setVisible(true);

    }

    public static ArrayList<String> readString(File file) {
        ArrayList<String> list = new ArrayList<>();
        try {
            // 构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            // 使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                list.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

