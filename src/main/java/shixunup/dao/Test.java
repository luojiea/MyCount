//package shixunup.dao;
//
//
//import java.util.List;
//import java.util.Scanner;
//
//public class Test {
//    AccountDAO accountDAO = new AccountDAO();
//
//    public Test() {
//        Scanner input = new Scanner(System.in);
//
//        while(true) {
//            System.out.println("|==========================================================================|");
//            System.out.println("|================================欢迎使用==================================|");
//            System.out.println("|0.查看数据                                                                |");
//            System.out.println("|1.添加数据                                                                |");
//            System.out.println("|2.修改数据                                                                |");
//            System.out.println("|3.删除数据                                                                |");
//            System.out.println("|4.退出系统                                                                |");
//            System.out.println("|请选择你要进行的操作！                                                    |");
//            System.out.print("|=========:");
//
//            int select;
//            for(select = input.nextInt(); select < 0 || select > 4; select = input.nextInt()) {
//                System.out.println("|操作不能识别，请重新选择！                                                |");
//                System.out.print("|=========:");
//            }
//
//            if (select == 0) {
//                List<Account> list = accountDAO.getAllAccount();
//                for (Account account:list) {
//                    System.out.println(account.getId()+"      "+account.getUserAccount()
//                            +"      "+account.getUserPassword()+"      "+account.getCcreateDate()+"      ");
//                }
//
//            } else if (select == 1) {
//                this.addDataMenu();
//            } else if (select == 2) {
//                this.updateDataMenu();
//            } else if (select == 3) {
//                System.out.print("请输入id:");
//                this.accountDAO.delete(input.nextInt());
//            } else {
//                System.exit(0);
//            }
//        }
//    }
//
//    private void addDataMenu() {
//        Account account = new Account();
//        boolean flag = true;
//        Scanner input = new Scanner(System.in);
//        System.out.println("|=================================添加数据=================================|");
//
//        while(true) {
//            String conSelect;
//            do {
//                if (!flag) {
//                    return;
//                }
//
//                System.out.print("|请输入要添加的账号:");
//                String accountName = input.next();
//                System.out.print("|请输入要添加的密码:");
//                String password = input.next();
//                account.setUserAccount(accountName);
//                account.setUserPassword(password);
//                this.accountDAO.insert(account);
//                System.out.println("|添加成功！                                                               |");
//                System.out.print("|是否继续添加？(Y(继续)(其它返回)):");
//                conSelect = input.next();
//            } while(conSelect.equals("Y") && conSelect.equals("y"));
//
//            flag = false;
//        }
//    }
//
//    private void updateDataMenu() {
//        Scanner input = new Scanner(System.in);
//
//        while(true) {
//            System.out.println("|==========================================================================|");
//            System.out.println("|================================修改数据==================================|");
//            System.out.println("|0.修改账号                                                                |");
//            System.out.println("|1.修改密码                                                                |");
//            System.out.println("|2.修改账号和密码                                                          |");
//            System.out.println("|3.返回                                                                    |");
//            System.out.println("|请选择你要进行的操作！                                                    |");
//            System.out.print("|=========:");
//
//            int select;
//            for(select = input.nextInt(); select < 0 || select > 3; select = input.nextInt()) {
//                System.out.println("|操作不能识别，请重新选择！                                                 |");
//                System.out.print("|=========:");
//            }
//
//            Account account;
//            if (select == 0) {
//                account = new Account();
//                System.out.print("|请输入需要修改的id:");
//                account.setId(input.nextInt());
//                System.out.print("|请输入新的账号:");
//                account.setUserAccount(input.next());
//                this.accountDAO.updateAccount(account);
//            } else if (select == 1) {
//                account = new Account();
//                System.out.print("|请输入需要修改的id:");
//                account.setId(input.nextInt());
//                System.out.print("|请输入新的密码:");
//                account.setUserPassword(input.next());
//                this.accountDAO.updatePassword(account);
//            } else {
//                if (select != 2) {
//                    return;
//                }
//
//                System.out.print("|请输入需要修改");
//            }
//        }
//    }
//
//    public static void main(String ... args) {
//        new Test();
//    }
//}
//
