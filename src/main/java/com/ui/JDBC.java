package com.ui;
import java.sql.*;
import java.util.Scanner;


public class JDBC {
    private Connection getConnection() {
        // 加载驱动
        //建立连
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/cnb";
            Connection connection = DriverManager.getConnection(dbURL, "root", "root");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void insetData(String account, String password) {
        //创建连接
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into account" + " (user_account,user_password) values ('" + account + "','" + password + "')");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteData(JDBC jdbc) {
        String value = null;
        String sql = null;
        String conSelect;
        int row = 0;
        Scanner input = new Scanner(System.in);
        jdbc.showTableAllData();
        System.out.println("|=====================================================================|");
        System.out.println("|==============================删除数据===============================|");
        System.out.print("|请输入要删除的id(可删除多个，但请用“,”隔开):");
        value = input.next();
        String[] values = value.split(",");
        System.out.println("|确认删除？(Y(删除)/N(返回)):");
        conSelect = input.next();
        if (conSelect.equals("y") || conSelect.equals("Y")) {
            try {
                //1.创建数据库连接
                Connection connection = this.getConnection();
                //2.构建sql语句
                Statement statement = connection.createStatement();
                for (int i = 0; i < values.length; i++) {
                    sql = "delete from account where id=" + Integer.parseInt(values[i]) + "";
                    //3. 执行sql语句
                    row += statement.executeUpdate(sql);
                }
                //4. 得到返回的结果
                System.out.println("|删除了" + row + "条信息!                                                     |");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateAccountData(int id, String account) {
        //1.创建数据库连接
        try {
            Connection connection = this.getConnection();
            //2.创建sql语句
            String sql = "update account set" + " user_account='" + account + "',add_date=NOW()" + " where id=" + id + "";
            //3.执行sql语句
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            //4.得到返回的结果
            System.out.println("修改数据成功！");
            this.showTableAData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePasswordData(int id, String password) {
        //1.创建数据库连接
        try {
            Connection connection = this.getConnection();
            //2.创建sql语句
            String sql = "update account set" + " user_password='" + password + "',add_date=NOW()"
                    + " where id=" + id + "";
            //3.执行sql语句
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            //4.得到返回的结果
            System.out.println("修改数据成功！");
            this.showTableAData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAllData(int id, String account, String password) {
        //1.创建数据库连接
        try {
            Connection connection = this.getConnection();
            //2.创建sql语句
            String sql = "update account set" + " user_account='" + account + "',user_password='" + password + "',add_date=NOW()"
                    + " where id=" + id + "";
            //3.执行sql语句
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            //4.得到返回的结果
            System.out.println("修改数据成功！");
            this.showTableAData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showTableAData(int id) {
        try {
            //1.建立数据库连接
            Connection connection = this.getConnection();
            //2.建立sql语句
            String aql = "select * from account" + " where id=" + id + "";
            //3.获取数据
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(aql);
            System.out.println("|=====================================================================|");
            System.out.println("| " + "  id" + "    账号" + "              密码" + "                      创建日期" + "        |");
            while (resultSet.next()) {
                System.out.print("|  ");
                System.out.printf("%-4d", resultSet.getInt(1));
                System.out.printf("%-20s", resultSet.getString(2));
                System.out.printf("%-20s", resultSet.getString(3));
                System.out.print("\t" + resultSet.getTimestamp(4));
                System.out.println(" |");
            }
            System.out.println("|=====================================================================|");
            //4.显示数据
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showTableAllData() {
        try {
            //1.建立数据库连接
            Connection connection = this.getConnection();
            //2.建立sql语句
            String aql = "select * from account";
            //3.获取数据
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(aql);
            System.out.println("|============================已创建的信息=============================|");
            System.out.println("| " + "  id" + "    账号" + "              密码" + "                      创建日期" + "        |");
            while (resultSet.next()) {
                System.out.print("|  ");
                System.out.printf("%-4d", resultSet.getInt(1));
                System.out.printf("%-20s", resultSet.getString(2));
                System.out.printf("%-20s", resultSet.getString(3));
                System.out.print("\t" + resultSet.getTimestamp(4));
                System.out.println(" |");
            }
            System.out.println("|=====================================================================|");
            //4.显示数据
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addData(JDBC jdbc) {
        String conSelect;
        Scanner input = new Scanner(System.in);
        System.out.println("|==============================添加数据===============================|");
        while (true) {
            System.out.print("|请输入要添加的账号:");
            String account = input.next();
            System.out.print("|请输入要添加的密码:");
            String password = input.next();
            jdbc.insetData(account, password);
            System.out.println("|添加成功！                                                         |");
            System.out.print("|是否继续添加？(Y/N):");
            conSelect = input.next();
            if (conSelect.equals("n") || conSelect.equals("N")) {
                break;
            }
        }
    }

    private void updateData(JDBC jdbc) {
        jdbc.showTableAllData();
        String conSelect;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("|=====================================================================|");
            System.out.println("|==============================修改数据===============================|");
            System.out.println("|0.修改账号                                                           |");
            System.out.println("|1.修改密码                                                           |");
            System.out.println("|2.修改账号和密码                                                     |");
            System.out.println("|3.返回                                                               |");
            System.out.println("|请选择你要进行的操作！                                               |");
            System.out.print("|=========:");
            int select = input.nextInt();
            while (select < 0 || select > 3) {
                System.out.println("|操作不能识别，请重新选择！                                           |");
                System.out.print("|=========:");
                select = input.nextInt();
            }
            if (select == 0) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的账号:");
                jdbc.updateAccountData(select, input.next());
            } else if (select == 1) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的密码:");
                jdbc.updatePasswordData(select, input.next());
            } else if (select == 2) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的账号:");
                String account = input.next();
                System.out.print("|请输入新的密码:");
                jdbc.updateAllData(select, account, input.next());
            } else {
                break;
            }
        }
    }

    public JDBC() {
        String conSelect;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("|=====================================================================|");
            System.out.println("|==============================欢迎使用===============================|");
            System.out.println("|0.查看所有数据                                                       |");
            System.out.println("|1.添加数据                                                           |");
            System.out.println("|2.修改数据                                                           |");
            System.out.println("|3.删除数据                                                           |");
            System.out.println("|4.退出系统                                                           |");
            System.out.println("|请选择你要进行的操作！                                               |");
            System.out.print("|=========:");
            int select = input.nextInt();
            while (select < 0 || select > 4) {
                System.out.println("|操作不能识别，请重新选择！======================================================|");
                System.out.print("|=========:");
                select = input.nextInt();
            }
            if (select == 0) {
                this.showTableAllData();
            } else if (select == 1) {
                this.addData(this);
            } else if (select == 2) {
                this.updateData(this);
            } else if (select == 3) {
                this.deleteData(this);
            } else {
                System.exit(0);
            }
        }

    }

    public static void main(String[] args) {
        new JDBC();
    }
}
