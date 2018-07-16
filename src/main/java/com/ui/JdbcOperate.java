package com.ui;
import java.sql.*;
import java.util.Scanner;
/**
 * @author 23149330203
 */
public class JdbcOperate {
    private final static int MEUN_MAIN_MAX = 4;
    private final static int MEUN_SELECT_AND_UPDATE_MAX = 3;
    private final static  String YES_SELECT_UP = "Y";
    private final static  String YES_SELECT_LOW = "y";
    private final static  String TITLE_AND_END = "|==========================================================================|";
    private final static  String COLUMN_TITLE = "| " + " id  |" + "       账号         |" + "        密码        |" + "         创建日期" + "        |";

    /**
     * 此方法实现对数据库的连接
     * @return Connection 返回数据库的连接
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
    private void closeConnection(Statement statement,Connection connection) {
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
    private  void printNullPrintException (){
        NullPointerException nullPointerException = new NullPointerException();
        nullPointerException.printStackTrace();
    }
    /**
     *此方法实现数据的添加操作
     * @param account 账户名称
     * @param password 账户密码
     *
     */
    private void insetData(String account, String password) {
        //创建连接
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            if(connection != null) {
                statement = connection.createStatement();
            }else{
                this.printNullPrintException();
            }
            if(statement != null) {
                statement.executeUpdate("insert into account" + " (user_account,user_password) values ('" + account + "','" + password + "')");
            }else{
                this.printNullPrintException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(statement,connection);
        }
    }

    /**
     *此方法实现对已有数据的删除
     */
    private void deleteData() {
        String value;
        String sql;
        String conSelect;
        int row = 0;
        Connection connection = null;
        Statement statement = null;
        Scanner input = new Scanner(System.in);
        this.showTableAllData();
        System.out.println(TITLE_AND_END);
        System.out.println("|=================================删除数据=================================|");
        System.out.print("|请输入要删除的id(可删除多个，但请用“,”隔开):");
        value = input.next();
        String[] values = value.split(",");
        System.out.println("|确认删除？(Y(删除)/N(返回)):");
        conSelect = input.next();
        if (conSelect.equals(YES_SELECT_UP) || conSelect.equals(YES_SELECT_LOW)) {
            try {
                //1.创建数据库连
                connection = this.getConnection();
                //2.构建sql语句
                if(connection != null) {
                    statement = connection.createStatement();
                } else{
                    this.printNullPrintException();
                }
                for(String id : values) {
                    sql = "delete from account where id=" + Integer.parseInt(id) + "";
                    //3. 执行sql语句
                    if(statement != null) {
                        row += statement.executeUpdate(sql);
                    } else{
                        this.printNullPrintException();
                    }
                }
                //4. 得到返回的结果
                System.out.println("|删除了" + row + "条信息!                                                     |");
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                this.closeConnection(statement,connection);
            }
        }
    }

    private void updateAccountData(int id, String account) {
        Connection connection = null;
        Statement statement = null;
        //1.创建数据库连接
        try {
            connection = this.getConnection();
            //2.创建sql语句
            String sql = "update account set" + " user_account='" + account + "',add_date=NOW()" + " where id=" + id + "";
            //3.执行sql语句
            if(connection != null) {
                statement = connection.createStatement();
            } else{
                this.printNullPrintException();
            }
            if(statement != null) {
                statement.executeUpdate(sql);
            } else{
                this.printNullPrintException();
            }
            //4.得到返回的结果
            System.out.println("修改数据成功！");
            this.showOneData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(statement,connection);
        }
    }

