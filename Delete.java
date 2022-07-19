package com.lianxi.EmployeeManage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author yuzs
 * @date 2022-07-2022/7/18-16:19
 */
public class Delete {
    private Scanner sc = new Scanner(System.in);
    private File file = new File(FilePath.PATH_NAME);
    private ArrayList<Employ> ems;
    public Delete(){
        if(file.exists()){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                try {
                    ems = (ArrayList<Employ>)ois.readObject();
                    ois.close();
                    if(ems!=null){
                        delete();
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
            System.out.println("数据文件不存在，无法删除");
        }
    }
    public boolean checkNum(String idStr){
        if(idStr == null || idStr.equals("")){
            System.out.println("非法输入，请重新输入");
            return  false;
        }
        char[] cs = idStr.toCharArray();
        for (int i = 0;i<cs.length;i++){
            if(cs[i] < '0'|| cs[i] > '9'){
                System.out.println("非法输入，请重新输入");
                return false;
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
            System.out.println("删除成功");
            System.out.println(ems);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void delete(){
        System.out.println("请输入要删除的员工ID:");
        int id = getRightNum();
        if(SearchID.searchId(id) != null){
            System.out.println("删除前用户的姓名为："+SearchID.searchId(id).getName());
            Iterator<Employ> it = ems.iterator();
            while(it.hasNext()){
                Employ em = it.next();
                if(id == em.getId()){
                    it.remove();
                    saveToFile();
                }
            }
        }else{
            System.out.println("对不起，查无此人");
            return;
        }

    }
}
