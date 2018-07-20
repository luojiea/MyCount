package shixunup.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shixunup.till.ApplicationConfig;
import shixunup.till.GetScreenCenterLocation;
import shixunup.till.ReadeImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{
    private JButton btnlog;
    private JButton btncancel;
    private JTextField textUasername;
    private JTextField password;
    private JLabel lableUsername;
    private JLabel lablePassword;
    JPanel panel = new JPanel();
    JPanel toppanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            //绘制背景
            Image bg = ReadeImage.readImage("src/main/java/shixunup/image/cat.jpg");
            g.drawImage(bg,-10, -10,210,200, null);
        }
    };
    public LoginFrame() {
        // TODO Auto-generated constructor stub

        this.lableUsername = new JLabel("用户名");
        lableUsername.setForeground(Color.white);
        this.lablePassword = new JLabel("密    码");
        lablePassword.setForeground(Color.white);
        this.textUasername = new JTextField(12);
        this.password = new JPasswordField(12);
        this.btnlog = new JButton("登陆");
        this.btncancel = new JButton("取消");
        this.setTitle("用户登陆界面");


//        this.setLayout(new CardLayout(0,35));

        lableUsername.setOpaque(false);
        lablePassword.setOpaque(false);
        panel.setOpaque(false);panel.add(this.lableUsername);
        panel.setLayout(new FlowLayout());
        panel.add(this.textUasername);
        panel.add(this.lablePassword);
        panel.add(this.password);
        panel.add(this.btnlog);
        panel.add(this.btncancel);
        toppanel.setLayout(new CardLayout(0,35));
        toppanel.add(panel);
        this.add(toppanel);


        this.btnlog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(textUasername.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "请输入帐户名！", "错误",JOptionPane.ERROR_MESSAGE);
                    lableUsername.setForeground(Color.RED);
                    return;
                }
                if(password.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "请输入密码！", "错误",JOptionPane.ERROR_MESSAGE);
                    lablePassword.setForeground(Color.RED);
                    return;
                }
                // TODO Auto-generated method stub
                if (textUasername.getText().equals(ApplicationConfig.getUserName())&&
                        password.getText().equals(ApplicationConfig.getUserPassword())) {
                    JOptionPane.showMessageDialog( null,
                            "登陆成功！^_^",null,JOptionPane.DEFAULT_OPTION);
                    LoginFrame.this.setVisible(false);
                    new OperateJFrame();
                }else {
                    JOptionPane.showMessageDialog( null,
                            "账号或密码错误！","警告",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.btncancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        this.getRootPane().setDefaultButton(btnlog);
        this.setVisible(true);
        this.setLocation(GetScreenCenterLocation.getLocation(210, 200)[0],
                GetScreenCenterLocation.getLocation(210, 200)[1]);
        this.setSize(210, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
