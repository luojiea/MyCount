package shixunup.till;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class ApplicationConfig {
    private final static String CONFIG_FILE_NAME = "application.config";
    private final static String DATA_FILE_NAME = "account.cnb";
    private String userName;
    private String userPassword;
//    private String passwordQuestion;
//    private String passwordAnswer;
    private String dbURL = "NOT";
    private String dbUser = "NOT";
    private String dbPassword = "NOT";
    private String dao;
    private String firstRun;

    private static Properties properties;
    static {
        try {
            File file = new File(DATA_FILE_NAME);
            file.mkdir();
            properties = new Properties();
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getUserName() {
        return properties.getProperty("UserName");
    }

    public static String getUserPassword() {
        return properties.getProperty("UserPassword");
    }

    public static String getPasswordQuestion() {
        return properties.getProperty("PasswordQuestion");
    }

    public static String getPasswordAnswer() {
        return properties.getProperty("PasswordAnswer");
    }

    public static String getDbURL() {
        return properties.getProperty("DbURL");
    }

    public static String getDbUser() {
        return properties.getProperty("DbUser");
    }

    public static String getDbPassword() {
        return properties.getProperty("DbPassword");
    }

    public static String getDaoA() {
        return properties.getProperty("DAO");
    }
    public static boolean isFirstRun() {
        if("true".equals(properties.getProperty("firstRun"))){
            return true;
        }
        return false;
    }

    public void setDaoA(String dao) {
        this.dao = dao;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

//    public void setPasswordQuestion(String passwordQuestion) {
//        this.passwordQuestion = passwordQuestion;
//    }
//
//    public void setPasswordAnswer(String passwordAnswer) {
//        this.passwordAnswer = passwordAnswer;
//    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    public void setFirstRun(String firstRun) {
        this.firstRun = firstRun;
    }

    public void WriteAppConfig(){
        Properties properties = new Properties();
        properties.setProperty("UserName",userName);
        properties.setProperty("UserPassword",userPassword);
//        properties.setProperty("PasswordQuestion",passwordQuestion);
//        properties.setProperty("PasswordAnswer",passwordAnswer);
        properties.setProperty("DbURL",dbURL);
        properties.setProperty("DbUser",dbUser);
        properties.setProperty("DbPassword",dbPassword);
        properties.setProperty("DAO",dao);
        properties.setProperty("firstRun",firstRun);
//            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(CONFIG_FILE_NAME);
            properties.store(fileOutputStream,null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
