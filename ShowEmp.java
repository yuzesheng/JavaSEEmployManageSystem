package com.lianxi.EmployeeManage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author yuzs
 * @date 2022-07-2022/7/18-16:10
 */
public class ShowEmp {
    private Scanner sc = new Scanner(System.in);
    private File file = new File(FilePath.PATH_NAME);
    private ArrayList<Employ> ems;
    public ShowEmp(){
        if(file.exists()){
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    try {
                        ems = (ArrayList<Employ>) ois.readObject();
                        ois.close();
                        if(ems!=null){
                            show();
                        }else{
                            System.out.println("系统内部问题，无法操作");
                            return;
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }else{
            System.out.println("数据文件不存在，无法查看");
            return;
        }
    }
    public boolean checkNum(String idStr){
        if(idStr == null || idStr.equals("")){
            System.out.println("非法输入，请重新输入");
            return false;
        }
        char[] cs = idStr.toCharArray();
        for(int i = 0;i < cs.length;i++){
            if(cs[i] < '0' || cs[i] > '9'){
                System.out.println("输入非法，请重新输入");
                return false;
            }
        }
        return true;
    }
    private String idStr;
    public int getRightNum(){
        idStr = sc.nextLine();
        if (!checkNum(idStr)){
            getRightNum();
        }
        int id = Integer.parseInt(idStr);
        return id;
    }
    public void show(){
        System.out.println("查看全部员工输入Y,查看单个员工输入N");
        String choice = sc.nextLine();
        if("Y".equalsIgnoreCase(choice)){
            System.out.println(ems);
            return;
        }else if("N".equalsIgnoreCase(choice)){
            System.out.println("请输入要查询员工的ID:");
            int id = getRightNum();
            if(SearchID.searchId(id) != null){
                System.out.println("您查找的员工信息威为：\n" + SearchID.searchId(id));
                return;
            }else{
                System.out.println("无此用户");
                return;
            }
        }else{
            System.out.println("无此命令，请重新选择！");
            show();
        }
    }

}
