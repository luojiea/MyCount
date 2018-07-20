package shixunup.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import shixunup.dao.Account;
import shixunup.dao.GetTableModel;
import shixunup.dao.IAccountDAo;
import shixunup.till.ApplicationConfig;
import shixunup.till.GetScreenCenterLocation;
import shixunup.till.ReadeImage;

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
    private  JPanel panelCenter = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            //绘制背景
            Image bg = ReadeImage.readImage("src/main/java/shixunup/image/bg2.jpg");
            g.drawImage(bg,-10, -10,810,600, null);
        }
    };

    /**
     * 注册属于中间面板的子面板
     * 1.添加项目的面板 panelCenterAdd
     * 2.显示已添加项目的面板 panelCenterTable
     */

    /**
     * //1.添加项目的面板
     */
    private  JPanel panelCenterAdd = new JPanel();
    private  JPanel panelCenterInAdd = new JPanel();
    private  JPanel panelCenterInAddAccount = new JPanel();
    private  JPanel panelCenterInAddPassword = new JPanel();

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

    private JLabel labelAccount = new JLabel(" Account :",SwingConstants.CENTER);
    private JLabel labelPassword = new JLabel("Password:",SwingConstants.CENTER);
    //创建四个文本域
    private JTextField areaAccount = new JTextField(25);
    private JTextField areaPassword = new JTextField(25);
    //2.显示信息的面板组件
    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();
    //声明变量
    private static int selectRow = -1;
    private static int selectColumn = -1;
    private String value;

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
    IAccountDAo accountDAO;
    private int option;
    {
        try {
            accountDAO = (IAccountDAo) Class.forName(ApplicationConfig.getDaoA()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * 组件注册结束
     */

    private void updateData(Account account){
        option = JOptionPane.showConfirmDialog(OperateJFrame.this,
                "确定要修改此数据么？","警告",
                JOptionPane.YES_NO_OPTION);
        if (option == 0){
            if(account.getUserAccount() == null){

            } else {

            }
        }
    }
    //构造方法
    /**
     * 实现窗口初始化和功能
     */
    public OperateJFrame() {
        panelNorth = new JPanel();
        //====设置名称====
        this.setTitle("欢迎使用本软件。。。。。。。。");

        //====添加顶部的面板内所有组件，并设置布局方式,此处的为上下部分的组件====
        panelNorth.setLayout(new BorderLayout());
        panelNorth.add(txtSearch);
        btnSearch.setBackground(Color.cyan);
        panelNorth.add(btnSearch,BorderLayout.EAST);

        //====添加中部的面板内所有组件，并设置布局方式===
        panelCenter.setLayout(new CardLayout(20,20));
        panelCenterTable.setOpaque(false);
        panelCenterAdd.setOpaque(false);
        panelCenter.setOpaque(false);
        panelCenter.add(panelCenterTable,"表格信息");
        panelCenter.add(panelCenterAdd,"添加界面");

        //1.添加添加项目面板的按钮
        panelCenterAdd.setLayout(new CardLayout(100,200));
        //按顺序将各个组件添加到面板上
        panelCenterInAdd.setLayout(new GridLayout(2,1));
        panelCenterInAdd.setOpaque(false);
        panelCenterInAddAccount.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelCenterInAddAccount.setOpaque(false);
        panelCenterInAddPassword.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelCenterInAddPassword.setOpaque(false);
        panelCenterInAddAccount.add(labelAccount);
        panelCenterInAddAccount.add(areaAccount);
        panelCenterInAddPassword.add(labelPassword);
        panelCenterInAddPassword.add(areaPassword);
        panelCenterInAdd.add(panelCenterInAddAccount);
        panelCenterInAdd.add(panelCenterInAddPassword);
        panelCenterAdd.add(panelCenterInAdd);
        //2.添加信息表格面板组件
        panelCenterTable.setLayout(new BorderLayout());
        table.setAutoCreateRowSorter(true);
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
        scrollPane.getViewport().add(table);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(null,
                0)));
        table.setOpaque(false);
        panelCenterTable.add(scrollPane);

        //====添加底部的面板内所有组件，并设置布局方式===
        panelSouth.setLayout(new CardLayout());
        panelSouth.add(paneSouthMain,"主界面按钮");
        panelSouth.add(panelSouthAdd,"添加界面按钮");
        //1.添加添加界面的按钮面板的所有组件
//        btnConfirm.setBackground( Color.green);
        panelSouthAdd.add(btnConfirm);
//        btnCancel.setBackground(Color.orange);
        panelSouthAdd.add(btnCancel);
        //2.添加主界面的按钮面板的所有组件
//        btnAdd.setBackground( Color.green);
        btnAdd.setOpaque(false);
        paneSouthMain.add(btnAdd);
//        btnDelete.setBackground(Color.red);
        btnDelete.setOpaque(false);
        paneSouthMain.add(btnDelete);
//        btnModify.setBackground(Color.orange);
        btnModify.setOpaque(false);
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
                layoutSouth.show(panelSouth,"主界面按钮");
            }
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( table.getSelectedRow()> -1 && table.getSelectedColumn()< 3 && table.getSelectedColumn()>0) {
                    if(selectRow == -1){
                        selectRow = table.getSelectedRow();
                        selectColumn = table.getSelectedColumn();
                    } else {
                        value = (String) table.getValueAt(selectRow,selectColumn);
                        dealUpdae();
                    }
                    table.editCellAt(selectRow,selectColumn);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        table.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String value = null;
                Account account  = accountDAO.getAccountById(Integer.parseInt(""+table.getValueAt(table.getSelectedRow(),0)));
                if (e.getKeyCode() == (char)KeyEvent.VK_ENTER){
                    if ( table.getSelectedRow()> -1 && table.getSelectedColumn()< 3 && table.getSelectedColumn()>0) {
                        value = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
                    }
                    if (table.getSelectedColumn() == 1) {
                        if ("".equals(value) || value.equals( account.getUserAccount())) {
                        } else {
                            if(updateConfig()){
                                account.setUserAccount(value);
                                accountDAO.updateAccount(account);
                            }
                            table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                        }
                    } else if(table.getSelectedColumn() == 2){
                        if ("".equals(value) || value.equals( account.getUserPassword())) {
                        } else {
                            if(updateConfig()){
                                account.setUserPassword(value);
                                accountDAO.updateAccount(account);
                            }
                            table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                        }
                    }
                }
                selectRow = -1;
                selectColumn = -1;
            }
        });
        //3.底部面板的主界面事件监听
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectRow = -1;
                selectColumn = -1;
                layoutCenter.show(panelCenter,"添加界面");
                layoutSouth.show(panelSouth,"添加界面按钮");
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectRow < 0){
                    JOptionPane.showMessageDialog(OperateJFrame.this,
                            "你未选择任何数据,请先点击修改后，选择一行数据！","提示",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    option = JOptionPane.showConfirmDialog(OperateJFrame.this,
                            "确定要删除此数据么？","警告",
                            JOptionPane.YES_NO_OPTION);
                    if(option == 0){
                        accountDAO.delete(Integer.parseInt(""+table.getValueAt(selectRow, 0)));
                        JOptionPane.showMessageDialog(OperateJFrame.this,
                                "删除成功！","提示",
                                JOptionPane.INFORMATION_MESSAGE);
                        table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                    }
                }
                selectRow = -1;
                selectColumn = -1;
            }
        });

        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setEnabled(true);
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
        this.setLocation(GetScreenCenterLocation.getLocation(800,600)[0],
                GetScreenCenterLocation.getLocation(300,600)[1]);
        this.setSize(800,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
                    }
    private boolean updateConfig(){
        option = JOptionPane.showConfirmDialog(OperateJFrame.this,
                "确定要刚更改选中的数据么？","警告",
                JOptionPane.YES_NO_OPTION);
        if(option == 0){
            return  true;
        } else {
            return false;
        }
    }
    private void dealUpdae(){
        Account account;
        if (table.getSelectedRow() != selectRow || table.getSelectedColumn() != selectRow){
            account = accountDAO.getAccountById(Integer.parseInt(""+table.getValueAt(selectRow,0)));
            if (selectColumn == 1) {
                if ("".equals(value) || value.equals( account.getUserAccount())) {
                    selectRow = table.getSelectedRow();
                    selectColumn = table.getSelectedColumn();
                } else {
                    if(updateConfig()){
                        account.setUserAccount(value);
                        accountDAO.updateAccount(account);
                    }
                    table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                    selectRow = -1;
                    selectColumn = -1;
                }
            } else if (selectColumn == 2){
                if ("".equals(value) || value.equals( account.getUserPassword())) {
                    selectRow = table.getSelectedRow();
                    selectColumn = table.getSelectedColumn();
                } else {
                    if(updateConfig()){
                        account.setUserPassword(value);
                        accountDAO.updateAccount(account);
                    }
                    table.setModel(GetTableModel.getTableModel(accountDAO.getAllAccount()));
                    selectRow = -1;
                    selectColumn = -1;
                }
            }
        }
    }
    public static void main(String[] args){
            new OperateJFrame();
    }
}