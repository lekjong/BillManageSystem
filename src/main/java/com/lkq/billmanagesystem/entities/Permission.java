package com.lkq.billmanagesystem.entities;

public class Permission {

    private  int pid;         //权限id
    private  String name;     //权限的菜单及按钮名
    private  String type;     //权限的菜单及按钮类型；
    private  String permcode; //权限代码；

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPermcode() {
        return permcode;
    }

    public void setPermcode(String permcode) {
        this.permcode = permcode;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", permcode='" + permcode + '\'' +
                '}';
    }
}
