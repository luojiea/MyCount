package shixunup.till;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import shixunup.dao.Database;

public class DatabaseConnectionUtil {
    /**
     *
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
     *
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
     *
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
     *
     */
    private static void printNullPrintException (){
        NullPointerException nullPointerException = new NullPointerException();
        nullPointerException.printStackTrace();
    }
}
