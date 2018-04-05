package com.JBB.tedu.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.JBB.tedu.Util.EntityUtil;
import com.JBB.tedu.dbutil.BuMenDBUtil;
import com.JBB.tedu.dbutil.HuiBaoDBUtil;
import com.JBB.tedu.dbutil.UserDBUtil;
import com.JBB.tedu.entity.Bumen;
import com.JBB.tedu.entity.HuiBao;
import com.JBB.tedu.entity.User;
import com.opensymphony.xwork2.ActionContext;


public class LoginAction {
    public int f;//f用来确定是页面跳转-1还是提交的登录请求-0
    public User user;
    public Map session = ActionContext.getContext().getSession();
    public String hbmesg;
    public List<User> User;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getF() {

        return f;
    }

    public void setHbmesg(String hbmesg) {
        this.hbmesg = hbmesg;
    }

    public String getHbmesg() {
        return hbmesg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //主页面上查询员工通讯录
    public String findygtxl() {
        /**
         * 执行效率慢，可提高效率，慢在需要查询所有数据
         * 1.查询出数据库中所有用户
         * 2.用hbmesg分别和用户名、工号比较
         //解决方法：从数据库中查询出指定数据
         //难点：指定数据字段为name和gh
         */
        User user = UserDBUtil.findUserByNameOrGH(hbmesg);
        if (hbmesg.equals("请输入员工姓名或工号")) {
            session.put("users", pjyhtxl());
            return "success";
        }
        if (user.getId() == 0) {
            session.put("users", null);
        } else {
            List<User> users = new ArrayList<User>();
            users.add(user);
            session.put("users", user);
            pjyhtxl(user);
        }
        return "success";
    }

    /**
     * 登录
     *
     * @return
     */
    public String login() {
        //使用前台页面传递过来的用户名和后台数据库中的进行比较。
        //登录失败
        System.out.println("f=" + f);
        if (1 == f) {
            return "success";
        }
        User us = UserDBUtil.findUserByGh(user.getGh());
        //1.验证登录时传值是否有错
        //由于用户名空，可能导致数据库查询崩溃系统会在这里出现奔溃
        if ((user.getPassword()).equals(us.getPassword())) {
            //1、随时清空session
            session.put("name", us.getUserName());
            //2、随时清空session
            session.put("pow", us.getPow());
            //3、随时清空session
            //把拼接好的页面用户通讯录放入到session中，
            session.put("users", pjyhtxl());
            //4、随时清空session
            session.put("gh", user.getGh());
            //自动更新当前用户上周期数据
            if (2 != us.getPow()) {
                //自动更新周报
                updateWF(us, 0, EntityUtil.weekNum - 1);
                //自动更新月报
                updateWF(us, 1, EntityUtil.paseMonth() - 1);
                //自动更新年报
                updateWF(us, 2, EntityUtil.paseYear() - 1);
            }
            //在exit中清空session
            //上一周未发汇报员工
            session.put("wfhbus", wfhbus());
            return "success";
        }
        return "error";
    }

    /**
     * 上周未发汇报的
     *
     * @param us
     * @param ty
     * @param zyys
     */
    private void updateWF(User us, int ty, int zyys) {
        HuiBao hb = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndZyys(us.getId(), ty, zyys);
        if (0 == hb.getUser_id()) {
            //自己写了newHuiBao的方法，但是由于变量多时太繁琐所以其他实体都没有用
            hb = hb.newHuiBao(ty, null, zyys, BuMenDBUtil.findByID(us.getBm_id()).getName(), UserDBUtil.findUserByID(us.getManager_id()).getUserName(), null, us.getId(), 0);
//            hb.setTy(ty);
//            hb.setZyys(zyys);
//            hb.setUser_id(us.getId());
//            hb.setSt(0);
//            hb.setBmName(BuMenDBUtil.findByID(us.getBm_id()).getName());
//            hb.setM_name(UserDBUtil.findUserByID(us.getManager_id()).getUserName());
            HuiBaoDBUtil.insertHuiBao(hb);
        }
    }

    /**
     * 拼接主页面上所有用户通讯录
     */
    private List<User> pjyhtxl() {
        //得到数据库中的所有用户，拼接成页面上显示需要
        List<User> users = UserDBUtil.findAllUsers();
        //测试数是否从数据库中得到用户
        System.out.println(users.size());
        //根据数据库返回的用户信息，拼接页面上显示需要的用户信息
        for (int i = 0; i < users.size(); i++) {
            //得到用户
            User user = users.get(i);
            //拼接页面上需要显示的用户信息
            pjyhtxl(user);
        }
        return users;
    }


    /**
     * 拼接页面上单个用户的部门进通讯录
     */
    public User pjyhtxl(User user) {
        //根据用户中的部门ID，得到部门信息。
        Bumen bm = BuMenDBUtil.findByID(user.getBm_id());
        user.setBm_name(bm.getName());
        return user;
    }

    private List<User> wfhbus() {
        List<User> users = new ArrayList<User>();
        //1.查出上周周报未发的员工
        wf(0, EntityUtil.weekNum - 1, users);
        //2.查出上月月未发的员工
        wf(1, EntityUtil.paseMonth() - 1, users);
        //3.查出上年年报未发的员工
        wf(2, EntityUtil.paseYear() - 1, users);
        return users;
    }

    private void wf(int ty, int zyys, List<User> users) {
        List<HuiBao> hbs = HuiBaoDBUtil.findWFHB(ty, zyys, 0);
        for (int i = 0; i < hbs.size(); i++) {
            User user = UserDBUtil.findUserByID(hbs.get(i).getUser_id());
            if (0 == ty) {
                user.setTy("周报");
            } else if (1 == ty) {
                user.setTy("月报");
            } else if (2 == ty) {
                user.setTy("年报");
            }
            user.setZyy(zyys);
            users.add(user);
        }
    }

    public String listwf() {
        if (hbmesg.equals("请输入员工姓名或工号")) {
            session.put("wfhbus", wfhbus());
            return "success";
        }
        User user = UserDBUtil.findUserByNameOrGH(hbmesg);
        System.out.println("LoginAction:" + hbmesg);
        if (0 == user.getId()) {
            session.put("wfhbus", null);
        } else {
            List<HuiBao> hbs = HuiBaoDBUtil.findHuiBaoByUseridAndST(user.getId(), 0);
            List<User> users = new ArrayList<User>();
            for (int i = 0; i < hbs.size(); i++) {
                User us = new User();
                us.setUserName(user.getUserName());
                us.setGh(user.getGh());
                if (0 == hbs.get(i).getTy()) {
                    us.setTy("周报");
                } else if (1 == hbs.get(i).getTy()) {
                    us.setTy("月报");
                } else if (2 == hbs.get(i).getTy()) {
                    us.setTy("年报");
                }
                us.setZyy(hbs.get(i).getZyys());
                users.add(us);
            }
            session.put("wfhbus", users);
        }
        return "success";
    }

    /**
     * @return
     */
    public String exit() {
        //清空全部session
        session.put("name", null);
        session.put("pow", null);
        session.put("users", null);
        session.put("gh", null);
        return "success";
    }
}

