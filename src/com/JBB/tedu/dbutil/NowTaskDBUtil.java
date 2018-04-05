package com.JBB.tedu.dbutil;

import com.JBB.tedu.Util.ConnectionUtil;
import com.JBB.tedu.entity.NextTask;
import com.JBB.tedu.entity.NowTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NowTaskDBUtil {

    /**
     * 增加一条本周主要工作总结的记录到nowtask里
     *
     * @param nowTasks
     */
    public static void saveNowTasks(List<NowTask> nowTasks) {
        String sql = "insert into nowtask (cdtask,xdrq,wcrq,hb_id) values(?,?,?,?)";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            for (NowTask nowTask : nowTasks) {
                pre.setString(1, nowTask.cdtask);
                pre.setString(2, nowTask.xdrq);
                pre.setString(3, nowTask.wcrq);
                pre.setInt(4, nowTask.hb_id);
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
    public static void deleteNowTaskByHb_id(int hb_id) {
        String sql = "delete from nowtask where hb_id  = ? ";
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

    /**
     * @return
     */
    public static List<NowTask> findNowTasksByHb_id(int hb_id) {
        String sql = "select * from nowtask where hb_id = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        List<NowTask> nowTaskList = new ArrayList<NowTask>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, hb_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                NowTask nowTask = new NowTask();
                nowTask.setId(rs.getInt("id"));
                nowTask.setCdtask(rs.getString("cdtask"));
                nowTask.setXdrq(rs.getString("xdrq"));
                nowTask.setWcrq(rs.getString("wcrq"));
                nowTask.setHb_id(rs.getInt("hb_id"));
                nowTaskList.add(nowTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return nowTaskList;
    }


}