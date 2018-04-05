package com.JBB.tedu.dbutil;

import com.JBB.tedu.Util.ConnectionUtil;
import com.JBB.tedu.entity.Bumen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BuMenDBUtil {
    /**
     * 根据部门id,返回部门信息
     */
    public static Bumen findByID(int id) {
        Bumen bm = new Bumen();
        String sql = "select * from bumen where id=?";
        Connection con = ConnectionUtil.getCurrentConnection();

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs;
            rs = pre.executeQuery();
            while (rs.next()) {
                bm.setId(rs.getInt("id"));
                bm.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bm;
    }

    public static List<Bumen> findAllBumen() {
        ArrayList bmList = new ArrayList();
        String sql = "select * from bumen";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Bumen bm = new Bumen();
                bm.setId(rs.getInt("id"));
                bm.setName(rs.getString("name"));
                bmList.add(bm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return bmList;
    }

    /**
     * @param id
     */
    public static void deleteByID(int id) {
        Bumen bm = new Bumen();
        String sql = "delete from bumen where id = ?";
        Connection con = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(con);
        }
    }

    /**
     * @param bumen
     */
    public static void insertBumen(Bumen bumen) {
        String sql = "insert into bumen values(?)";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bumen.getName());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }

    /**
     *
     * @param id
     * @param name
     */
    public static void updateBumenByID(int id, String name) {
        String sql = "update bumen set name = ? where id = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }
}
