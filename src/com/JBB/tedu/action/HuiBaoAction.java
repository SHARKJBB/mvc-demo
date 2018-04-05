package com.JBB.tedu.action;

import com.JBB.tedu.Util.EntityUtil;
import com.JBB.tedu.dbutil.*;
import com.JBB.tedu.entity.*;
import com.opensymphony.xwork2.ActionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HuiBaoAction {
    //得到服务器中存在的session中的所有数据
    public Map session = ActionContext.getContext().getSession();

    //对应常用的实体构建容器
    public Bumen bm;
    public HuiBao hb;
    public List<User> users;
    public List<Bumen> bms;

    //汇报列表
    public List<HuiBao> hbs;
    //上周期的布置的这周期的工作重点列表,这周的工作重点是上一周的下周计划，所以类型是NextTask
    public List<NextTask> pts;

    //未批复的汇报列表，集中出现在超级管理员的收件箱，所以不可复用
    public List<HuiBao> noreplyzbs;
    public List<HuiBao> noreplyybs;
    public List<HuiBao> noreplynbs;

    //汇报的子内容 在xinjian.jsp和daifa.jsp使用
    public List<NowTask> nowts;
    public List<NextTask> nextts;
    public List<WenTi> wts;

    public List<HuiBao> getHbs() {
        return hbs;
    }

    public void setHbs(List<HuiBao> hbs) {
        this.hbs = hbs;
    }


    public List<NowTask> getNowts() {
        return nowts;
    }

    public void setNowts(List<NowTask> nowts) {
        this.nowts = nowts;
    }

    public List<NextTask> getNextts() {
        return nextts;
    }

    public void setNextts(List<NextTask> nextts) {
        this.nextts = nextts;
    }

    public List<WenTi> getWts() {
        return wts;
    }

    public void setWts(List<WenTi> wts) {
        this.wts = wts;
    }


    public void setHb(HuiBao hb) {
        this.hb = hb;
    }

    public void setBm(Bumen bm) {

        this.bm = bm;
    }

    public Bumen getBm() {

        return bm;
    }

    public HuiBao getHb() {
        return hb;
    }

    //    本周期承担任务
    public String[] cdtasks;
    //    本周期下达日期
    public String[] xdrqs;
    //    本周期完成日期
    public String[] wcrqs;

    /**
     * ext_代指下周这一栏的值
     * 名称对应于XINJIAN里的对应键
     */
    //    下周承担的任务
    public String[] next_cdtasks;
    //    下周计划的预计完成日期
    public String[] next_rqs;
    //    下周计划的责任人
    public String[] next_zrs;
    //    下周计划的协调部门
    public String[] next_xtbm;
    //    下周计划的备注
    public String[] next_bzs;

    /**
     * q_代指问题这一栏的值
     * 名称对应于XINJIAN里的对应键
     */
    //    问题里承担的任务
    public String[] q_cdtasks;
    //    问题的主要问题
    public String[] q_zywt;
    //    问题的对策及建议
    public String[] q_dcjjy;

    /*************************************这是首页的相关方法*************************************************/

    public String deleteAll() {
        HuiBaoDBUtil.deleteHuiBaoByST(0);
        return "success";
    }

/*************************************这是xinjian.jsp的相关方法*************************************************/
    /**
     * 新建汇报，根据hb.ty的值确定汇报类型 0-周报 1-月报 2-年报
     *
     * @return
     */
    public String newHB() {
        //为页面上的select传递全部的用户和部门
        users = UserDBUtil.findAllUsers();
        bms = BuMenDBUtil.findAllBumen();
        //获取页面需要的信息
        String name = session.get("name").toString();
        //根据用户名得到用户信息
        User user = UserDBUtil.findUserByName(name);
        bm = BuMenDBUtil.findByID(user.getBm_id());
        //确定数据库中唯一一条汇报数据需要：用户id，汇报类型ty,周数weekNum
        hb.setBm_name(bm.getName());
        hb.setRq(EntityUtil.parseDay());
        hb.setM_name(UserDBUtil.findUserByID(user.getManager_id()).getUserName());
        //根据汇报类型存入当前周期 0-周报 1-月报 2-年报
        if (hb.ty == 0) {
            hb.setZyys(EntityUtil.weekNum);
        } else if (1 == hb.ty) {
            hb.setZyys(EntityUtil.paseMonth());
        } else if (2 == hb.ty) {
            hb.setZyys(EntityUtil.paseYear());
        }

        //调取到上一周期的工作重点,写入到本周期的工作重点
        //工作汇报的状态设为不等于2，当状态时发送和批阅时都可以作为本周的工作汇报
        HuiBao lasehb = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndZyysAndSTORST(user.getId(), hb.ty, hb.zyys - 1, 1, 3);
        pts = NextTaskDBUtil.findNexttasksByHb_id(lasehb.getId());

        //判断本周期是否已发送或保存汇报
        // 通过获取数据库中本周期是否有同类型的记录判断
        HuiBao nowHuibao = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndZyys(user.getId(), hb.ty, hb.zyys);

        //通过session传值，标明本周期已有汇报的类型，来帮助xinjian页面
        session.put("hbst", 0);
        if (1 == nowHuibao.getSt()) {
            session.put("hbst", 1);
        } else if (2 == nowHuibao.getSt()) {
            session.put("hbst", 2);
        } else if (3 == nowHuibao.getSt()) {
            session.put("hbst", 3);
        }

        //跳转到对应的xinjian.jsp
        if (0 == hb.ty) {
            return "zb";
        } else if (1 == hb.ty) {
            return "yb";
        } else if (2 == hb.ty) {
            return "nb";
        }
        return "success";
    }

    /**
     * 发送或保存汇报
     * 发送或保存的操作由xinjian.jsp里传入的hb.st确定 1-发送 2-保存
     * 只有当数据库中没有本周期建汇报的记录,才能看见这项功能
     *
     * @return
     */
    public String sashb() {
        //获取页面需要的信息的来源实体
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);
        User manager = UserDBUtil.findUserByID(user.getManager_id());
        Bumen bumen = BuMenDBUtil.findByID(user.getBm_id());

        //构建父表中的汇报
        HuiBao huiBao = new HuiBao();
        huiBao.setTy(hb.ty);
        huiBao.setRq(EntityUtil.parseTime());
        huiBao.setZyys(hb.zyys);
        huiBao.setBm_name(bumen.getName());
        huiBao.setM_name(manager.getUserName());
        huiBao.setCsren(user.getUserName());
        huiBao.setUser_id(user.getId());
        huiBao.setSt(hb.st);
        HuiBaoDBUtil.insertHuiBao(huiBao);

//        对应数据库中不同表，新建一个存工作汇报的list，方便存到数据库
//        避免单个单次存储反复链接数据库造成不必要资源浪费
        List<NowTask> nowts = new ArrayList<NowTask>();
//        新建一个存下周计划工作重点的list，方便存到数据库
        List<NextTask> nexts = new ArrayList<NextTask>();
//        新建一个存在的问题及合理化建议的list，方便存到数据库
        List<WenTi> wts = new ArrayList<WenTi>();

        /**
         * 把数据插入
         * 在将汇报保存到数据库中时试过用写一个方法将内容保存进去，
         * 但是由于属性比较多，在使用方的时候会弄混参数的顺序，没有set方法直观
         * 所以在这里选择使用set方法填入信息
         *
         * 在查找hb_id的时候为了唯一确定
         * 需要确认当前用户 ；当前时间 ；当前汇报种类 ；当前汇报状态
         * 使用 findHuiBaoByUseridAndTYAndSTAndZyys方法
         */
        int hb_id = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndZyys(user.getId(), hb.ty, hb.zyys).getId();

        for (int i = 0; i < cdtasks.length; i++) {
            NowTask nt = new NowTask();
            nt.setCdtask(cdtasks[i]);
            nt.setXdrq(xdrqs[i]);
            nt.setWcrq(wcrqs[i]);
            nt.setHb_id(hb_id);
            nowts.add(nt);
        }
        for (int i = 0; i < next_cdtasks.length; i++) {
            NextTask nextTask = new NextTask();
            nextTask.setCdtask(next_cdtasks[i]);
            nextTask.setRq(next_rqs[i]);
            nextTask.setZr(next_zrs[i]);
            nextTask.setXtbm(next_xtbm[i]);
            nextTask.setBz(next_bzs[i]);
            nextTask.setHb_id(hb_id);
            nextTask.toString();
            nexts.add(nextTask);
        }
        for (int i = 0; i < q_cdtasks.length; i++) {
            WenTi wenTi = new WenTi();
            wenTi.setCdtask(q_cdtasks[i]);
            wenTi.setZywt(q_zywt[i]);
            wenTi.setDcjjy(q_dcjjy[i]);
            wenTi.setHb_id(hb_id);
            wts.add(wenTi);
        }

        //保存到数据库
        NowTaskDBUtil.saveNowTasks(nowts);
        NextTaskDBUtil.saveNextTasks(nexts);
        WenTiDBUtil.saveWeTis(wts);

        //根据发送和保存的不同状态给出不同的提示页面
        //跳转到对应的xinjian.jsp
        //汇报的状态0 没有邮件 1已经发送 2等待发送 3已经批阅
        if (2 == hb.st) {
            return "savesuccess";
        } else if (1 == hb.st) {
            return "sendsuccess";
        }
        return "success";
    }


