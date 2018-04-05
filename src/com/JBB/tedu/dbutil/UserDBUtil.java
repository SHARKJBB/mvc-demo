package com.JBB.tedu.dbutil;

import com.JBB.tedu.Util.ConnectionUtil;
import com.JBB.tedu.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//和用户相关的数据库操作
public class UserDBUtil {
    //建议对数据库的操作都是静态方法
    //好处是可以调用
    public static User findUserByName(String userName) {
        User user = new User();
        String sql = "select * from user where name = ?";
        Connection con = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, userName);
            //rs对应的是表格返回的虚表的第一行之前的一行
            ResultSet rs = pre.executeQuery();
            //系统存在bug,如果用户名相同则只返回最后一个用户信息
            /**
             * 解决方案：
             * 1.更改页面为工号登录
             * 2.注册时规定不可使用统一用户名注册
             */
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setGh(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setPow(rs.getInt(6));
                user.setManager_id(rs.getInt(7));
                user.setBm_id(rs.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(con);
        }
        return user;
    }

    public static User findUserByGh(String gh) {
        User user = new User();
        String sql = "select * from user where gh = ?";
        Connection con = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, gh);
            //rs对应的是表格返回的虚表的第一行之前的一行
            ResultSet rs = pre.executeQuery();
            //系统存在bug,如果用户名相同则只返回最后一个用户信息
            /**
             * 解决方案：
             * 信息由管理员录入
             */
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setGh(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setPow(rs.getInt(6));
                user.setManager_id(rs.getInt(7));
                user.setBm_id(rs.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(con);
        }
        return user;
    }

    public static User findUserByID(int id) {
        User user = new User();
        String sql = "select * from user where id = ?";
        Connection con = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //rs对应的是表格返回的虚表的第一行之前的一行
            ResultSet rs = pre.executeQuery();
            //系统存在bug,如果用户名相同则只返回最后一个用户信息
            /**
             * 解决方案：
             * 信息由管理员录入
             */
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setGh(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setPow(rs.getInt(6));
                user.setManager_id(rs.getInt(7));
                user.setBm_id(rs.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(con);
        }
        return user;
    }

    /**
     * 同时根据用户名或工号进行查询
     *
     * @param str//可能是name或gh
     * @return
     */
    public static User findUserByNameOrGH(String str) {
        User user = new User();
        String sql = "select * from user where name = ? or gh = ?";
        Connection con = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, str);
            pre.setString(2, str);
            //rs对应的是表格返回的虚表的第一行之前的一行
            ResultSet rs = pre.executeQuery();
            //系统存在bug,如果用户名相同则只返回最后一个用户信息

            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setGh(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setPow(rs.getInt(6));
                user.setManager_id(rs.getInt(7));
                user.setBm_id(rs.getInt(8));
                System.out.println(user.userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(con);
        }
        return user;
    }

    /**
     * 得到数据库中所有的用户
     */
    public static List<User> findAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "select * from user";
        Connection con = ConnectionUtil.getCurrentConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setGh(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setPow(rs.getInt(6));
                user.setManager_id(rs.getInt(7));
                user.setBm_id(rs.getInt(8));
                users.add(user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;

    }


    /**
     * 添加用户信息
     *
     * @param user
     */
    public static void insertUser(User user) {
        String sql = "insert into user(gh,name,password,phone,pow,manager_id,bm_id) values(?,?,?,?,?,?,?)";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getGh());
            pre.setString(2, user.getUserName());
            pre.setString(3, user.getPassword());
            pre.setString(4, user.getPhone());
            pre.setInt(5, user.getPow());
            pre.setInt(6, user.getManager_id());
            pre.setInt(7, user.getBm_id());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }


    /**
     * 根据ID删除
     *
     * @param id
     */
    public static void deleteByID(int id) {
        String sql = "delete from user where id = ?";
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
     * 根据GH删除
     *
     * @param gh
     */
    public static void deleteByGH(String gh) {
        String sql = "delete from user where gh = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, gh);
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }
    }


    /**
     * 更新----根据GH更新 pwd or phone
     */
    public static void updatePasswordByGH(String gh, String pwd) {
        String sql = "update user set password = ? where gh = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pwd);
            pre.setString(2, gh);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }

    }

    public static void updatePhoneByGH(String gh, String phone) {
        String sql = "update user set phone = ? where gh = ?";
        Connection conn = ConnectionUtil.getCurrentConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, phone);
            pre.setString(2, gh);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(conn);
        }

    }


}
