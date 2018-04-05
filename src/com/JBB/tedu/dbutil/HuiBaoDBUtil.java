package com.JBB.tedu.dbutil;

import com.JBB.tedu.Util.ConnectionUtil;
import com.JBB.tedu.entity.HuiBao;
import com.JBB.tedu.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuiBaoDBUtil {

    /**
     * @param st
     * @param ty
     * @return
     */
    public static List<HuiBao> findHuiBaoBySTAndTY(int st, int ty) {
        String sql = "select * from hb where st = ? and ty = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        List<HuiBao> hbs = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, st);
            pre.setInt(2, ty);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                HuiBao hb = new HuiBao();
                hb.setId(rs.getInt("id"));
                hb.setTy(rs.getInt("ty"));
                hb.setRq(rs.getString("rq"));
                hb.setZyys(rs.getInt("zyys"));
                hb.setBm_name(rs.getString("bm_name"));
                hb.setM_name(rs.getString("m_name"));
                hb.setCsren(rs.getString("csren"));
                hb.setUser_id(rs.getInt("user_id"));
                hb.setSt(rs.getInt("st"));
                hbs.add(hb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return hbs;
    }


    /**
     * @param user_id
     * @param st
     * @return
     */
    public static List<HuiBao> findHuiBaoByUseridAndST(int user_id, int st) {
        String sql = "select * from hb where st = ? and user_id = ?";
        ArrayList<HuiBao> hbs = new ArrayList<>();
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, st);
            pre.setInt(2, user_id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                HuiBao hb = new HuiBao();
                hb.setId(rs.getInt("id"));
                hb.setTy(rs.getInt("ty"));
                hb.setRq(rs.getString("rq"));
                hb.setZyys(rs.getInt("zyys"));
                hb.setBm_name(rs.getString("bm_name"));
                hb.setM_name(rs.getString("m_name"));
                hb.setCsren(rs.getString("csren"));
                hb.setUser_id(rs.getInt("user_id"));
                hb.setSt(rs.getInt("st"));
                hbs.add(hb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return hbs;
    }

    /**
     * @param m_name
     * @param st
     * @return
     */
    public static List<HuiBao> findHuiBaoByManagerNameAndSTAndTY(String m_name, int st, int ty) {
        String sql = "select * from hb where st = ? and m_name = ? and ty = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        List<HuiBao> huiBaoList = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, st);
            pre.setString(2, m_name);
            pre.setInt(3, ty);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                HuiBao hb = new HuiBao();
                hb.setId(rs.getInt("id"));
                hb.setTy(rs.getInt("ty"));
                hb.setRq(rs.getString("rq"));
                hb.setZyys(rs.getInt("zyys"));
                hb.setBm_name(rs.getString("bm_name"));
                hb.setM_name(rs.getString("m_name"));
                hb.setCsren(rs.getString("csren"));
                hb.setUser_id(rs.getInt("user_id"));
                hb.setSt(rs.getInt("st"));
                huiBaoList.add(hb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return huiBaoList;
    }

    /**
     * 通过Userid TY ST唯一确定一个
     *
     * @param user_id
     * @param ty
     * @param st
     * @return
     */
    public static HuiBao findHuiBaoByUseridAndTYAndST(int user_id, int ty, int st) {
        String sql = "select * from hb where ty = ? and user_id = ? and st = ?";
        HuiBao hb = new HuiBao();
        ArrayList<HuiBao> hbs = new ArrayList<>();
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ty);
            pre.setInt(2, user_id);
            pre.setInt(3, st);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                hb.setId(rs.getInt("id"));
                hb.setTy(rs.getInt("ty"));
                hb.setRq(rs.getString("rq"));
                hb.setZyys(rs.getInt("zyys"));
                hb.setBm_name(rs.getString("bm_name"));
                hb.setM_name(rs.getString("m_name"));
                hb.setCsren(rs.getString("csren"));
                hb.setUser_id(rs.getInt("user_id"));
                hb.setSt(rs.getInt("st"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return hb;
    }

    /**
     * 根据用户汇报类型和汇报周期唯一确定用户的一条汇报数据
     *
     * @param user_id
     * @param ty
     * @param zyys
     * @return
     */
    public static HuiBao findHuiBaoByUseridAndTYAndZyys(int user_id, int ty, int zyys) {
        String sql = "select * from hb where ty = ? and user_id = ? and zyys = ?";
        HuiBao hb = new HuiBao();
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ty);
            pre.setInt(2, user_id);
            pre.setInt(3, zyys);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                hb.setId(rs.getInt("id"));
                hb.setTy(rs.getInt("ty"));
                hb.setRq(rs.getString("rq"));
                hb.setZyys(rs.getInt("zyys"));
                hb.setBm_name(rs.getString("bm_name"));
                hb.setM_name(rs.getString("m_name"));
                hb.setCsren(rs.getString("csren"));
                hb.setUser_id(rs.getInt("user_id"));
                hb.setSt(rs.getInt("st"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return hb;
    }

    /**
     * 根据用户汇报类型和汇报周期唯一确定用户的一条汇报数据
     *
     * @param user_id
     * @param ty
     * @param zyys
     * @return
     */
    public static HuiBao findHuiBaoByUseridAndTYAndZyysAndSTORST(int user_id, int ty, int zyys, int st, int st2) {
        String sql = "select * from hb where ty = ? and user_id = ? and zyys = ? and (st = ? or st = ?) ";
        HuiBao hb = new HuiBao();
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ty);
            pre.setInt(2, user_id);
            pre.setInt(3, zyys);
            pre.setInt(4, st);
            pre.setInt(5, st2);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                hb.setId(rs.getInt("id"));
                hb.setTy(rs.getInt("ty"));
                hb.setRq(rs.getString("rq"));
                hb.setZyys(rs.getInt("zyys"));
                hb.setBm_name(rs.getString("bm_name"));
                hb.setM_name(rs.getString("m_name"));
                hb.setCsren(rs.getString("csren"));
                hb.setUser_id(rs.getInt("user_id"));
                hb.setSt(rs.getInt("st"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return hb;
    }

    /**
     * 根据汇报类型，汇报状态，汇报周期，查找未发汇报
     * 每周期末发汇报在超级管理员用户登录时自动更新
     *
     * @return
     */
    public static List<HuiBao> findWFHB(int ty, int zyys, int st) {
        String sql = "select * from hb where ty = ? and zyys = ? and st = ?";
        ArrayList<HuiBao> hbs = new ArrayList<>();
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ty);
            pre.setInt(2, zyys);
            pre.setInt(3, st);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                HuiBao hb = new HuiBao();
                hb.setId(rs.getInt("id"));
                hb.setTy(rs.getInt("ty"));
                hb.setRq(rs.getString("rq"));
                hb.setZyys(rs.getInt("zyys"));
                hb.setBm_name(rs.getString("bm_name"));
                hb.setM_name(rs.getString("m_name"));
                hb.setCsren(rs.getString("csren"));
                hb.setUser_id(rs.getInt("user_id"));
                hb.setSt(rs.getInt("st"));
                hbs.add(hb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
        return hbs;
    }

    /**
     * 添加
     */
    public static void insertHuiBao(HuiBao hb) {
        String sql = "insert into hb (ty,rq,zyys,bm_name,m_name,csren,user_id,st) values(?,?,?,?,?,?,?,?)";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, hb.getTy());
            pre.setString(2, hb.getRq());
            pre.setInt(3, hb.getZyys());
            pre.setString(4, hb.getBm_name());
            pre.setString(5, hb.getM_name());
            pre.setString(6, hb.getCsren());
            pre.setInt(7, hb.getUser_id());
            pre.setInt(8, hb.getSt());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }

    /**
     * /删除
     */
    public static void deleteHuiBaoById(int id) {
        String sql = "delete from hb where id  = ? ";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }

    /**
     * 应用于管理员一键删除所有未发汇报列表
     *
     * @param st
     */
    public static void deleteHuiBaoByST(int st) {
        String sql = "delete from hb where st  = ? ";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, st);
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }

    /**
     * 更新
     */
    public static void updateHuiBaoByIDSetST(int id, int st) {
        String sql = "update hb set st  = ? where id = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            System.out.println("st=" + st + "\nid" + id);
            pre.setInt(1, st);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }
}
