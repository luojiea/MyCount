package shixunup.dao;

import java.sql.Timestamp;

public class Account {
    private int id;
    private String userAccount;
    private String userPassword;
    private Timestamp createDate;

    public Timestamp getCcreateDate() {
        return createDate;
    }

//    public void setCreate_date(Date create_date) {
//        this.create_date = create_date;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
