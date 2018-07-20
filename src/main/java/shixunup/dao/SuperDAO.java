package shixunup.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shixunup.till.DatabaseConnectionUtil;

public class SuperDAO {
    private Connection connection ;
    private Statement statement = null;
    private ResultSet resultSet = null;

    protected int executeDML(String sql){
        try {
            this.connection = DatabaseConnectionUtil.getConnection(DatabaseDAO.getDatabase());
            if(this.connection != null) {
                this.statement = this.connection.createStatement();
            } else{
                DatabaseConnectionUtil.printNullPrintException();
            }
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.closeConnection(statement,connection);
        }
        return 0;
    }
    protected List<Object []> query(String sql){
        List<Object[]> datas = new ArrayList<>();
        try {
            this.connection = DatabaseConnectionUtil.getConnection(DatabaseDAO.getDatabase());
            if(this.connection != null) {
                this.statement = this.connection.createStatement();
                if(this.statement != null) {
                    resultSet = statement.executeQuery(sql);
                }
                ResultSetMetaData metaData = resultSet.getMetaData();
                int column = metaData.getColumnCount();
                while(resultSet.next()){
                    Object [] columnData = new Object[column];
                    for (int i = 0; i < columnData.length ; i++) {
                        columnData[i] = resultSet.getObject(i+1);
                    }
                    datas.add(columnData);
                }
            } else{
                DatabaseConnectionUtil.printNullPrintException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.closeConnection(resultSet,statement,connection);
        }
        return datas;
    }
}