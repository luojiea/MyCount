package main.java.com.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFrame extends JFrame {
    public AddFrame(){
        //设置窗体名称
        this.setTitle("添加账号信息界面");
        //创建三个部分的面板
        JPanel panelNorth = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelSouth = new JPanel();
        //创建和添加顶部面板的内容，一个搜索文本框和触发按钮
        JTextField fieldSearch = new JTextField();
        JButton btnSearch = new JButton("Search");
        panelNorth.setLayout(new BorderLayout());
        //添加到面板中
        panelNorth.add(fieldSearch,BorderLayout.CENTER);
        panelNorth.add(btnSearch,BorderLayout.EAST);
        //添加面板并设置位置
        this.add(panelNorth,BorderLayout.PAGE_START);

        //创建一种网格布局
        GridLayout gridLayout = new GridLayout(4,2);
        //设置组件垂直间隙
        gridLayout.setVgap(3);
        //应用此布局
        panelCenter.setLayout(gridLayout);
        //创建四个标签
        JLabel labelTitle = new JLabel("title:",SwingConstants.CENTER);
        JLabel labelAccount = new JLabel("account:",SwingConstants.CENTER);
        JLabel labelPassword = new JLabel("password:",SwingConstants.CENTER);
        JLabel labelRemark = new JLabel("remark:",SwingConstants.CENTER);
        //创建四个文本域
        JTextArea areaTitle = new JTextArea();
        JTextArea areaAccount = new JTextArea();
        JTextArea areaPassword = new JTextArea();
        JTextArea areaRemark= new JTextArea();
        //按顺序将各个组件添加到面板上
        panelCenter.add(labelTitle);
        panelCenter.add(areaTitle);
        panelCenter.add(labelAccount);
        panelCenter.add(areaAccount);
        panelCenter.add(labelPassword);
        panelCenter.add(areaPassword);
        panelCenter.add(labelRemark);
        panelCenter.add(areaRemark);
        //添加中间的面板，设置位置
        this.add(panelCenter,BorderLayout.CENTER);
        //创建第三个面板中的组件
        JButton btnConfirm = new JButton("Confirm");
        JButton btnCancel = new JButton("Cancel");
        //为两个按钮注册事件监听，这里直接用的
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //按顺序判断各个文本域是否为空
                if(areaTitle.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Title could not be null!");
                    areaTitle.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red,
                            1)));
                }else if(areaAccount.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Account could not be null!");
                    areaAccount.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red,
                            1)));
                }else if(areaPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Password could not be null!");
                    areaPassword.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red,
                            1)));
                }else {
                    String massage = "Title:" + areaTitle.getText() + "\nAccount:" + areaAccount.getText() +
                            "\nPassword:" + areaPassword.getText() + "\nRemark:" + areaRemark.getText();
                    JOptionPane.showMessageDialog(null, massage);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //确认是否取消
                if(JOptionPane.showConfirmDialog(null,"确认取消？",null,
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        //添加到组件到底部的面包那中
        panelSouth.add(btnConfirm);
        panelSouth.add(btnCancel);
        this.add(panelSouth,BorderLayout.PAGE_END);
        //显示面板，设置初始位置和大小，且不可改变窗口大小
        this.setVisible(true);
        this.setBounds(500,200,800,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        AddFrame addFrame = new AddFrame();
    }
}
