package main.java.com.ui;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private  void testConnection() {
        /**
         * 加载驱动
         */
        /**
         * 建立连接
         */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql//localhost/cnb";
            Connection connection = DriverManager.getConnection(dbURL,"root","root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