/*************************************这是daifalist.jsp的相关方法*************************************************/
    /**
     * 新建未发汇报
     *
     * @return
     */
    public String newWF() {
        //获取页面需要的信息
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);
        if (hb.ty == 0) {
            hb.setZyys(EntityUtil.weekNum);
        } else if (1 == hb.ty) {
            hb.setZyys(EntityUtil.paseMonth());
        } else if (2 == hb.ty) {
            hb.setZyys(EntityUtil.paseYear());
        }
        //找到本周期里待发送的汇报
        HuiBao huiBao = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndST(user.getId(), hb.ty, 2);
        huiBao.have = 0;
        //如果有待发汇报
        if (huiBao.getId() != 0) {
            huiBao.have = 1;
        }

        //根据汇报的类型跳转到相应的待发页面
        //通过在seesion中加入diafahb，来携带diafahb的信息
        if (0 == hb.ty) {
            huiBao.setNowZyys(EntityUtil.weekNum);
            session.put("hbs", huiBao);
            return "zb";
        } else if (1 == hb.ty) {
            huiBao.setNowZyys(EntityUtil.paseMonth());
            session.put("hbs", huiBao);
            return "yb";
        } else if (2 == hb.ty) {
            huiBao.setNowZyys(EntityUtil.paseYear());
            session.put("hbs", huiBao);
            return "nb";
        }
        return "success";
    }

    /**
     * daifalist.jsp里发送汇报的功能
     *
     * @return
     */
    public String tosendhb() {
        //获取页面需要的信息
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);
        //找到本周期里待发送的汇报
        HuiBao huiBao = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndST(user.getId(), hb.ty, 2);
        //在数据库修改大汇报的状态st 从2-->1
        HuiBaoDBUtil.updateHuiBaoByIDSetST(huiBao.getId(), 1);
        return "sendsuccess";
    }

    /**
     * daifalist.jsp里编辑汇报的功能
     *
     * @return
     */
    public String edithb() {
        //获取页面需要的信息的来源实体
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);
        HuiBao huiBao = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndST(user.getId(), hb.ty, 2);
        //验证确实从数据库中得到用户信息
        System.out.println(user.getId());
        bm = BuMenDBUtil.findByID(user.getBm_id());
        //确定数据库中唯一一条汇报数据需要：用户id，汇报类型ty,周数weekNum
        hb.setBm_name(bm.getName());
        hb.setRq(EntityUtil.parseDay());
        hb.setM_name(UserDBUtil.findUserByID(user.getManager_id()).getUserName());
        //根据汇报类型存入当前周期 0-周报 1-月报 2-年报
        if (hb.ty == 0) {
            hb.setZyys(EntityUtil.weekNum);
        } else if (1 == hb.ty) {
            hb.setZyys(EntityUtil.paseMonth());
        } else if (2 == hb.ty) {
            hb.setZyys(EntityUtil.paseYear());
        }

        int hb_id = huiBao.getId();
        //找到当前hb_id下的三个子汇报里的具体信息
        nowts = NowTaskDBUtil.findNowTasksByHb_id(hb_id);
        nextts = NextTaskDBUtil.findNexttasksByHb_id(hb_id);
        wts = WenTiDBUtil.findAllWenTiByHb_id(hb_id);
        //给两个SELECT传入值
        users = UserDBUtil.findAllUsers();
        bms = BuMenDBUtil.findAllBumen();

        //调取到上一周期的工作重点,写入到本周期的工作重点
        //工作汇报的状态设为不等于2，当状态时发送和批阅时都可以作为本周的工作汇报
        HuiBao lasehb = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndZyysAndSTORST(user.getId(), hb.ty, hb.zyys - 1, 1, 3);
        pts = NextTaskDBUtil.findNexttasksByHb_id(lasehb.getId());

        if (0 == hb.ty) {
            return "zb";
        } else if (1 == hb.ty) {
            return "yb";
        } else if (2 == hb.ty) {
            return "nb";
        }
        return "success";
    }

    /**
     * 待发了列表 编辑功能 里面的发送或保存
     * @return
     */
    public String dfsendhb() {
        //从页面获取需要的信息
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);
        HuiBao huiBao = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndZyys(user.getId(), hb.ty, hb.zyys);
        int hb_id = huiBao.getId();

        //根据提交的状态更新数据库里的hb状态
        huiBao.setSt(hb.st);
        HuiBaoDBUtil.updateHuiBaoByIDSetST(hb_id, hb.st);
        //1.先删除三个子表中同hb_id的表项
        NowTaskDBUtil.deleteNowTaskByHb_id(hb_id);
        NextTaskDBUtil.deleteNextTaskByHb_id(hb_id);
        WenTiDBUtil.deleteWenTiByHb_id(hb_id);
        //2.将现在页面上写的内容保存到数据库
        //对应数据库中不同表，新建一个存工作汇报的三个子list，方便存到数据库
        //避免单个单次存储反复链接数据库造成不必要资源浪费
        List<NowTask> nowts = new ArrayList<NowTask>();
        List<NextTask> nexts = new ArrayList<NextTask>();
        List<WenTi> wts = new ArrayList<WenTi>();
        for (int i = 0; i < cdtasks.length; i++) {
            NowTask nt = new NowTask();
            nt.setCdtask(cdtasks[i]);
            nt.setXdrq(xdrqs[i]);
            nt.setWcrq(wcrqs[i]);
            nt.setHb_id(hb_id);
            nowts.add(nt);
        }
        for (int i = 0; i < next_cdtasks.length; i++) {
            NextTask nextTask = new NextTask();
            nextTask.setCdtask(next_cdtasks[i]);
            nextTask.setRq(next_rqs[i]);
            nextTask.setZr(next_zrs[i]);
            nextTask.setXtbm(next_xtbm[i]);
            nextTask.setBz(next_bzs[i]);
            nextTask.setHb_id(hb_id);
            nextTask.toString();
            nexts.add(nextTask);
        }
        for (int i = 0; i < q_cdtasks.length; i++) {
            WenTi wenTi = new WenTi();
            wenTi.setCdtask(q_cdtasks[i]);
            wenTi.setZywt(q_zywt[i]);
            wenTi.setDcjjy(q_dcjjy[i]);
            wenTi.setHb_id(hb_id);
            wts.add(wenTi);
        }

        //更新后的工作汇报保存到数据库
        NowTaskDBUtil.saveNowTasks(nowts);
        NextTaskDBUtil.saveNextTasks(nexts);
        WenTiDBUtil.saveWeTis(wts);
        if (2 == hb.st) {
            return "savesuccess";
        } else if (1 == hb.st) {
            return "sendsuccess";
        }
        return "success";
    }

    public String deletehb() {
        //获取页面需要的信息的来源实体
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);
        if (hb.ty == 0) {
            hb.setZyys(EntityUtil.weekNum);
        } else if (1 == hb.ty) {
            hb.setZyys(EntityUtil.paseMonth());
        } else if (2 == hb.ty) {
            hb.setZyys(EntityUtil.paseYear());
        }
        //找到本周期里待发送的汇报
        HuiBao huiBao = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndST(user.getId(), hb.ty, 2);
        int hb_id = huiBao.getId();
        System.out.println("当前要删除的汇报id = " + hb_id);
        //删除时，先删除三个字表中同hb_id的表项
        NowTaskDBUtil.deleteNowTaskByHb_id(hb_id);
        NextTaskDBUtil.deleteNextTaskByHb_id(hb_id);
        WenTiDBUtil.deleteWenTiByHb_id(hb_id);
        HuiBaoDBUtil.deleteHuiBaoById(hb_id);
        return "deletesuccess";
    }

