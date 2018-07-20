package shixunup.till;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import shixunup.dao.DatabaseDAO;

public class CreatTableInDatabase {
    public void creatTable(){
        Connection connection = null;
        Statement statement = null;
        String sql = "CREATE TABLE IF NOT EXISTS ccount("
                + " id int(11) PRIMARY KEY auto_increment, remark varchar (100), user_account varchar(100), user_password varchar(100));";
        try {
            connection = DatabaseConnectionUtil.getConnection(DatabaseDAO.getDatabase());
            if(connection != null) {
                statement = connection.createStatement();
            } else{
                DatabaseConnectionUtil.printNullPrintException();
            }
            statement.addBatch(sql);
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.closeConnection(statement,connection);
        }
    }
}
