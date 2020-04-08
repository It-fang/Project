package com.fangcansen.www.dao;

import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.StudentUser;
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
public class StudentUserDao {
    /**
     * 在数据库中增加学生用户对象信息
     * @param studentUser
     * @throws SQLException
     */
    public void add(StudentUser studentUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into studentuser " +
                "(username,password,student_id)   " +
                "value (?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,studentUser.getUsername());
        preparedStatement.setString(2,studentUser.getPassword());
        preparedStatement.setInt(3,studentUser.getStudentId());
        preparedStatement.execute();
    }

    /**
     * 按照学生id在数据库中删除学生用户对象信息
     * @param id
     * @throws SQLException
     */
    public void delete(Integer id) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from studentuser " +
                "where id = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    /**
     * 在数据库中更新学生用户对象信息
     * @param studentUser
     * @throws SQLException
     */
    public void update(StudentUser studentUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update studentuser " +
                "set " +
                "username = ?,password = ?" +
                "where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,studentUser.getUsername());
        preparedStatement.setString(2,studentUser.getPassword());
        preparedStatement.setInt(3,studentUser.getStudentId());
        preparedStatement.execute();
    }

    /**
     * 按id从小到大返回所有学生用户对象信息
     * @return List<StudentUser></>
     * @throws SQLException
     */

    public List<StudentUser> queryAll() throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from studentuser " +
                "order by id";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<StudentUser> studentUsers = new ArrayList<StudentUser>();
        StudentUser studentUser = null;
        while(resultSet.next()){
            studentUser = new StudentUser();
            studentUser.setId(resultSet.getInt("id"));
            studentUser.setUsername(resultSet.getString("username"));
            studentUser.setPassword(resultSet.getString("password"));
            studentUser.setStudentId(resultSet.getInt("student_id"));
            studentUsers.add(studentUser);
        }
        return studentUsers;
    }

    /**
     * 按照学生id来查询学生用户信息
     * @param id
     * @return
     * @throws SQLException
     */
    public StudentUser get(Integer id) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from studentuser where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        StudentUser studentUser = null;
        while(resultSet.next()){
            studentUser = new StudentUser();
            studentUser.setId(resultSet.getInt("id"));
            studentUser.setUsername(resultSet.getString("username"));
            studentUser.setPassword(resultSet.getString("password"));
            studentUser.setStudentId(resultSet.getInt("id"));

        }
        return studentUser;
    }

}
