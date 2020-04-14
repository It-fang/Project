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
        preparedStatement.setString(5,teacher.getFreetime());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
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
        JdbcUtil.close(preparedStatement,conn);
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
        preparedStatement.setString(5,teacher.getFreetime());
        preparedStatement.setInt(6,teacher.getId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
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
            teacher.setFreetime(resultSet.getString("freetime"));
            teachers.add(teacher);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return teachers;
    }

    /**
     * 根据用户输入的名字或学院查询教师
     * @param term
     * @return
     * @throws SQLException
     */
    public List<Teacher> query(String term) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teachers " +
                "where college = ? or name like ? " +
                "order by id";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,term);
        preparedStatement.setString(2,'%'+term+'%');
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
            teacher.setFreetime(resultSet.getString("freetime"));
            teachers.add(teacher);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return teachers;
    }

    /**
     * 根据name查找老师对象信息
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
            teacher.setFreetime(resultSet.getString("freetime"));
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return teacher;
    }

    /**
     * 根据id查找老师对象信息
     * @param id
     * @return
     */
    public Teacher get(int id) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teachers where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Teacher teacher = null;
        while(resultSet.next()){
            teacher = new Teacher();
            teacher.setId(resultSet.getInt("id"));
            teacher.setName(resultSet.getString("name"));
            teacher.setCollege(resultSet.getString("college"));
            teacher.setMajor(resultSet.getString("major"));
            teacher.setClas(resultSet.getString("clas"));
            teacher.setFreetime(resultSet.getString("freetime"));
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return teacher;
    }

}
