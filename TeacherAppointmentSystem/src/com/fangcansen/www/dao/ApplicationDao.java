package com.fangcansen.www.dao;

import com.fangcansen.www.po.Application;
import com.fangcansen.www.po.Student;
import com.fangcansen.www.util.JdbcUtil;
import javafx.css.converter.LadderConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author it-fang
 */
public class ApplicationDao {
    /**
     *向数据库中添加申请表数据
     * @param application
     * @throws SQLException
     */
    public void add(Application application) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into application " +
                "(teacher_id,teacher_name,student_id,student_name,student_number,applytime) " +
                "value (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,application.getTeacherId());
        preparedStatement.setString(2,application.getTeacherName());
        preparedStatement.setInt(3,application.getStudentId());
        preparedStatement.setString(4,application.getStudentName());
        preparedStatement.setString(5,application.getStudentNumber());
        preparedStatement.setString(6,application.getApplyTime());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     *将申请表数据从数据库中删除
     * @param id
     * @throws SQLException
     */
    public void delete(Integer id) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from application " +
                "where id = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     *
     * @param number
     * @param teacherId
     * @throws SQLException
     */
    public void delete(String number,int teacherId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from application " +
                "where student_number = ? and teacher_id = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setString(1,number);
        preparedStatement.setInt(2,teacherId);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     *
     * @param application
     * @throws SQLException
     */
    public void update(Application application) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update application " +
                "set " +
                "ifagree = ?" +
                "where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,application.getIfAgree());
        preparedStatement.setInt(2,application.getId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }
    public void update(int teacherId, String studentNumber, String ifAgree) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update application " +
                "set " +
                "ifagree = ?" +
                "where teacher_id = ? and student_number = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,ifAgree);
        preparedStatement.setInt(2,teacherId);
        preparedStatement.setString(3,studentNumber);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     *
     * @param _teacherId
     * @return
     * @throws SQLException
     */
    public List<Application> query(String _teacherId) throws SQLException {
        int teacherId = Integer.parseInt(_teacherId);
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from application where teacher_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,teacherId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Application> applications = new ArrayList<Application>();
        Application application = null;
        while(resultSet.next()){
            application = new Application();
            application.setId(resultSet.getInt("id"));
            application.setTeacherId(resultSet.getInt("teacher_id"));
            application.setTeacherName(resultSet.getString("teacher_name"));
            application.setTeacherId(resultSet.getInt("student_id"));
            application.setStudentName(resultSet.getString("student_name"));
            application.setStudentNumber(resultSet.getString("student_number"));
            application.setApplyTime(resultSet.getString("applytime"));
            application.setIfAgree(resultSet.getString("ifagree"));

            applications.add(application);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return applications;
    }

    /**
     *
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Application> query(int studentId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from application where student_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,studentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Application> applications = new ArrayList<Application>();
        Application application = null;
        while(resultSet.next()){
            application = new Application();
            application.setId(resultSet.getInt("id"));
            application.setTeacherId(resultSet.getInt("teacher_id"));
            application.setTeacherName(resultSet.getString("teacher_name"));
            application.setTeacherId(resultSet.getInt("student_id"));
            application.setStudentName(resultSet.getString("student_name"));
            application.setStudentNumber(resultSet.getString("student_number"));
            application.setApplyTime(resultSet.getString("applytime"));
            application.setIfAgree(resultSet.getString("ifagree"));

            applications.add(application);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return applications;
    }

}
