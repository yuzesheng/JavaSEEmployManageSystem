package com.lianxi.EmployeeManage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author yuzs
 * @date 2022-07-2022/7/18-16:10
 */
public class Add {
    //定义输入sc
    private Scanner sc = new Scanner(System.in);
    //定义文件
    private File file = new File(FilePath.PATH_NAME);
    //定义员工类集合
    private ArrayList<Employ> ems;
    public Add(){
        //如果文件存在
        if(file.exists()){
            try {
                //读取file文件里面的对象流
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                try {
                    //将文件中的对象读取并转为员工集合
                    ems = (ArrayList<Employ>)ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //新建员工集合
            ems = new ArrayList<Employ>();
        }
        if(ems!=null){
            add();
        }else{
            System.out.println("系统内部问题，无法操作");
            return;
        }
    }
    public boolean checkNum(String idStr){
        //检查用户输入用户id格式
        if(idStr == null || idStr.equals("")){
            System.out.println("非法输入，请重新输入");
            return false;
        }
        char[] cs = idStr.toCharArray();
        for (int i = 0;i < cs.length;i++){
            if (cs[i] < '0' || cs[i] > '9'){
                System.out.println("输入非法，重来");
                return  false;
            }
        }
        return true;
    }
    private String idStr;
    public int getRightNum(){
        //获取用户输入的正确格式
        idStr = sc.nextLine();
        if(!checkNum(idStr)){
            getRightNum();
        }
        int id = Integer.parseInt(idStr);
        return id;
    }
    public void askGoOn(){
        //询问是否继续进行该操作
        System.out.println("请问是否继续录入？Y/N");
        String choise = sc.nextLine();
        if ("Y".equalsIgnoreCase(choise)){
            add();
        }else if("N".equalsIgnoreCase(choise)){
            saveToFile();
            return;
        }else{
            System.out.println("无此命令，请重新选择！！");
            askGoOn();
        }
    }
    public  void saveToFile(){
        //将集合输出到文件中
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(ems);
            oos.close();
            System.out.println("添加成功");
            System.out.println(ems);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void add(){
        System.out.println("请输入用户ID:");
        int id = getRightNum();
        for (int i = 0;i < ems.size();i++){
            if (id == ems.get(i).getId()){
                System.out.println("id已存在，请重新输入");
                add();
            }
        }
        System.out.println("请输入员工姓名：");
        String name = sc.nextLine();
        Employ em = new Employ(id,name);
        ems.add(em);
        //是否继续录入
        askGoOn();
    }

}
