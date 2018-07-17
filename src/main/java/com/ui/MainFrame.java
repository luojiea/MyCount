package com.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @author
 */
public class MainFrame extends JFrame {
    /**
     * ====注册最顶部的搜索组件组件=====
     * panelNorth
     */
    private  JPanel panelNorth = new JPanel();
    /**
     * ====注册中间的面板及其内部的面板====
     * panelCenter
     */
    private  JPanel panelCenter = new JPanel();
    /**
     * 注册属于中间面板的子面板
     * 1.添加项目的面板 panelCenterAdd
     * 2.显示已添加项目的面板 panelCenterTable
     */
    //1.添加项目的面板
    private  JPanel panelCenterAdd = new JPanel();
    //2.显示已添加项目的面板
    private  JPanel panelCenterTable = new JPanel();
    /**
     * ====注册底部操作按钮的面板，包含两个面板====
     * panelSouth
     */
    private  JPanel panelSouth = new JPanel();
    /**
     * 注册属于底部面板的子面板
     * 1.添加界面的按钮面板panelSouthAdd
     * 2.主界面的按钮面板paneSouthMain
     */
    //1.添加界面的按钮面板panelSouthAdd
    private JPanel panelSouthAdd = new JPanel();
    //2.主界面的按钮面板paneSouthMain
    private JPanel paneSouthMain = new JPanel();
    /**
     * ====注册所有组件=====
     * 1.顶部所有组件
     * 2.中部所有组件
     * 3.底部所有组件
     */

    /**
     * ----顶部所有组件----
     * 1.搜索文本框
     * 2.搜索按钮
     */
    //1.搜索文本框
    private  TextField txtSearch = new TextField();
    //2.搜索按钮
    private  JButton btnSearch = new JButton("Search");

    /**
     * ----中部所有组件----
     * 1.添加项目面板内的所有组件
     * 2.显示信息的面板的所有组件
     */
    //1.添加项目面板内的所有组件
    //创建四个标签
    private JLabel labelTitle = new JLabel("title:",SwingConstants.CENTER);
    private JLabel labelAccount = new JLabel("account:",SwingConstants.CENTER);
    private JLabel labelPassword = new JLabel("password:",SwingConstants.CENTER);
    private JLabel labelRemark = new JLabel("remark:",SwingConstants.CENTER);
    //创建四个文本域
    private JTextArea areaTitle = new JTextArea();
    private JTextArea areaAccount = new JTextArea();
    private JTextArea areaPassword = new JTextArea();
    private JTextArea areaRemark= new JTextArea();
    //2.显示信息的面板组件
    JLabel labelCenterMassage = new JLabel("这里是要放一个表格的");

    /**
     * ----底部所有组件----
     * 1.添加界面的按钮面板的所有组件
     * 2.主界面的按钮面板的所有组件
     */
    //1.添加界面的按钮面板的所有组件
    private JButton btnConfirm = new JButton("Confirm");
    private JButton btnCancel = new JButton("Cancel");
    //2.主界面的按钮面板的所有组件
    private  JButton btnAdd = new JButton("Add");
    private  JButton btnDelete = new JButton("Delete");
    private  JButton btnModify = new JButton("Modify");

    //注册一两个个卡片布局方式
    CardLayout layoutCenter,layoutSouth;

    /**
     * 组件注册结束
     */
    //构造方法
    /**
     * 实现窗口初始化和功能
     */
    public MainFrame(){

        //====设置名称====
        this.setTitle("This is MainFrame.....");

        //====添加顶部的面板内所有组件，并设置布局方式,此处的为上下部分的组件====
        panelNorth.setLayout(new BorderLayout());
        panelNorth.add(txtSearch);
        panelNorth.add(btnSearch,BorderLayout.EAST);

        //====添加中部的面板内所有组件，并设置布局方式===
        panelCenter.setLayout(new CardLayout());
        panelCenter.add(panelCenterTable,"表格信息");
        panelCenter.add(panelCenterAdd,"添加界面");

        //1.添加添加项目面板的按钮
        panelCenterAdd.setLayout(new GridLayout(4,2,0,3));
        //按顺序将各个组件添加到面板上
        panelCenterAdd.add(labelTitle);
        panelCenterAdd.add(areaTitle);
        panelCenterAdd.add(labelAccount);
        panelCenterAdd.add(areaAccount);
        panelCenterAdd.add(labelPassword);
        panelCenterAdd.add(areaPassword);
        panelCenterAdd.add(labelRemark);
        panelCenterAdd.add(areaRemark);
        //2.添加信息表格面板组件
        panelCenterTable.setLayout(new BorderLayout());
        panelCenterTable.add(JTableTest.getJScrollPane());

        //====添加底部的面板内所有组件，并设置布局方式===
        panelSouth.setLayout(new CardLayout());
        panelSouth.add(paneSouthMain,"主界面按钮");
        panelSouth.add(panelSouthAdd,"添加界面按钮");
        //1.添加添加界面的按钮面板的所有组件
        panelSouthAdd.add(btnConfirm);
        panelSouthAdd.add(btnCancel);
        //2.添加主界面的按钮面板的所有组件
        paneSouthMain.add(btnAdd);
        paneSouthMain.add(btnDelete);
        paneSouthMain.add(btnModify);

        /**
         * =====注册一系列监听事件=====
         */
        //这里先获得两个面板的布局方式
        layoutCenter = (CardLayout)panelCenter.getLayout();
        layoutSouth = (CardLayout)panelSouth.getLayout();
        //1.顶部的事件监听
            //搜索框的，还需要进一步优化
        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String massage = txtSearch.getText();
                labelCenterMassage.setText(massage);
                layoutCenter.show(panelCenter,"表格信息");
            }
        });
            //搜索按钮的
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String massage = txtSearch.getText();
                labelCenterMassage.setText(massage);
                layoutCenter.show(panelCenter,"表格信息");
            }
        });

        //2.底部面板的主界面事件监听
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layoutCenter.show(panelCenter,"添加界面");
                layoutSouth.show(panelSouth,"添加界面按钮");
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //3.底部面板的添加界面的按钮的事件监听
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
                    layoutCenter.show(panelCenter,"表格信息");
                    layoutSouth.show(panelSouth,"主界面按钮");
                }
            }
        });

        //====添加主要组件====
        this.add(panelNorth,BorderLayout.NORTH);
        this.add(panelCenter);
        this.add(panelSouth,BorderLayout.SOUTH);

        //====设置窗口大小和关闭方式等====
        this.setBounds(300,300,800,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args){
        new MainFrame();
    }
}
