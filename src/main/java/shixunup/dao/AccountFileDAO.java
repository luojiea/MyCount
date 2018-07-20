package shixunup.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 23149330203
 */
public class AccountFileDAO implements IAccountDAo{
    private final static String FILE_NAME = "account.cnb";
    /**
     * 实现增加账号
     * @return 返回是否成功修改
     */
    @Override
    public boolean insert(Account account){
        StringBuffer data = filedReade();
        data.append(account.getId()+"="+account.getUserAccount()+"="+account.getUserPassword()+"="+
                (new Timestamp(System.currentTimeMillis())).toString()+System.lineSeparator());
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(data.toString());
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 实现删除账号
     * @param id 得到需要删除的账号的id
     * @return 返回是否删除成功
     */
    @Override
    public boolean delete(int id) {
        List<Account> list = getAllAccount();
        list.removeIf(p->p.getId() == id);
        return this.writeGroupData(list);
    }

    /**
     * 实现修改账号
     * @return 返回一个布尔值是否修改成功
     */
    @Override
    public boolean updateAccount(Account account){
        List<Account> list = getAllAccount();
        list.forEach(p->{
            if(p.getId() == account.getId()){
                p.setUserAccount(account.getUserAccount());
            }
        });
        return this.writeGroupData(list, account.getId());
    }
    /**
     * 实现修改密码
     * @return 返回一个布尔值是否修改成功
     */
    @Override
    public boolean updatePassword(Account account){
        List<Account> list = getAllAccount();
        list.forEach(p->{
            if(p.getId() == account.getId()){
                p.setUserPassword(account.getUserPassword());
            }
        });
        return this.writeGroupData(list, account.getId());
    }

    /**
     * 获得数据库已存的所有数据
     * @return 返回账号的集合
     */
    @Override
    public List<Account> getAllAccount(){
        accountIdRefresh();
        Account account;
        String datas = filedReade().toString();
        List<Account> list = new ArrayList<>();
        for (String data : datas.split(System.lineSeparator())) {
            String[] values = data.split("=");
            account = new Account();
            for (int i = 0; i < values.length ; i++) {

                switch (i){
                    case 0:{
                        account.setId(Integer.parseInt(values[0]));
                        break;
                    }
                    case 1:{
                        account.setUserAccount(values[1]);
                        break;
                    }
                    case 2:{
                        account.setUserPassword(values[2]);
                        break;
                    }case 3:{
                        if (values[3] !=null ) {
                            account.setCreateDate(Timestamp.valueOf(values[3]));
                        }
                        break;
                    }
                    default:break;
                }
            }
            list.add(account);
        }
        return list;
    }
    @Override
    public Account getAccountById(int id){
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
        List<Account> list = getAllAccount();
        List<Account> file;
        file = list.stream().filter(p->{
            if(p.getUserAccount().contains(keyword)){
                return true;
            }
            return  false;
        }).collect(Collectors.toList());

        return file;
    }

    private StringBuffer filedReade(){
        StringBuffer data = new StringBuffer();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String str = reader.readLine();
            while(str != null && !str.trim().equals("")) {
                data.append(str + System.lineSeparator());
                str = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    private boolean writeGroupData(List<Account> list){
        StringBuffer data = new StringBuffer();
        FileWriter writer;
        try {
            for (Account account: list) {
                data.append(account.getId() + "=" + account.getUserAccount() + "=" +
                        account.getUserPassword() + "=" + account.getCcreateDate() +
                        System.lineSeparator());
            }
            writer = new FileWriter(FILE_NAME);
            writer.write(data.toString());
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean writeGroupData(List<Account> list, int id){
        StringBuffer data = new StringBuffer();
        FileWriter writer;
        try {
            for (Account account: list) {
                if (account.getId() == id) {
                    data.append(account.getId() + "=" + account.getUserAccount() + "=" +
                            account.getUserPassword() + "=" + (new Timestamp(System.currentTimeMillis())).toString() +
                            System.lineSeparator());
                } else {
                    data.append(account.getId() + "=" + account.getUserAccount() + "=" +
                            account.getUserPassword() + "=" + account.getCcreateDate() +
                            System.lineSeparator());
                }
            }
            writer = new FileWriter(FILE_NAME);
            writer.write(data.toString());
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *此方法实现对id的更新，使其每次显示都从1开始排列
     */
    @Override
    public void accountIdRefresh(){
        int index = 0;
        Account account;
        String datas = filedReade().toString();
            List<Account> list = new ArrayList<>();
            for (String data : datas.split(System.lineSeparator())) {
                String[] values = data.split("=");
                account = new Account();
                for (int i = 0; i < values.length; i++) {
                    switch (i) {
                        case 0: {
                            account.setId(++index);
                            break;
                        }
                        case 1: {
                            account.setUserAccount(values[1]);
                            break;
                        }
                        case 2: {
                            account.setUserPassword(values[2]);
                            break;
                        }
                        case 3: {
                            if (values[3] == null) {
                                account.setCreateDate(new Timestamp(System.currentTimeMillis()));

                            } else {
                                account.setCreateDate(Timestamp.valueOf(values[3]));
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
                list.add(account);
            }
            writeGroupData(list);
    }
}
