package main.java.com.ui;

public class TeacherFrame {
    public static  void main(String[] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
