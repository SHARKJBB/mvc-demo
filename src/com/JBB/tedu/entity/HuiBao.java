package com.JBB.tedu.entity;

public class HuiBao {
    //在区分daifa时引入的变量,机制类似User里的bm_name，ty
    public int have;
    public int nowZyys;

    public int getNowZyys() {
        return nowZyys;
    }

    public void setNowZyys(int nowZyys) {
        this.nowZyys = nowZyys;
    }

    public int getHave() {
        return have;
    }

    public void setHave(int have) {
        this.have = have;
    }

    //正经属于汇报的属性
    public int id;
    public int ty;//汇报类型0周报1月报2年报
    public String rq;//	汇报创建的日期 xxxx年XX月xx日
    public int zyys;//周数/月数/年数
    public String bm_name;//部门名称
    public String m_name;//领导名字
    public String csren;//抄送人名字
    public int user_id;///发件人ID
    public int st;//汇报的状态0 没有邮件 1已经发送 2等待发送 3已经批阅

    public static HuiBao newHuiBao(int ty, String rq, int zyys, String bm_name, String m_name, String csren, int user_id, int st) {
        HuiBao hb = new HuiBao();
        hb.ty = ty;
        hb.rq = rq;
        hb.zyys = zyys;
        hb.bm_name = bm_name;
        hb.m_name = m_name;
        hb.csren = csren;
        hb.user_id = user_id;
        hb.st = st;
        return hb;
    }

    @Override
    public String toString() {
        return "###HuiBao{" +
                "id=" + id +
                ", ty=" + ty +
                ", rq='" + rq + '\'' +
                ", zyys=" + zyys +
                ", bm_name='" + bm_name + '\'' +
                ", m_name='" + m_name + '\'' +
                ", csren='" + csren + '\'' +
                ", user_id=" + user_id +
                ", st=" + st +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTy(int ty) {
        this.ty = ty;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public void setZyys(int zyys) {
        this.zyys = zyys;
    }

    public void setBm_name(String bm_name) {
        this.bm_name = bm_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public void setCsren(String csren) {
        this.csren = csren;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public int getId() {

        return id;
    }

    public int getTy() {
        return ty;
    }

    public String getRq() {
        return rq;
    }

    public int getZyys() {
        return zyys;
    }

    public String getBm_name() {
        return bm_name;
    }

    public String getM_name() {
        return m_name;
    }

    public String getCsren() {
        return csren;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getSt() {
        return st;
    }

}
