package shixunup.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shixunup.till.ApplicationConfig;
import shixunup.till.CreatTableInDatabase;
import shixunup.till.GetScreenCenterLocation;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ConfigJFrame extends JFrame implements ActionListener {
    //注册组件
    //顶部组件
    JLabel topLable = new JLabel("应用配置",JButton.HORIZONTAL);
    //中部组件
    JPanel centerPane = new JPanel();
    //第一个选择数据储存方式
    JPanel chooseDataStylePane = new JPanel();
    JPanel topPane = new JPanel();
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton rBtndatabase = new JRadioButton("用数据库来储存数据");
    JRadioButton rBtnFile = new JRadioButton("用文件储存数据");
    //若选择数据库，选择数据库的组件，注册一个账号，提示密码不可再更改
    JPanel databasePane = new JPanel();
    JPanel dbUrlPane = new JPanel();
    JPanel dbUserPane = new JPanel();
    JPanel dbPasswordPane = new JPanel();
    JLabel lableUrl = new JLabel("数据库 URL:");
    JLabel lableDbUser = new JLabel("数据库账号:");
    JLabel lableDbPassword = new JLabel("数据库密码:");
    JTextField txtUrl = new JTextField(20);
    JTextField txtDbuser = new JTextField(20);
    JTextField txtDbPassword = new JTextField(20);

//    JPanel registerOutPane = new JPanel();
    JPanel registerPane = new JPanel();
    JPanel userNmaePane = new JPanel();
    JPanel passwordPane = new JPanel();
    JPanel configPane = new JPanel();
    JLabel lableUser = new JLabel("应用登陆账号:");
    JLabel lablePassword = new JLabel("应用登陆密码:");
    JLabel lableConfigPassword = new JLabel("确认登陆密码:");
    JTextField txtUser = new JTextField(20);
    JTextField txtPassword = new JPasswordField(20);
    JTextField txtConfigPassword = new JPasswordField(20);

    //底部按钮组件
    JPanel bottomPane = new JPanel();
    JButton btnpreviousStep = new JButton("Previous Step");
    JButton btnNext = new JButton("Next");


    String command;
    int whichPane = 1;


    public ConfigJFrame(){
        topPane.add(topLable);
        this.add(topPane,BorderLayout.NORTH);

        rBtndatabase.addActionListener(this);
        rBtnFile.addActionListener(this);
        buttonGroup.add(rBtnFile);
        buttonGroup.add(rBtndatabase);
        chooseDataStylePane.setLayout(new FlowLayout(FlowLayout.LEFT,130,20));
        chooseDataStylePane.add(rBtnFile);
        chooseDataStylePane.add(rBtndatabase);
        chooseDataStylePane.setVisible(false);
        centerPane.setLayout(new CardLayout(0,30));
        centerPane.add(chooseDataStylePane,"选择储存数据面板");

        dbUrlPane.add(lableUrl);
        dbUrlPane.add(txtUrl);
        dbUserPane.add(lableDbUser);
        dbUserPane.add(txtDbuser);
        dbPasswordPane.add(lableDbPassword);
        dbPasswordPane.add(txtDbPassword);
        databasePane.setLayout(new GridLayout(3,1));
        databasePane.add(dbUrlPane);
        databasePane.add(dbUserPane);
        databasePane.add(dbPasswordPane);
        centerPane.add(databasePane,"数据库注册面板");

        userNmaePane.add(lableUser);
        userNmaePane.add(txtUser);
        passwordPane.add(lablePassword);
        passwordPane.add(txtPassword);
        configPane.add(lableConfigPassword);
        configPane.add(txtConfigPassword);
        registerPane.setLayout(new GridLayout(3,1));
        registerPane.add(userNmaePane);
        registerPane.add(passwordPane);
        registerPane.add(configPane);
        centerPane.add(registerPane,"注册面板");
//        CardLayout cardLayout = (CardLayout) centerPane.getLayout();
//        cardLayout.show(centerPane,"注册面板");
        this.add(centerPane);

        btnpreviousStep.setVisible(false);
        bottomPane.add(btnpreviousStep);
        bottomPane.add(btnNext);
        this.add(bottomPane,BorderLayout.SOUTH);

        CardLayout cardLayout = (CardLayout) centerPane.getLayout();


        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (whichPane == 1) {
                    if ("用数据库来储存数据".equals(command)) {
                        cardLayout.show(centerPane, "数据库注册面板");
                        btnpreviousStep.setVisible(true);
                        whichPane = 2;
                    } else if ("用文件储存数据".equals(command)) {
                        cardLayout.show(centerPane, "注册面板");
                        btnpreviousStep.setVisible(true);
                        btnNext.setText("Finish");
                        whichPane = 3;
                    } else {
                        JOptionPane.showMessageDialog(ConfigJFrame.this, "未选择储存方式");
                    }
                } else if (whichPane == 2) {
                    if (txtUrl==null || txtDbuser == null){
                        JOptionPane.showMessageDialog(ConfigJFrame.this,
                                "URL和数据库账不能为空！","警告",JOptionPane.ERROR);
                    } else {
                        cardLayout.show(centerPane, "注册面板");
                        btnNext.setText("Finish");
                        whichPane = 3;
                    }
                } else {
                    if ("".equals(txtUser.getText().trim()) || "".equals(txtPassword.getText().trim())){
                        JOptionPane.showMessageDialog(ConfigJFrame.this,
                                "URL和数据库账不能为空！","警告",JOptionPane.ERROR_MESSAGE);
                    } else if ("".equals(txtConfigPassword.getText().trim())){
                        JOptionPane.showMessageDialog(ConfigJFrame.this,
                                "请输入确认密码！","警告",JOptionPane.ERROR_MESSAGE);
                    } else if (!txtPassword.getText().equals(txtConfigPassword.getText())){
                        JOptionPane.showMessageDialog(ConfigJFrame.this,
                                "两次密码不一致！","警告",JOptionPane.ERROR_MESSAGE);
                    } else if(0 == JOptionPane.showConfirmDialog(ConfigJFrame.this,
                            "确定之前的配置信息都正确么？","",JOptionPane.YES_NO_OPTION)){
                        ApplicationConfig applicationConfig = new ApplicationConfig();
                        applicationConfig.setUserName(txtUser.getText());
                        applicationConfig.setUserPassword(txtPassword.getText());
                        if ("用数据库来储存数据".equals(command)) {
                            applicationConfig.setDbURL(txtUrl.getText());
                            applicationConfig.setDbUser(txtDbuser.getText());
                            applicationConfig.setDbPassword(txtDbPassword.getText());
                            applicationConfig.setDaoA("shixunup.dao.AccountDAO");
                        } else if ("用文件储存数据".equals(command)) {
                            applicationConfig.setDaoA("shixunup.dao.AccountFileDAO");
                        }
                        applicationConfig.setFirstRun("false");
                        applicationConfig.WriteAppConfig();
                        new CreatTableInDatabase().creatTable();
                        new LoginFrame();

                    }
                }
            }
        });
        btnpreviousStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("用数据库来储存数据".equals(command) && whichPane == 3){
                    cardLayout.show(centerPane, "数据库注册面板");
                    whichPane = 2;
                    btnNext.setText("Next");
                } else if( whichPane == 2){
                    cardLayout.show(centerPane,"选择储存数据面板");
                    btnNext.setText("Next");
                    whichPane = 1;
                    btnpreviousStep.setVisible(false);
                }else {
                    cardLayout.show(centerPane,"选择储存数据面板");
                    btnNext.setText("Next");
                    whichPane = 1;
                    btnpreviousStep.setVisible(false);
                }
            }
        });
        this.setLocation(GetScreenCenterLocation.getLocation(400,300)[0],
                GetScreenCenterLocation.getLocation(400,300)[1]);
        this.setSize(400,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        command = e.getActionCommand();
    }
}
