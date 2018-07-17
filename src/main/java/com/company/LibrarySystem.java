package com.company;

import java.sql.*;
import java.util.Scanner;

/**
 * @author 23149330203
 */
public class LibrarySystem {
    private final static int MEUN_MAIN_MAX = 6;
    private final static  String YES_SELECT_UP = "Y";
    private final static  String YES_SELECT_LOW = "y";
    private final static  String TITLE_AND_END = "|==================================================================" +
            "==============================================================|";

    /**
     * 建立数据库连接
     * @return Connection 返回建立的连接
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

    /**
     * 关闭的修改，添加，删除的连接
     * @param statement statement连接
     * @param connection connection连接
     */
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
    /**
     * 关闭获取数据的来连接
     * @param resultSet resultSet连接
     * @param statement statement连接
     * @param connection connection连接
     */
    private void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
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
     * 打印空指针异常，因为SQLException会抛出NullPrintException异常
     */
    private  void printNullPrintException (){
        NullPointerException nullPointerException = new NullPointerException();
        nullPointerException.printStackTrace();
    }

    /**
     *  1.执行添加数据
     * @param name name
     * @param publishers publishers
     * @param author author
     */
    private void insetBook(String name, String publishers, String author) {
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
                statement.executeUpdate("insert into book" + " (book_name,book_publishers,book_author) values" +
                        " ('" + name + "'," +
                        "'" + publishers + "',"+
                        "'" + author + "')");
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
     *  1.添加数据菜单
     */
    private void addBookMenu() {
        String conSelect;
        String name;
        String publishers;
        String author;
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        System.out.println("|=============================================================添加书籍===========================================================|");
        System.out.println("| " + " id  |" + "        书籍名          |" + "        出版商        |"+ "        作者        |" + "         创建日期" + "        |");
        while (flag) {
            System.out.print("|请输入要添加的书籍名:");
            name = input.next();
            System.out.print("|请输入要添加的出版商:");
            publishers = input.next();
            System.out.print("|请输入要添加的书籍作者:");
            author = input.next();
            this.insetBook(name, publishers, author);
            System.out.println("|添加成功！                                                               |");
            System.out.print("|是否继续添加？(Y(继续)(其它返回)):");
            conSelect = input.next();
            if (!YES_SELECT_UP.equals(conSelect) || !YES_SELECT_LOW.equals(conSelect)) {
                flag = false;
            }
        }
    }
    private void showOneBook(int id) {
        String[] values = this.getOneBook(id);
        System.out.println(TITLE_AND_END);
        System.out.println("| " + " id  |" + "       书籍名         |" + "        出版商        |"
                + "        作者        |" + "         创建日期" + "        |");
        System.out.print("|  ");
        System.out.printf("%-4s|", values[0]);
        System.out.printf("%-20s|", values[1]);
        System.out.printf("%-20s|", values[2]);
        System.out.print("   "+values[3]);
        System.out.print(" |"+System.lineSeparator());
        System.out.println(TITLE_AND_END);
    }
    /**
     * 2.1.修改书籍名
     * @param id id
     * @param name name
     */
    private void updateBookName(int id, String name) {
        Connection connection = null;
        Statement statement = null;
        //1.创建数据库连接
        try {
            connection = this.getConnection();
            //2.创建sql语句
            String sql = "update book set" + " book_name='" + name + "',create_time=NOW()" + " where id=" + id + "";
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
            this.showOneBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(statement,connection);
        }
    }

    /**
     * 2.2 修改出版商
     * @param id id
     * @param publishers publishers
     */
    private void updateBookPublishers(int id, String publishers) {
        //1.创建数据库连接
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            //2.创建sql语句
            String sql = "update book set" + " book_publishers='" + publishers + "',create_time=NOW()"
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
            this.showOneBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(statement,connection);
        }
    }
    /**
     * 2.3 修改作者
     * @param id id
     * @param author author
     */
    private void updateBookAuthor(int id, String author) {
        //1.创建数据库连接
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            //2.创建sql语句
            String sql = "update book set" + " book_author='" + author + "',create_time=NOW()"
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
            this.showOneBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(statement,connection);
        }
    }

    /**
     * 2.4 修改所有信息
     * @param id 的方式
     * @param name 方式是
     * @param publishers  附属公司
     * @param author 是否是
     */
    private void updateBookAll(int id, String name, String publishers, String author) {
        //1.创建数据库连接
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            //2.创建sql语句
            String sql = "update book set" + " book_name='" + name + "'," + "book_publishers='"+ publishers + "',"
                    + "book_author='" + author + "'," + "create_time=NOW()" + " where id=" + id + "";
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
            this.showOneBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(statement,connection);
        }
    }
    /**
     * 2.修改数据
     */
    private void updateDataMenu() {
        this.showTableAllBook();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(TITLE_AND_END);
            System.out.println("|===========================================================修改数据=============================================================|");
            System.out.println("|1.修改书籍名称                                                                                                                  |");
            System.out.println("|2.修改书籍出版商                                                                                                                |");
            System.out.println("|3.修改书籍作者                                                                                                                  |");
            System.out.println("|4.修改所有信息                                                                                                                  |");
            System.out.println("|5.返回                                                                                                                          |");
            System.out.println("|请选择你要进行的操作！                                                                                                          |");
            System.out.print("|=========:");
            int select = input.nextInt();
            while (select < 0 || select > MEUN_MAIN_MAX-1) {
                System.out.println("|操作不能识别，请重新选择！                                                 |");
                System.out.print("|=========:");
                select = input.nextInt();
            }
            if (select == 1) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的书籍名:");
                this.updateBookName(select, input.next());
            } else if (select == 2) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的出版商:");
                this.updateBookPublishers(select, input.next());
            }else if (select == 3) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的作者:");
                this.updateBookAuthor(select, input.next());
            } else if (select == 4) {
                System.out.print("|请输入需要修改的id:");
                select = input.nextInt();
                System.out.print("|请输入新的书籍名:");
                String name = input.next();
                System.out.print("|请输入新的出版商:");
                String publishers = input.next();
                System.out.print("|请输入新的作者:");
                this.updateBookAll(select, name, publishers, input.next());
            } else {
                break;
            }
        }
    }

    /**
     * 3. 删除书籍
     */
    private void deleteBook() {
        String value;
        String sql;
        String conSelect;
        int row = 0;
        Connection connection = null;
        Statement statement = null;
        Scanner input = new Scanner(System.in);
        this.showTableAllBook();
        System.out.println(TITLE_AND_END);
        System.out.println("|============================================================删除数据============================================================|");
        System.out.print("|请输入要删除的id(可删除多个，但请用“,”隔开):");
        value = input.next();
        String[] values = value.split(",");
        System.out.println("|确认删除？(Y(删除)/N(返回)):");
        conSelect = input.next();
        if (YES_SELECT_UP.equals(conSelect) || YES_SELECT_LOW.equals(conSelect)) {
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
                    sql = "delete from book where id=" + Integer.parseInt(id) + "";
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
    private String[][] getAllBook() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[][] values = new String[100][5];
        int index = 0;
        try {
            //1.建立数据库连接
            connection = this.getConnection();
            //2.建立sql语句
            String sql = "select * from book";
            //3.获取数据
            if(connection != null) {
                statement = connection.createStatement();
                if(statement != null) {
                    resultSet = statement.executeQuery(sql);
                    if(resultSet != null) {
                        while (resultSet.next()) {
                            values[index][0] = ""+resultSet.getInt("id");
                            values[index][1] = resultSet.getString("book_name");
                            values[index][2] = resultSet.getString("book_publishers");
                            values[index][3] = ""+resultSet.getString("book_author");
                            values[index][4] = ""+resultSet.getTimestamp("create_time");
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
    private String[] getOneBook(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] values = new String[5];
        try {
            //1.建立数据库连接
            connection = this.getConnection();
            //2.建立sql语句
            String aql = "select * from book" + " where id=" + id + "";
            //3.获取数据
            if(connection != null) {
                statement = connection.createStatement();
                if(statement != null){
                    resultSet = statement.executeQuery(aql);
                    if(resultSet != null) {
                        while (resultSet.next()) {
                            values[0] = ""+resultSet.getInt("id");
                            values[1] = resultSet.getString("book_name");
                            values[2] = resultSet.getString("book_publishers");
                            values[3] = ""+resultSet.getString("book_author");
                            values[4] = ""+resultSet.getTimestamp("create_time");
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
    private String[][] getDataByLike(String keyValue) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[][] values = new String[100][4];
        int index = 0;
        String sql = "select * from book" + " where book_name like '%" + keyValue + "%'"
                + "or book_publishers like '"+keyValue+"'"
                + "or book_author like '"+keyValue+"'";
        try {
            connection = this.getConnection();
            if(connection != null) {
                statement = connection.createStatement();
                if(statement != null) {
                    resultSet = statement.executeQuery(sql);
                    if(resultSet != null) {
                        while (resultSet.next()) {
                            values[index][0] = ""+resultSet.getInt("id");
                            values[index][1] = resultSet.getString("book_name");
                            values[index][2] = resultSet.getString("book_publishers");
                            values[index][3] = ""+resultSet.getString("book_author");
                            values[index][3] = ""+resultSet.getTimestamp("create_time");
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
     * 4.显示所书籍
     */
    private void showTableAllBook() {
        String[][] values = this.getAllBook();
        System.out.println("|======================================================已创建的信息===== ========================================================|");
        System.out.println("| " + " id  |" + "       书籍名         |" + "        出版商        |"
                + "        作者        |" + "         创建日期" + "        |");
        int index = 0;
        while(values[index][0] != null) {
            String[] value = values[index];
            System.out.print("|  ");
            System.out.printf("%-4s|", value[0]);
            System.out.printf("%-20s|", value[1]);
            System.out.printf("%-20s|", value[2]);
            System.out.printf("%-20s|", value[3]);
            System.out.print("   "+value[4]);
            System.out.println(" |");
            index++;
        }
        System.out.println(TITLE_AND_END);
    }
    private void showDataByLike() {
        Scanner input = new Scanner(System.in);
        System.out.print("|请输入关键字:");
        String[][] values = this.getDataByLike(input.next());
        System.out.println("|==========================================================已创建的信息==========================================================|");
        System.out.println("| " + " id  |" + "       书籍名         |" + "        出版商        |"+ "        作者        |" + "         创建日期" + "        |");
        int index = 0;
        while(values[index][0] != null) {
            String[] value = values[index];
            System.out.print("|  ");
            System.out.printf("%-4s|", value[0]);
            System.out.printf("%-20s|", value[1]);
            System.out.printf("%-20s|", value[2]);
            System.out.print("   "+value[3]);
            System.out.println(" |");
            index++;
        }
        System.out.println(TITLE_AND_END);
    }
    /**
     *
     */
    private LibrarySystem() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(TITLE_AND_END);
            System.out.println("|=========================================================欢迎使用===============================================================|");
            System.out.println("|1.添加书籍                                                                                                                      |");
            System.out.println("|2.修改数据                                                                                                                      |");
            System.out.println("|3.删除数据                                                                                                                      |");
            System.out.println("|4.查询所有书籍                                                                                                                  |");
            System.out.println("|5.按关键字对书籍名、出版商、作者进行模糊查询                                                                                    |");
            System.out.println("|6.退出系统                                                                                                                      |");
            System.out.println("|请选择你要进行的操作！                                                                                                          |");
            System.out.print("|=========:");
            int select = input.nextInt();
            while (select < 1 || select > MEUN_MAIN_MAX) {
                System.out.println("|操作不能识别，请重新选择！                                                                                                      |");
                System.out.print("|=========:");
                select = input.nextInt();
            }
            if (select == 1) {
                this.addBookMenu();
            } else if (select == 2) {
                this.updateDataMenu();
            } else if (select == 3) {
                this.deleteBook();
            } else if (select == 4) {
                this.showTableAllBook();
            } else if (select == 5) {
                this.showDataByLike();
            } else {
                System.exit(0);
            }
        }
    }
    public static void main(String[] args) {
        new LibrarySystem();
    }
}
