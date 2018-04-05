package com.JBB.tedu.Util;

import java.sql.*;

import com.mysql.jdbc.Statement;

public class ConnectionUtil {

    static String user = "username";
    static String pwd = "password";
    static String url = "jdbc:mysql://IP:port/datebase";
    static String driver = "com.mysql.jdbc.Driver";

public static void main(String[] args) {
    getCurrentConnection();
}
    public static Connection getCurrentConnection(){
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,pwd);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }
    public static void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void close(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
