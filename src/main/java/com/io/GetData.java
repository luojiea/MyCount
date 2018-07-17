package com.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author 23149330203
 */
public class GetData {
    /**
     *
     * @return
     */
    public String[][] getAllData() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[][] values = null;
        int index = 0;
        try {
            //1.建立数据库连接
            connection = this.getConnection();
            //2.建立sql语句
            String sql = "select * from account";
            //3.获取数据
            if(connection != null) {
                statement = connection.createStatement();
                if(statement != null) {
                    resultSet = statement.executeQuery(sql);
                    resultSet.last();
                    values = new String[resultSet.getRow()][4];
                    resultSet.first();
                    resultSet.beforeFirst();
                    if(resultSet.getRow()<0){
                        resultSet = null;
                    }
                    if(resultSet != null) {
                        while (resultSet.next()) {
                            values[index][0] = ""+resultSet.getInt("id");
                            values[index][1] = resultSet.getString("user_account");
                            values[index][2] = resultSet.getString("user_password");
                            values[index][3] = ""+resultSet.getTimestamp("add_date");
                            index++;
                        }
                    }
                } else{
                    this.printNullPrintException();
                }
            } else{
                this.printNullPrintException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(resultSet,statement,connection);
        }
        return  values;
    }

    /**
     *
     * @param id
     * @return
     */
    public String[] getOneData(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] values = new String[4];
        try {
            //1.建立数据库连接
            connection = this.getConnection();
            //2.建立sql语句
            String aql = "select * from account" + " where id=" + id + "";
            //3.获取数据
            if(connection != null) {
                statement = connection.createStatement();
                if(statement != null){
                    resultSet = statement.executeQuery(aql);
                    if(resultSet != null) {
                        while (resultSet.next()) {
                            values[0] = ""+resultSet.getInt("id");
                            values[1] = resultSet.getString("user_account");
                            values[2] = resultSet.getString("user_password");
                            values[3] = ""+resultSet.getTimestamp("add_date");
                        }
                    } else {
                        this.printNullPrintException();
                    }
                } else {
                    this.printNullPrintException();
                }
            } else {
                this.printNullPrintException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(resultSet,statement,connection);
        }
        return  values;
    }

    /**
     *
     * @param keyValue
     * @return
     */
    public String[][] getDataByLikeAccount(String keyValue) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[][] values = new String[100][4];
        int index = 0;
        String sql = "select * from account" + " where user_account like '%" + keyValue + "%'";
        try {
            connection = this.getConnection();
            if(connection != null) {
                statement = connection.createStatement();
                if(statement != null) {
                    resultSet = statement.executeQuery(sql);
                    if(resultSet != null) {
                        while (resultSet.next()) {
                            values[index][0] = ""+resultSet.getInt("id");
                            values[index][1] = resultSet.getString("user_account");
                            values[index][2] = resultSet.getString("user_password");
                            values[index][3] = ""+resultSet.getTimestamp("add_date");
                            index++;
                        }
                    } else{
                        this.printNullPrintException();
                    }
                } else{
                    this.printNullPrintException();
                }
            } else{
                this.printNullPrintException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(resultSet,statement,connection);
        }
        return  values;
    }
    /**
     *
     * @return
     */
    private Connection getConnection() {
            Connection connection = null;
            try {
                // 加载驱动
                Class.forName("com.mysql.jdbc.Driver");
                String dbURL = "jdbc:mysql://localhost:3306/cnb";
                try {
                    connection = DriverManager.getConnection(dbURL, "root", "root");
                } catch(SQLException e){
                    e.printStackTrace();
                }
                return connection;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    private void closeConnection(Statement statement, Connection connection) {
            try {
                if (statement != null){
                    statement.close();
                } else{
                    this.printNullPrintException();
                }
                if (connection != null){
                    connection.close();
                } else{
                    this.printNullPrintException();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    private void closeConnection(ResultSet resultSet,Statement statement,Connection connection) {
            try {
                if (resultSet != null){
                    resultSet.close();
                } else{
                    this.printNullPrintException();
                }
                if (statement != null){
                    statement.close();
                } else{
                    this.printNullPrintException();
                }
                if (connection != null){
                    connection.close();
                } else{
                    this.printNullPrintException();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         *
         */
        private  void printNullPrintException (){
            NullPointerException nullPointerException = new NullPointerException();
            nullPointerException.printStackTrace();
        }
}