/*************************************这是yifalist.jsp的相关方法*************************************************/
    /**
     * @return
     */
    public String findSenthb() {
        //获取页面需要的信息
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);
        if (hb.ty == 0) {
            hb.setZyys(EntityUtil.weekNum);
        } else if (1 == hb.ty) {
            hb.setZyys(EntityUtil.paseMonth());
        } else if (2 == hb.ty) {
            hb.setZyys(EntityUtil.paseYear());
        }

        //找到本周期里已发送的汇报
        HuiBao huiBao = HuiBaoDBUtil.findHuiBaoByUseridAndTYAndZyysAndSTORST(user.getId(), hb.ty, hb.zyys, 3, 1);
        huiBao.have = 0;
        if (huiBao.getId() != 0) {//证明有汇报
            huiBao.have = 1;
        }

        //根据汇报的类型跳转到相应的待发页面
        //通过在seesion中加入diafahb，来携带diafahb的信息
        if (0 == hb.ty) {
            session.put("hbs", huiBao);
            return "zb";
        } else if (1 == hb.ty) {
            session.put("hbs", huiBao);
            return "yb";
        } else if (2 == hb.ty) {
            session.put("hbs", huiBao);
            return "nb";
        }
        return "success";
    }

    /*************************************这是收件箱的相关方法*************************************************/

    public String inBox() {
        //获取页面需要的信息
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);

        //获取所有种类和
        hbs = HuiBaoDBUtil.findHuiBaoByManagerNameAndSTAndTY(user.getUserName(), 1, hb.ty);
        if (0 == hb.ty) {
            return "zb";
        } else if (1 == hb.ty) {
            return "yb";
        } else if (2 == hb.ty) {
            return "nb";
        }
        return "success";
    }

    /**
     * 批复功能
     *
     * @return
     */
    public String reply() {
        //获取页面需要的信息
        String userName = session.get("name").toString();
        User user = UserDBUtil.findUserByName(userName);
        System.out.println("user =" + user.getId());
        /**
         * 无法获取除当前登录用户以外的其他信息，所以功能无法实现
         * 功能的本质就是将汇报的状态st=3改为已批阅
         */
        System.out.println("hb.ty = "+hb.ty);
        System.out.println("hb.id = "+hb.id);
//        System.out.println("userid=" + hb.user_id);
//        System.out.println("hbTY=" + hb.ty);
//        HuiBao huiBao = HuiBaoDBUtil.findHuiBaoByManagerNameAndSTAndTY(user.getUserName(), hb.ty, 1);
//        int hb_id = huiBao.getId();
//        System.out.println("hb_id =" + hb_id);
//        HuiBaoDBUtil.updateHuiBaoByIDSetST(hb_id, 3);
        return "success";
    }

    public String adminInbox() {
        noreplyzbs = HuiBaoDBUtil.findHuiBaoBySTAndTY(1, 0);
        noreplyybs = HuiBaoDBUtil.findHuiBaoBySTAndTY(1, 1);
        noreplynbs = HuiBaoDBUtil.findHuiBaoBySTAndTY(1, 2);
        return "success";
    }
}
