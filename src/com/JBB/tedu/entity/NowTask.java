package com.JBB.tedu.entity;

public class NowTask {
    public int id;
    public String cdtask;
    public String xdrq;
    public String wcrq;
    public int hb_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setCdtask(String cdtask) {
        this.cdtask = cdtask;
    }

    public void setXdrq(String xdrq) {
        this.xdrq = xdrq;
    }

    public void setWcrq(String wcrq) {
        this.wcrq = wcrq;
    }

    public void setHb_id(int hb_id) {
        this.hb_id = hb_id;
    }

    public int getId() {

        return id;
    }

    public String getCdtask() {
        return cdtask;
    }

    public String getXdrq() {
        return xdrq;
    }

    public String getWcrq() {
        return wcrq;
    }

    public int getHb_id() {
        return hb_id;
    }

}
