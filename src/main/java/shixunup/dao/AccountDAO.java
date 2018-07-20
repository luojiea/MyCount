package shixunup.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现对数据的增加，修改，删除，查询
 * @author 23149330203
 */
public class AccountDAO extends SuperDAO implements IAccountDAo{
    private String sql;
    @Override
    public boolean insert(Account account){
        sql = "insert into account" + " (user_account,user_password) values"
                + " ('" + account.getUserAccount() + "','" + account.getUserPassword() + "')";
        return executeDML(sql) > 0;
    }

    /**
     * 实现删除账号
     * @param id 得到需要删除的账号的id
     * @return 返回是否删除成功
     */
    @Override
    public boolean delete(int id) {
        sql = "delete from account where id=" + id + "";
        return executeDML(sql) > 0;
    }

    /**
     * 实现修改账号
     * @return 返回一个布尔值是否修改成功
     */
    @Override
    public boolean updateAccount(Account account){
        sql = "update account set" + " user_account='" + account.getUserAccount() + "',add_date=NOW()"
                + " where id=" + account.getId() + "";
        return executeDML(sql) > 0;
    }

    /**
     * 实现修改密码
     * @return 返回一个布尔值是否修改成功
     */
    @Override
    public boolean updatePassword(Account account){
        //2.创建sql语句
        sql = "update account set" + " user_password='" + account.getUserPassword() + "',add_date=NOW()"
                + " where id=" + account.getId() + "";
        return executeDML(sql) > 0;
    }

    /**
     * 获得数据库已存的所有数据
     * @return 返回账号的集合
     */
    @Override
    public List<Account> getAllAccount(){
        this.accountIdRefresh();
        sql = "select * from account";
        return this.conversionData(query(sql));
    }

    @Override
    public  Account getAccountById(int id){
        Account account = new Account();
        List<Account> list = getAllAccount();
        list.stream().forEach(p->{
            if (p.getId()==id){
                account.setId(id);
                account.setUserAccount(p.getUserAccount());
                account.setUserPassword(p.getUserPassword());
                account.setCreateDate(p.getCcreateDate());
            }
        });
        return account;
    }
    /**
     * 此方法实现获得模糊查询的数据
     * @param keyword 模糊查询的关键字
     * @return 返回查询的得到数据集合
     */
    @Override
    public List<Account> getAllByKeyword(String keyword){
        sql = "select * from account" + " where user_account like '%" + keyword + "%'";
        return this.conversionData(query(sql));
    }
    /**
     *此方法实现对id的更新，使其每次显示都从1开始排列
     */
    @Override
    public void accountIdRefresh(){
        int index = 1;
        sql = "select id from account";
        List<Object[]>  values = query(sql);
        for (Object[] value:values) {
            sql = "update account set" + " id='" + index + "'"
                    + " where id=" + Integer.parseInt("" + value[0]) + "";
            executeDML(sql);
            index++;
        }
    }

    /**
     * 此方法实现对获得的数据进行转换
     * @param values 需要转换的数据对像
     * @return 返回转换后的数据结果，即Account集合
     */
    private List<Account> conversionData(List<Object[]> values){
        List<Account> list = new ArrayList<>();
        Account account;
        for (Object[] value:values) {
            account = new Account();
            account.setId(Integer.parseInt((""+ value[0])));
            account.setUserAccount((String) value[1]);
            account.setUserPassword((String) value[2]);
            account.setCreateDate((Timestamp) value[3]);
            list.add(account);
        }
        return list;
    }
}