    private void updatePasswordData(int id, String password) {
        //1.创建数据库连接
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            //2.创建sql语句
            String sql = "update account set" + " user_password='" + password + "',add_date=NOW()"
                    + " where id=" + id + "";
            //3.执行sql语句
            if(connection != null) {
                statement = connection.createStatement();
            } else{
                this.printNullPrintException();
            }
            if(statement != null) {
                statement.executeUpdate(sql);
            } else{
                this.printNullPrintException();
            }
            //4.得到返回的结果
            System.out.println("修改数据成功！");
            this.showOneData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(statement,connection);
        }
    }

    private void updateAllData(int id, String account, String password) {
        //1.创建数据库连接
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            //2.创建sql语句
            String sql = "update account set" + " user_account='" + account + "',user_password='" + password + "',add_date=NOW()"
                    + " where id=" + id + "";
            //3.执行sql语句
            if(connection != null) {
                statement = connection.createStatement();
            } else{
                this.printNullPrintException();
            }
            if(statement != null) {
                statement.executeUpdate(sql);
            } else{
                this.printNullPrintException();
            }
            //4.得到返回的结果
            System.out.println("修改数据成功！");
            this.showOneData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(statement,connection);
        }
    }
    private String[][] getAllData() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[][] values = new String[100][4];
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
    private String[] getOneData(int id) {
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
    private String[][] getDataByLikeAccount(String keyValue) {
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
    private void showTableAllData() {
        String[][] values = this.getAllData();
        System.out.println("|===============================已创建的信息===============================|");
        System.out.println(COLUMN_TITLE);
        int index = 0;
        while(values[index][0] != null) {
            String[] value = values[index];
            System.out.print("|  ");
            System.out.printf("%-4s|", value[0]);
            System.out.printf("%-20s|", value[1]);
            System.out.printf("%-20s|", value[2]);
            System.out.print("   "+value[3]);
            System.out.println(" |"+System.lineSeparator());
            index++;
        }
        System.out.println(TITLE_AND_END);
    }
    private void showOneData(int id) {
        String[] values = this.getOneData(id);
            System.out.println(TITLE_AND_END);
            System.out.println(COLUMN_TITLE);
                System.out.print("|  ");
                System.out.printf("%-4s|", values[0]);
                System.out.printf("%-20s|", values[1]);
                System.out.printf("%-20s|", values[2]);
                System.out.print("   "+values[3]);
                System.out.print(" |"+System.lineSeparator());
            System.out.println(TITLE_AND_END);
    }
    private void showDataByLikeAccount(String keyValue) {
        String[][] values = this.getDataByLikeAccount(keyValue);
        System.out.println("|===============================已创建的信息===============================|");
        System.out.println(COLUMN_TITLE);
        int index = 0;
        while(values[index][0] != null) {
            String[] value = values[index];
            System.out.print("|  ");
            System.out.printf("%-4s|", value[0]);
            System.out.printf("%-20s|", value[1]);
            System.out.printf("%-20s|", value[2]);
            System.out.print("   "+value[3]);
            System.out.println(" |"+System.lineSeparator());
            index++;
        }
        System.out.println(TITLE_AND_END);
    }
    private void showDataMenu() {
        int select;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(TITLE_AND_END);
            System.out.println("|================================查询数据==================================|");
            System.out.println("|0.查询所有                                                                |");
            System.out.println("|1.按id查询                                                                |");
            System.out.println("|2.按账号模糊查询                                                          |");
            System.out.println("|3.返回                                                                    |");
            System.out.println("|请选择你要进行的操作！                                                    |");
            System.out.print("|=========:");
            select = input.nextInt();
            while (select < 0 || select > MEUN_SELECT_AND_UPDATE_MAX) {
                System.out.println("|操作不能识别，请重新选择！                                                 |");
                System.out.print("|=========:");
                select = input.nextInt();
            }
            if (select == 0) {
                this.showTableAllData();
            } else if (select == 1) {
                System.out.print("|请输入需要查询的id:");
                this.showOneData(input.nextInt());
            } else if (select == 2) {
                System.out.print("|请输入一个账号字段:");
                this.showDataByLikeAccount(input.next());
            } else {
                break;
            }
        }
    }
    private void addDataMenu() {
        String conSelect;
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        System.out.println("|=================================添加数据=================================|");
        while (flag) {
            System.out.print("|请输入要添加的账号:");
            String account = input.next();
            System.out.print("|请输入要添加的密码:");
            String password = input.next();
            this.insetData(account, password);
            System.out.println("|添加成功！                                                               |");
            System.out.print("|是否继续添加？(Y(继续)(其它返回)):");
            conSelect = input.next();
            if (!conSelect.equals(YES_SELECT_UP) || !conSelect.equals(YES_SELECT_LOW)) {
                flag = false;
            }
        }
    }

    private void updateDataMenu() {
        this.showTableAllData();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(TITLE_AND_END);
            System.out.println("|================================修改数据==================================|");
            System.out.println("|0.修改账号                                                                |");
            System.out.println("|1.修改密码                                                                |");
            System.out.println("|2.修改账号和密码                                                          |");
            System.out.println("|3.返回                                                                    |");
            System.out.println("|请选择你要进行的操作！                                                    |");
            System.out.print("|=========:");
            int select = input.nextInt();
            while (select < 0 || select > MEUN_SELECT_AND_UPDATE_MAX) {
                System.out.println("|操作不能识别，请重新选择！                                                 |");
                System.out.print("|=========:");
                select = input.nextInt();
            }
            if (select == 0) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的账号:");
                this.updateAccountData(select, input.next());
            } else if (select == 1) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的密码:");
                this.updatePasswordData(select, input.next());
            } else if (select == 2) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的账号:");
                String account = input.next();
                System.out.print("|请输入新的密码:");
                this.updateAllData(select, account, input.next());
            } else {
                break;
            }
        }
    }
    private JdbcOperate() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(TITLE_AND_END);
            System.out.println("|================================欢迎使用==================================|");
            System.out.println("|0.查看数据                                                                |");
            System.out.println("|1.添加数据                                                                |");
            System.out.println("|2.修改数据                                                                |");
            System.out.println("|3.删除数据                                                                |");
            System.out.println("|4.退出系统                                                                |");
            System.out.println("|请选择你要进行的操作！                                                    |");
            System.out.print("|=========:");
            int select = input.nextInt();
            while (select < 0 || select > MEUN_MAIN_MAX) {
                System.out.println("|操作不能识别，请重新选择！                                                |");
                System.out.print("|=========:");
                select = input.nextInt();
            }
            if (select == 0) {
                this.showDataMenu();
            } else if (select == 1) {
                this.addDataMenu();
            } else if (select == 2) {
                this.updateDataMenu();
            } else if (select == 3) {
                this.deleteData();
            } else {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new JdbcOperate();
    }
}
