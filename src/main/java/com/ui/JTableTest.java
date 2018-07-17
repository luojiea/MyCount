package com.ui;

import com.io.GetData;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableTest{
    public static JScrollPane getJScrollPane() {
        JTable table = new JTable(getTableModel());
        table.setAutoCreateRowSorter(true);
        JScrollPane jScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return jScrollPane;
    }
    private static DefaultTableModel getTableModel(){
        Object [] heads = {"id","账户名","密码","最后一次修改日期"};
        Object[][] bodys = new GetData().getAllData();
        DefaultTableModel tableModel = new DefaultTableModel(bodys, heads);
        tableModel.setColumnCount(4);
        return  tableModel;
    }
//    public static void main(String[] args) {
//        new JTableTest();
//    }
}
