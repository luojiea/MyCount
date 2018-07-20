package shixunup.till;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import shixunup.dao.Database;

/**
 * @author 23149330203
 */
public class DatabaseConnectionUtil {
    /**
     * 实现对数据库建立连接
     * @param database 一个数据库对象
     * @return 返回一个数据库连接对象
     */
    public static Connection getConnection(Database database) {
        Connection connection = null;
        try {
            // 加载驱动
            Class.forName(database.getDriverName());
            try {
                connection = DriverManager.getConnection(database.getDbURL(),
                        database.getRoot(), database.getPassword());
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
     * 实现关闭的数据库连接对像
     * @param statement 要关闭的statement对象
     * @param connection 要关闭的connection对象
     */
    public static void closeConnection(Statement statement, Connection connection) {
        try {
            if (statement != null){
                statement.close();
            } else{
                printNullPrintException();
            }
            if (connection != null){
                connection.close();
            } else{
                printNullPrintException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现关闭的数据库连接对像，对上面的方法进行来了重载
     * @param resultSet 要关闭的resultSet对象
     * @param statement 要关闭的statement对象
     * @param connection 要关闭的statement对象
     */
    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null){
                resultSet.close();
            } else{
                printNullPrintException();
            }
            if (statement != null){
                statement.close();
            } else{
                printNullPrintException();
            }
            if (connection != null){
                connection.close();
            } else{
                printNullPrintException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 打印空指针异常
     */
    public static void printNullPrintException (){
        NullPointerException nullPointerException = new NullPointerException();
        nullPointerException.printStackTrace();
    }
}
