package com.fangcansen.www.dao;

import com.fangcansen.www.po.StudentUser;
import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.po.TeacherUser;
import com.fangcansen.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author it-fang
 */
public class TeacherUserDao {
    /**
     * 在数据库中增加老师用户对象信息
     * @param teacherUser
     * @throws SQLException
     */
    public void add(TeacherUser teacherUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into teacheruser " +
                "(username,password,teacher_id)   " +
                "value (?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,teacherUser.getUsername());
        preparedStatement.setString(2,teacherUser.getPassword());
        preparedStatement.setInt(3,teacherUser.getTeacherId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 按照老师id在数据库中删除老师用户对象信息
     * @param id
     * @throws SQLException
     */
    public void delete(Integer id) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from teacheruser " +
                "where id = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 在数据库中更新老师用户对象信息
     * @param teacherUser
     * @throws SQLException
     */
    public void update(TeacherUser teacherUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update teacheruser " +
                "set " +
                "username = ?,password = ?" +
                "where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,teacherUser.getUsername());
        preparedStatement.setString(2,teacherUser.getPassword());
        preparedStatement.setInt(3,teacherUser.getTeacherId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 按id从小到大返回所有老师用户对象信息
     * @return
     * @throws SQLException
     */
    public List<TeacherUser> queryAll() throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teacheruser " +
                "order by id";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<TeacherUser> teacherUsers = new ArrayList<TeacherUser>();
        TeacherUser teacherUser = null;
        while(resultSet.next()){
            teacherUser = new TeacherUser();
            teacherUser.setId(resultSet.getInt("id"));
            teacherUser.setUsername(resultSet.getString("username"));
            teacherUser.setPassword(resultSet.getString("password"));
            teacherUser.setTeacherId(resultSet.getInt("teacher_id"));
            teacherUsers.add(teacherUser);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return teacherUsers;
    }

    /**
     * 按照老师id来查询老师用户信息
     * @param username
     * @return
     * @throws SQLException
     */
    public TeacherUser get(String username) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teacheruser where username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        TeacherUser teacherUser = null;
        while(resultSet.next()){
            teacherUser = new TeacherUser();
            teacherUser.setId(resultSet.getInt("id"));
            teacherUser.setUsername(resultSet.getString("username"));
            teacherUser.setPassword(resultSet.getString("password"));
            teacherUser.setTeacherId(resultSet.getInt("teacher_id"));
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return teacherUser;
    }
}
