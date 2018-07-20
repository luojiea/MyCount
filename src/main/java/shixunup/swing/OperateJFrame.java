package shixunup.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shixunup.dao.Account;
import shixunup.dao.AccountDAO;
import shixunup.dao.GetTableModel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author lj
 */
public class OperateJFrame extends JFrame{

    /**
     * ====注册最顶部的搜索组件组件=====
     * panelNorth
     */
    private JPanel panelNorth;
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

    /**
     * //1.添加项目的面板
     */
    private  JPanel panelCenterAdd = new JPanel();
    /**
     * //2.显示已添加项目的面板
     */

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
    private JTextField txtSearch = new JTextField();
    //2.搜索按钮
    private JButton btnSearch = new JButton("Search");

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
    private JTextField areaTitle = new JTextField();
    private JTextField areaAccount = new JTextField();
    private JTextField areaPassword = new JTextField();
    private JTextField areaRemark= new JTextField();
    //2.显示信息的面板组件
    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();

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
    AccountDAO accountDAO = new AccountDAO();
    int row = 0;
    int option = 1;

    /**
     * 组件注册结束
     */
    //构造方法
    /**
     * 实现窗口初始化和功能
     */
    public OperateJFrame(){
        panelNorth = new JPanel();
        //====设置名称====
        this.setTitle("This is MainFrame.....");

        //====添加顶部的面板内所有组件，并设置布局方式,此处的为上下部分的组件====
        panelNorth.setLayout(new BorderLayout());
        panelNorth.add(txtSearch);
        btnSearch.setBackground(Color.cyan);
        panelNorth.add(btnSearch,BorderLayout.EAST);

        //====添加中部的面板内所有组件，并设置布局方式===
        panelCenter.setLayout(new CardLayout());
        panelCenter.add(panelCenterTable,"表格信息");
        panelCenter.add(panelCenterAdd,"添加界面");

        //1.添加添加项目面板的按钮
        panelCenterAdd.setLayout(new GridLayout(4,2,0,20));
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
        table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
        scrollPane.getViewport().add(table);
        panelCenterTable.add(scrollPane);

        //====添加底部的面板内所有组件，并设置布局方式===
        panelSouth.setLayout(new CardLayout());
        panelSouth.add(paneSouthMain,"主界面按钮");
        panelSouth.add(panelSouthAdd,"添加界面按钮");
        //1.添加添加界面的按钮面板的所有组件
        btnConfirm.setBackground( Color.green);
        panelSouthAdd.add(btnConfirm);
        btnCancel.setBackground(Color.orange);
        panelSouthAdd.add(btnCancel);
        //2.添加主界面的按钮面板的所有组件
        btnAdd.setBackground( Color.green);
        paneSouthMain.add(btnAdd);
        btnDelete.setBackground(Color.red);
        paneSouthMain.add(btnDelete);
        btnModify.setBackground(Color.orange);
        paneSouthMain.add(btnModify);

        /**
         * =====注册一系列监听事件=====
         */

        //这里先获得两个面板的布局方式
        layoutCenter = (CardLayout)panelCenter.getLayout();
        layoutSouth = (CardLayout)panelSouth.getLayout();
        //1.顶部的事件监听
        //搜索框的，还需要进一步优化
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String keyword = txtSearch.getText();
                if(keyword == null || "".equals(keyword.trim())){
                    table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                } else {
                    table.setModel(GetTableModel.getTableModel(accountDAO.getAllByKeyword(keyword.trim())));
                }
                layoutCenter.show(panelCenter,"表格信息");
                layoutSouth.show(panelSouth,"主界面按钮");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String keyword = txtSearch.getText();
                if(keyword == null || "".equals(keyword.trim())){
                    table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                } else {
                    table.setModel(GetTableModel.getTableModel(accountDAO.getAllByKeyword(keyword.trim())));
                }
                layoutCenter.show(panelCenter,"表格信息");
                layoutSouth.show(panelSouth,"主界面按钮");
            }

            @Override
            public void changedUpdate(DocumentEvent e) { }
        });
        //搜索按钮的
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = txtSearch.getText();
                if(keyword == null || "".equals(keyword.trim())){
                    table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                } else {
                    table.setModel(GetTableModel.getTableModel(accountDAO.getAllByKeyword(keyword.trim())));
                }
                layoutCenter.show(panelCenter,"表格信息");
                layoutCenter.show(panelCenter,"表格信息");
                layoutSouth.show(panelSouth,"主界面按钮");
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
                row = table.getSelectedRow();
                if(row < 0){
                    JOptionPane.showMessageDialog(OperateJFrame.this,
                            "你未选择任何数据","提示",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    option = JOptionPane.showConfirmDialog(OperateJFrame.this,
                            "确定要删除此数据么？","警告",
                            JOptionPane.YES_NO_OPTION);
                    if(option == 0){
                        accountDAO.delete(Integer.parseInt(""+table.getValueAt(row, 0)));
                        JOptionPane.showMessageDialog(OperateJFrame.this,
                                "删除成功！","提示",
                                JOptionPane.INFORMATION_MESSAGE);
                        table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                    }
                }
            }
        });

        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row = table.getSelectedRow();
                System.out.println();
//                if(row < 0){
//                    JOptionPane.showMessageDialog(MainFrame.this,
//                            "你未选择任何数据","提示",
//                            JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    option = JOptionPane.showConfirmDialog(MainFrame.this,
//                            "确定要删除此数据么？", "警告",
//                            JOptionPane.YES_NO_OPTION);
//                    if (option == 0) {
//                        jdbcOperate.deleteData(Integer.parseInt("" + table.getValueAt(row, 0)));
//                        System.out.println("删除成功！");
//                    }
//                }
                }
            });


//3.底部面板的添加界面的按钮的事件监听
        btnConfirm.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
        //按顺序判断各个文本域是否为空
//                if(areaTitle.getText().equals("")){
//                    JOptionPane.showMessageDialog(MainFrame.this,
//                            "Title could not be null!");
//                    areaTitle.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red,
//                            1)));
//                }else
        if(areaAccount.getText().equals("")){
        JOptionPane.showMessageDialog(OperateJFrame.this,
        "Account could not be null!");
        areaAccount.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red,
        1)));
        }else if(areaPassword.getText().equals("")){
        JOptionPane.showMessageDialog(OperateJFrame.this,
        "Password could not be null!");
        areaPassword.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red,
        1)));
        }else {
        Account account = new Account();
        account.setUserAccount(areaAccount.getText());
        account.setUserPassword(areaPassword.getText());
        accountDAO.insert(account);
        JOptionPane.showMessageDialog(OperateJFrame.this, "添加成功","提示",JOptionPane.INFORMATION_MESSAGE);

//                    areaTitle.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(null,
//                            0)));
        areaAccount.setText("");
        areaAccount.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(null,
        0)));
        areaPassword.setText("");
        areaPassword.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(null,
        0)));
        table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
        }
        }
        });
        btnCancel.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
        //确认是否取消
        if(JOptionPane.showConfirmDialog(OperateJFrame.this,"确认取消？",null,
        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
        table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
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
        this.setMinimumSize(new Dimension(550,200));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        }
public static void main(String[] args){
        new OperateJFrame();
        }
        }