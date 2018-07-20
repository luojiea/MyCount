package shixunup.dao;

import java.util.List;

public interface IAccountDAo {
    /**
     * 实现增加账号
     * @return 返回是否成功修改
     */
    boolean insert(Account account);

    /**
     * 实现删除账号
     * @param id 得到需要删除的账号的id
     * @return 返回是否删除成功
     */
    boolean delete(int id) ;

    /**
     * 实现修改账号
     * @return 返回一个布尔值是否修改成功
     */
    boolean updateAccount(Account account);

    /**
     * 实现修改密码
     * @return 返回一个布尔值是否修改成功
     */
    boolean updatePassword(Account account);

    /**
     * 获得数据库已存的所有数据
     * @return 返回账号的集合
     */
    List<Account> getAllAccount();
    /**
     * 获得数据库已存的所有数据
     * @return 返回账号的集合
     */
    Account getAccountById(int id);

    /**
     * 此方法实现获得模糊查询的数据
     * @param keyword 模糊查询的关键字
     * @return 返回查询的得到数据集合
     */
    List<Account> getAllByKeyword(String keyword);
    void accountIdRefresh();
}
