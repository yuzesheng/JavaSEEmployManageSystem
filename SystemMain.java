package com.lianxi.EmployeeManage;

import java.util.Scanner;

/**
 * @author yuzs
 * @date 2022-07-2022/7/18-16:03
 */
public class SystemMain {
    /*
    * 菜单选项，根据输入来做crud
    * */
    private Scanner sc = new Scanner(System.in);
    public SystemMain(){
        showWelcome();
    }
    public void showWelcome(){
        System.out.println("----员工收录系统");
        System.out.println("1.增加员工功能");
        System.out.println("2.查看员工功能");
        System.out.println("3.修改员工功能");
        System.out.println("4.删除员工功能");
        System.out.println("5.退出系统");
        System.out.println("请输入您的选项：");
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                System.out.println("您选择了增加员工功能");
                new Add();
                break;
            case "2":
                System.out.println("您选择了查看用户功能");
                new ShowEmp();
                break;
            case "3" :
                System.out.println("您选择了修改用户功能");
                new Modify();
                break;
            case "4" :
                System.out.println("您选择了删除用户功能");
                new Delete();
                break;
            case "5" :
                System.out.println("您选择了退出系统");
                return;
            default:
                System.out.println("无此功能");
                break;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showWelcome();
    }
    public static void main(String[] args) {
        new SystemMain();
    }
}
