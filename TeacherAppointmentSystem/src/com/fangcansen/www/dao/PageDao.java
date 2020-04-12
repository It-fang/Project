package com.fangcansen.www.dao;

import com.fangcansen.www.po.Page;
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
public class PageDao {
    /**
     *
     * @param start
     * @param rows
     * @return
     * @throws SQLException
     */
    public static List<Teacher> findByPage(int start, int rows) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql ="" +
                "select * from teachers limit ? , ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,start);
        preparedStatement.setInt(2,rows);
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
     *
     * @return
     * @throws SQLException
     */
    public static int findTotalCount() throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select count(*) as totalCount from teachers";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int totalCount = 0;
        while(resultSet.next()){
            totalCount = resultSet.getInt("totalCount");
        }
        return totalCount;
    }


}
