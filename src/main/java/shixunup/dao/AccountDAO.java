package shixunup.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import shixunup.till.DatabaseConnectionUtil;

public class AccountDAO {
    private Connection connection;
    private Statement statement;
    private String sql;
    private int row;
    /**
     * 实现增加账号
     * @return 返回是否成功修改
     */
    public boolean insert(Account account){
            try {
                sql = "insert into account" + " (user_account,user_password) values"
                        + " ('" + account.getUserAccount() + "','" + account.getUserPassword() + "')";
                this.builtStatement();
                row = this.statement.executeUpdate(sql);
                return row > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseConnectionUtil.closeConnection(statement,connection);
            }
        return false;
    }

    /**
     * 实现删除账号
     * @param id 得到需要删除的账号的id
     * @return 返回是否删除成功
     */
    public boolean delete(int id) {
        try {
            sql = "delete from account where id=" + id + "";
            //3. 执行sql语句
                this.builtStatement();
                row = this.statement.executeUpdate(sql);
                return row > 0;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.closeConnection(statement,connection);
        }
        return false;
    }

    /**
     *
     * @return 返回一个布尔值是否修改成功
     */
    public boolean updateAccount(Account account){
        try {
            //1.创建sql语句
            sql = "update account set" + " user_account='" + account.getUserAccount() + "',add_date=NOW()" + " where id=" + account.getId() + "";
            //3.执行sql语句
            this.builtStatement();
             row = statement.executeUpdate(sql);
            //4.得到返回的结果
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.closeConnection(statement,connection);
        }
        return false;
    }/**
     *
     * @return 返回一个布尔值是否修改成功
     */
    public boolean updatePassword(Account account){
        try {
            //2.创建sql语句
            sql = "update account set" + " user_password='" + account.getUserPassword() + "',add_date=NOW()"
                    + " where id=" + account.getId() + "";
            //3.执行sql语句
            this.builtStatement();
            row = statement.executeUpdate(sql);
            //4.得到返回的结果
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabaseConnectionUtil.closeConnection(statement,connection);
        }
        return false;
    }
    /**
     *
     */
    private void builtStatement(){
        try {
            //1.创建数据库连
            this.connection = DatabaseConnectionUtil.getConnection(DatabaseDAO.getDatabase());
            //2.构建sql语句
            if(this.connection != null) {
                this.statement = this.connection.createStatement();
            } else{
                DatabaseConnectionUtil.printNullPrintException();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
