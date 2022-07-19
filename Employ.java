package com.lianxi.EmployeeManage;

import java.io.Serializable;

/**
 * @author yuzs
 * @date 2022-07-2022/7/18-16:57
 */
public class Employ implements Serializable {
    /*
    * 员工bean，并且实现序列化
    * */
    private int id;
    private String name;
    public Employ(){

    }

    public Employ(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employ[id=" + id + ", name='" + name +"]\n";
    }
}
