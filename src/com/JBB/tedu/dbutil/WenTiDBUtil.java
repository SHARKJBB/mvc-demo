package com.JBB.tedu.dbutil;

import com.JBB.tedu.Util.ConnectionUtil;
import com.JBB.tedu.entity.NowTask;
import com.JBB.tedu.entity.WenTi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类中将记录关于wenti表的操作
 */
public class WenTiDBUtil {
    /**
     * 增加一条问题记录
     *
     * @param wenTis
     */
    public static void saveWeTis(List<WenTi> wenTis) {
        String sql = "insert into wenti (cdtask,zywt,dcjjy,hb_id) values(?,?,?,?)";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            for (WenTi wenTi : wenTis) {
                pre.setString(1, wenTi.cdtask);
                pre.setString(2, wenTi.zywt);
                pre.setString(3, wenTi.dcjjy);
                pre.setInt(4, wenTi.hb_id);
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
    public static void deleteWenTiByHb_id(int hb_id) {
        String sql = "delete from wenti where hb_id  = ? ";
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
    public static List<WenTi> findAllWenTiByHb_id(int hb_id) {
        String sql = "select * from wenti where hb_id = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        List<WenTi> wenTiList = new ArrayList<WenTi>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, hb_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                WenTi wenTi = new WenTi();
                wenTi.setId(rs.getInt("id"));
                wenTi.setCdtask(rs.getString("cdtask"));
                wenTi.setZywt(rs.getString("zywt"));
                wenTi.setDcjjy(rs.getString("dcjjy"));
                wenTi.setHb_id(rs.getInt("hb_id"));
                wenTiList.add(wenTi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return wenTiList;

    }


}
