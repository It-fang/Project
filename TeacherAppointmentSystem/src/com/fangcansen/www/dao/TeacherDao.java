package com.fangcansen.www.dao;

import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.Teacher;
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
public class TeacherDao {
    /**
     * 在数据库中插入老师对象信息
     * @param teacher
     * @throws SQLException
     */
    public void add(Teacher teacher) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into teachers " +
                "(name,college,major,clas,freetime)   " +
                "value (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,teacher.getName());
        preparedStatement.setString(2,teacher.getCollege());
        preparedStatement.setString(3,teacher.getMajor());
        preparedStatement.setString(4,teacher.getClas());
        preparedStatement.setTime(5,teacher.getFreetime());
        preparedStatement.execute();
    }

    /**
     * 在数据库中删除老师对象信息
     * @param id
     * @throws SQLException
     */
    public void delete(Integer id) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from teachers " +
                "where id = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    /**
     * 在数据库中更新老师对象信息
     * @param teacher
     * @throws SQLException
     */
    public void update(Teacher teacher) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update teachers " +
                "set " +
                "name = ?,college = ?,major = ?,clas = ?,freetime = ?" +
                "where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,teacher.getName());
        preparedStatement.setString(2,teacher.getCollege());
        preparedStatement.setString(3,teacher.getMajor());
        preparedStatement.setString(4,teacher.getClas());
        preparedStatement.setTime(5,teacher.getFreetime());
        preparedStatement.setInt(6,teacher.getId());
        preparedStatement.execute();
    }

    /**
     * 按id从小到大返回所有师生对象信息
     * @return List<Teacher>
     * @throws SQLException
     */
    public List<Teacher> queryAll() throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teachers " +
                "order by id";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Teacher> teachers = new ArrayList<Teacher>();
        Teacher teacher = null;
        while(resultSet.next()){
            teacher = new Teacher();
            teacher.setId(resultSet.getInt("id"));
            teacher.setName(resultSet.getString("name"));
            teacher.setCollege(resultSet.getString("college"));
            teacher.setMajor(resultSet.getString("major"));
            teacher.setClas(resultSet.getString("clas"));
            teacher.setFreetime(resultSet.getTime("freetime"));
            teachers.add(teacher);
        }
        return teachers;
    }

    /**
     * 根据id查找老师对象信息
     * @param name
     * @return Teacher
     * @throws SQLException
     */
    public Teacher get(String name) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teachers where name = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        Teacher teacher = null;
        while(resultSet.next()){
            teacher = new Teacher();
            teacher.setId(resultSet.getInt("id"));
            teacher.setName(resultSet.getString("name"));
            teacher.setCollege(resultSet.getString("college"));
            teacher.setMajor(resultSet.getString("major"));
            teacher.setClas(resultSet.getString("clas"));
            teacher.setFreetime(resultSet.getTime("freetime"));
        }
        return teacher;
    }

}
