package com.JBB.tedu.entity;

public class User {
    public int id;
    public String userName;
    public String password;
    public String gh;
    public String phone;
    public int pow;
    public int manager_id;
    public int bm_id;

    //通讯录中使用的部门名称
    public String bm_name;
    //未发汇报员工使用
    public String ty;
    public int zyy;

    public void setTy(String ty) {
        this.ty = ty;
    }

    public void setZyy(int zyy) {
        this.zyy = zyy;
    }

    public String getTy() {

        return ty;
    }

    public int getZyy() {
        return zyy;
    }

    public void setBm_name(String bm_name) {
        this.bm_name = bm_name;
    }

    public String getBm_name() {

        return bm_name;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public void setBm_id(int bm_id) {
        this.bm_id = bm_id;
    }

    public String getGh() {

        return gh;
    }

    public String getPhone() {
        return phone;
    }

    public int getPow() {
        return pow;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getManager_id() {
        return manager_id;
    }

    public int getBm_id() {
        return bm_id;
    }

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }


}
