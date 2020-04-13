package com.fangcansen.www.dao;

import com.fangcansen.www.po.Student;
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
public class StudentDao {
    /**
     * 在数据库中插入学生对象信息
     * @param student
     * @throws SQLException
     */
    public void add(Student student) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into students " +
                "(number,name,sex,college,major,clas)   " +
                "value (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,student.getNumber());
        preparedStatement.setString(2,student.getName());
        preparedStatement.setString(3,student.getSex());
        preparedStatement.setString(4,student.getCollege());
        preparedStatement.setString(5,student.getMajor());
        preparedStatement.setString(6,student.getClas());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 在数据库中删除学生对象信息
     * @param id
     * @throws SQLException
     */
    public void delete(Integer id) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from students " +
                "where id = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 在数据库中更新学生对象信息
     * @param student
     * @throws SQLException
     */
    public void update(Student student) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update students " +
                "set " +
                "number = ?,name = ?,sex = ?,college = ?,major = ?,clas = ?" +
                "where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,student.getNumber());
        preparedStatement.setString(2,student.getName());
        preparedStatement.setString(3,student.getSex());
        preparedStatement.setString(4,student.getCollege());
        preparedStatement.setString(5,student.getMajor());
        preparedStatement.setString(6,student.getClas());
        preparedStatement.setInt(7,student.getId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 按id从小到大返回所有学生对象信息
     * @return  List<Student>
     * @throws SQLException
     */
    public List<Student> queryAll() throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from students " +
                "order by id";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> students = new ArrayList<Student>();
        Student student = null;
        while(resultSet.next()){
            student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setNumber(resultSet.getString("number"));
            student.setName(resultSet.getString("name"));
            student.setSex(resultSet.getString("sex"));
            student.setCollege(resultSet.getString("college"));
            student.setMajor(resultSet.getString("major"));
            student.setClas(resultSet.getString("clas"));

            students.add(student);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return students;
    }

    /**
     * 根据id查找学生对象信息
     * @param number
     * @return Student
     * @throws SQLException
     */
    public Student get(String number) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from students where number = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,number);
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student = null;
        while(resultSet.next()){
            student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setNumber(resultSet.getString("number"));
            student.setName(resultSet.getString("name"));
            student.setSex(resultSet.getString("sex"));
            student.setCollege(resultSet.getString("college"));
            student.setMajor(resultSet.getString("major"));
            student.setClas(resultSet.getString("clas"));
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return student;
    }

}
