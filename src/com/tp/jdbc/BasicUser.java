package com.tp.jdbc;
import com.tp.jdbc.util.DbUtils;
import java.sql.*;

public class BasicUser implements IUser {
    private boolean flag;
    private String username;
    private String password;
    public BasicUser(String username,String password){
        this.username = username;
        this.password = password;
    }
    @Override//注册
    public void register() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try{
            con = DbUtils.getConnection();
            String sql = "INSERT IGNORE INTO BasicUsers (username, password) VALUES (?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            int rowsInsert = ps.executeUpdate();
            if (rowsInsert > 0){
                System.out.println("恭喜您注册成功！");
                this.flag = true;
            }else{
                System.out.println("不好意思,您的用户名已经有人注册过了呢,请您再想一个注册名");
                this.flag = false;
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            DbUtils.close(con,ps,res);
        }

    }

    @Override
    public void login() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try{
            con = DbUtils.getConnection();
            String sql = "SELECT password From BasicUsers WHERE username = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            res = ps.executeQuery();
            if (res.next()){
                String storedPassword = res.getString("password");
                if (password.equals(storedPassword)){
                    System.out.println("登录成功");
                    this.flag = true;
                }else{
                    System.out.println("密码错误,登录失败,请重新登录");
                    this.flag = false;
                }
            }


        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            DbUtils.close(con,ps,res);
        }
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean getflage() {return flag;}

}
