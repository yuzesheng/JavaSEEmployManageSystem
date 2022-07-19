package com.lianxi.EmployeeManage;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author yuzs
 * @date 2022-07-2022/7/18-16:13
 */
public class Modify {
    private Scanner sc = new Scanner(System.in);
    private File file = new File(FilePath.PATH_NAME);
    private ArrayList<Employ> ems;
    public Modify(){
        if(file.exists()){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                try {
                    ems = (ArrayList<Employ>) ois.readObject();
                    ois.close();
                    if(ems!= null){
                        modify();
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
        for (int i = 0;i < cs.length;i++){
            if(cs[i] < '0' || cs[i] > '9'){
                System.out.println("非法输入，重来");
                return  false;
            }
        }
        return true;
    }
    private String idStr;
    public int getRightNum(){
        idStr = sc.nextLine();
        if(!checkNum(idStr)){
            getRightNum();
        }
        int id = Integer.parseInt(idStr);
        return id;
    }
    public void saveToFile(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(ems);
            oos.close();
            System.out.println("修改成功");
            System.out.println(ems);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void modify(){
        System.out.println("请输入要秀改的用户ID：");
        int id = getRightNum();
        if(SearchID.searchId(id)!= null){
            System.out.println("修改前用户的姓名为：" + SearchID.searchId(id).getName());
            System.out.println("请输入修改后的姓名：");
            String name = sc.nextLine();
            for (int i = 0;i < ems.size();i++){
                if(id == ems.get(i).getId()){
                    ems.get(i).setName(name);
                    saveToFile();
                }
            }
        }else{
            System.out.println("无此用户");
            return;
        }
    }
}
