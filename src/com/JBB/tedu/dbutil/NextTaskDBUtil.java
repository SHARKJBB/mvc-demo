package com.JBB.tedu.dbutil;

import com.JBB.tedu.Util.ConnectionUtil;
import com.JBB.tedu.entity.NextTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NextTaskDBUtil {
    /**
     * 增加一条下周计划的记录到nexttask里
     *
     * @param nextTasks
     */
    public static void saveNextTasks(List<NextTask> nextTasks) {
        String sql = "insert into nexttask (cdtask,rq,zr,xtbm,bz,hb_id) values(?,?,?,?,?,?)";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            for (NextTask nextTask : nextTasks) {
                pre.setString(1, nextTask.cdtask);
                pre.setString(2, nextTask.rq);
                pre.setString(3, nextTask.zr);
                pre.setString(4, nextTask.xtbm);
                pre.setString(5, nextTask.bz);
                pre.setInt(6, nextTask.hb_id);
                pre.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }

    /**
     * 增加hb_id下的本周主要工作总结的记录
     *
     * @param hb_id
     */
    public static void deleteNextTaskByHb_id(int hb_id) {
        String sql = "delete from nexttask where hb_id  = ? ";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, hb_id);
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }

    public static List<NextTask> findNexttasksByHb_id(int hb_id) {
        String sql = "select * from nexttask where hb_id = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        List<NextTask> nextTaskList = new ArrayList<NextTask>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, hb_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                NextTask nextTask = new NextTask();
                nextTask.setId(rs.getInt("id"));
                nextTask.setCdtask(rs.getString("cdtask"));
                nextTask.setRq(rs.getString("rq"));
                nextTask.setZr(rs.getString("zr"));
                nextTask.setXtbm(rs.getString("xtbm"));
                nextTask.setBz(rs.getString("bz"));
                nextTask.setHb_id(rs.getInt("hb_id"));
                nextTaskList.add(nextTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return nextTaskList;
    }

}
