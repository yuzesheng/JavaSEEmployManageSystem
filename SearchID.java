package com.lianxi.EmployeeManage;

import java.io.*;
import java.util.ArrayList;

/**
 * @author yuzs
 * @date 2022-07-2022/7/18-17:01
 */
public class SearchID {
    private SearchID(){}
    public static Employ searchId(int id){
        File file = new File(FilePath.PATH_NAME);
        if (file.exists()){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    try {
                        ArrayList<Employ> ems = (ArrayList<Employ>) ois.readObject();
                        ois.close();
                        for (int i = 0; i < ems.size(); i++) {
                            if(id == ems.get(i).getId()){
                                return ems.get(i);
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            return  null;
        }
        return null;
    }
}
