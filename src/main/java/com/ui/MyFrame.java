package main.java.com.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{
    public  MyFrame(){
        this.setTitle("This is a frame....");
        this.setSize(300,200);
        //设置布局方式
        FlowLayout flowLayout = new FlowLayout();
        GridLayout gridLayout = new GridLayout(3,2);
//        this.setLayout(flowLayout);
        this.setLayout(gridLayout);
        //创建按钮
        JButton btnNorth = new JButton("北方");
        JButton btnWast = new JButton("西方");
        JButton btnCenter = new JButton("中间");
        JButton btnEast = new JButton("东方");
        JButton btnSouth = new JButton("南方");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button =  (JButton) e.getSource();
                button.setText("设置成功！");
            }
        };
        btnNorth.addActionListener(actionListener);
        btnWast.addActionListener(actionListener);
        btnCenter.addActionListener(actionListener);
        btnEast.addActionListener(actionListener);
        btnSouth.addActionListener(actionListener);
        //添加按钮
//        this.add(btnNorth,BorderLayout.NORTH);
//        this.add(btnWast,BorderLayout.WEST);
//        this.add(btnCenter,BorderLayout.CENTER);
//        this.add(btnEast,BorderLayout.EAST);
//        this.add(btnSorth,BorderLayout.SOUTH);

        this.add(btnNorth,BorderLayout.NORTH);
        this.add(btnWast,BorderLayout.WEST);
        this.add(btnCenter,BorderLayout.CENTER);
        this.add(btnEast,BorderLayout.EAST);
        this.add(btnSouth,BorderLayout.SOUTH);
        //显示窗口
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        MyFrame myFrame = new MyFrame();

    }
}
