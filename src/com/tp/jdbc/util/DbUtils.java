package com.tp.jdbc.util;

import java.sql.*;
import java.util.ResourceBundle;

public class DbUtils {
    private DbUtils(){}
    //��̬����
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        //������ص�ʱ��ע����������������Ӧ�ó�����˵��ע������ֻ��Ҫִ��һ��
        ResourceBundle bundle = ResourceBundle.getBundle("com.tp.jdbc.jdbc");
        driver = bundle.getString("driver");
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }


    }
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        if(stmt!=null){
            try{
                stmt.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }




}
