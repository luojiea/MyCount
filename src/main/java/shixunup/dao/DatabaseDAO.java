package shixunup.dao;

import shixunup.till.ApplicationConfig;

/**
 * 获得一个数据库的的主要信息
 * @author 23149330203
 */
public class DatabaseDAO {
    /**
     * 实现获得一个数据库对象
     * @return 返回一个数据库对象
     */
    public static Database getDatabase(){
        Database database = new Database();
        database.setDriverName("com.mysql.jdbc.Driver");
        database.setDbURL(ApplicationConfig.getDbURL());
        database.setRoot(ApplicationConfig.getDbUser());
        database.setPassword(ApplicationConfig.getDbPassword());
//        database.setDriverName("com.mysql.jdbc.Driver");
//        database.setDbURL("jdbc:mysql://127.0.0.1:3306/cnb");
//        database.setRoot("root");
//        database.setPassword("root");
        return database;
    }
}
