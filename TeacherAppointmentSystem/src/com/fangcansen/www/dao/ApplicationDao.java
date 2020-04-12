package com.fangcansen.www.dao;

import com.fangcansen.www.po.Application;
import com.fangcansen.www.util.JdbcUtil;
import javafx.css.converter.LadderConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author it-fang
 */
public class ApplicationDao {
    /**
     *
     * @param application
     * @throws SQLException
     */
    public void add(Application application) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into application " +
                "(teacher_id,teacher_name,student_name,student_number,applytime)   " +
                "value (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,application.getTeacherId());
        preparedStatement.setString(2,application.getTeacherName());
        preparedStatement.setString(3,application.getStudentName());
        preparedStatement.setString(4,application.getStudentNumber());
        preparedStatement.setString(5,application.getApplyTime());
        preparedStatement.execute();
    }

    /**
     *
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
    }
}
