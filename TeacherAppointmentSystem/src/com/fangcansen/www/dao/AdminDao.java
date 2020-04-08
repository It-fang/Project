package com.fangcansen.www.dao;

import com.fangcansen.www.po.Admin;
import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.TeacherUser;
import com.fangcansen.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    /**
     * 在数据库中增加超级用户账号
     * @param admin
     * @throws SQLException
     */
    public void add(Admin admin) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into admin " +
                "(username,password)   " +
                "value (?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,admin.getUsername());
        preparedStatement.setString(2,admin.getPassword());
        preparedStatement.execute();
    }

    /**
     * 在数据库中删除超级用户账号
     * @param username
     * @throws SQLException
     */
    public void delete(String username) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from admin " +
                "where username = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.execute();
    }

    /**
     * 在数据库中修改超级用户密码
     * @param admin
     * @throws SQLException
     */
    public void update(Admin admin) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update admin " +
                "set " +
                "password = ?" +
                "where username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,admin.getPassword());
        preparedStatement.setString(2,admin.getUsername());
        preparedStatement.execute();
    }

    /**
     * 通过用户名获得超级用户信息
     * @param username
     * @return
     * @throws SQLException
     */
    public Admin get(String username) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from admin where username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        while(resultSet.next()){
            admin = new Admin();
            admin.setId(resultSet.getInt("id"));
            admin.setUsername(resultSet.getString("username"));
            admin.setPassword(resultSet.getString("password"));

        }
        return admin;
    }
}
