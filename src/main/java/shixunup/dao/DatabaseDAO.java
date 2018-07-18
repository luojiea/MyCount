package shixunup.dao;

public class DatabaseDAO {
    public static Database getDatabase(){
        Database database = new Database();
        database.setDriverName("com.mysql.jdbc.Driver");
        database.setDbURL("jdbc:mysql://127.0.0.1:3306/cnb");
        database.setRoot("root");
        database.setPassword("root");
        return database;
    }
}
