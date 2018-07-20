package shixunup.dao;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class GetTableModel {
    public static DefaultTableModel getTableModel(List<Account> list){
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                if(column == 1 || column == 2){
                    return true;
                } else {
                    return  false;
                }
            }
        };
        tableModel.addColumn("id");
        tableModel.addColumn("账户名");
        tableModel.addColumn("密码");
        tableModel.addColumn("最后一次修改日期");
        for (Account account:list) {
            Vector vector = new Vector();
            vector.add(account.getId());
            vector.add(account.getUserAccount());
            vector.add(account.getUserPassword());
            vector.add(account.getCcreateDate());
            tableModel.addRow(vector);
        }
        tableModel.setColumnCount(4);
        return  tableModel;
    }
}
