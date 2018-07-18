package shixunup.dao;

import java.util.Scanner;

/**
 * @author 23149330203
 */
public class Test {
        private final static int MEUN_MAIN_MAX = 4;
        private final static int MEUN_SELECT_AND_UPDATE_MAX = 3;
        private final static  String YES_SELECT_UP = "Y";
        private final static  String YES_SELECT_LOW = "y";
        private final static  String TITLE_AND_END = "|=================================================================="
                + "========|";
        private final static  String COLUMN_TITLE = "| " + " id  |" + "       账号         |" + "        密码        |" + "         创建日期" + "        |";
        AccountDAO accountDAO = new shixunup.dao.AccountDAO();
        public Test() {
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println(TITLE_AND_END);
                System.out.println("|================================欢迎使用==================================|");
                System.out.println("|0.查看数据                                                                |");
                System.out.println("|1.添加数据                                                                |");
                System.out.println("|2.修改数据                                                                |");
                System.out.println("|3.删除数据                                                                |");
                System.out.println("|4.退出系统                                                                |");
                System.out.println("|请选择你要进行的操作！                                                    |");
                System.out.print("|=========:");
                int select = input.nextInt();
                while (select < 0 || select > MEUN_MAIN_MAX) {
                    System.out.println("|操作不能识别，请重新选择！                                                |");
                    System.out.print("|=========:");
                    select = input.nextInt();
                }
                if (select == 0) {
                    System.out.println("查看数据");
                } else if (select == 1) {
                    this.addDataMenu();
                } else if (select == 2) {
                    this.updateDataMenu();
                } else if (select == 3) {
                    System.out.print("请输入id:");
                    accountDAO.delete(input.nextInt());
                } else {
                    System.exit(0);
                }
            }
        }

        private void addDataMenu() {
            shixunup.dao.Account account = new shixunup.dao.Account();
            String conSelect;
            boolean flag = true;
            Scanner input = new Scanner(System.in);
            System.out.println("|=================================添加数据=================================|");
            while (flag) {
                System.out.print("|请输入要添加的账号:");
                String accountName = input.next();
                System.out.print("|请输入要添加的密码:");
                String password = input.next();
                account.setUserAccount(accountName);
                account.setUserPassword(password);
                accountDAO.insert(account);
                System.out.println("|添加成功！                                                               |");
                System.out.print("|是否继续添加？(Y(继续)(其它返回)):");
                conSelect = input.next();
                if (!conSelect.equals(YES_SELECT_UP) || !conSelect.equals(YES_SELECT_LOW)) {
                    flag = false;
                }
            }
        }

        private void updateDataMenu() {
            shixunup.dao.Account account;
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println(TITLE_AND_END);
                System.out.println("|================================修改数据==================================|");
                System.out.println("|0.修改账号                                                                |");
                System.out.println("|1.修改密码                                                                |");
                System.out.println("|2.修改账号和密码                                                          |");
                System.out.println("|3.返回                                                                    |");
                System.out.println("|请选择你要进行的操作！                                                    |");
                System.out.print("|=========:");
                int select = input.nextInt();
                while (select < 0 || select > MEUN_SELECT_AND_UPDATE_MAX) {
                    System.out.println("|操作不能识别，请重新选择！                                                 |");
                    System.out.print("|=========:");
                    select = input.nextInt();
                }
                if (select == 0) {
                    account = new shixunup.dao.Account();
                    System.out.print("|请输入需要修改的id:");
                    account.setId(input.nextInt());
                    System.out.print("|请输入新的账号:");
                    account.setUserAccount(input.next());
                    accountDAO.updateAccount(account);
                } else if (select == 1) {
                    account = new shixunup.dao.Account();
                    System.out.print("|请输入需要修改的id:");
                    account.setId(input.nextInt());
                    System.out.print("|请输入新的密码:");
                    account.setUserPassword(input.next());
                    accountDAO.updatePassword(account);
                } else if (select == 2) {
                    System.out.print("|请输入需要修改");
                } else {
                    break;
                }
            }
        }
        public static void main(){
            new Test();
        }
}
