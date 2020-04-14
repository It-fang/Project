package com.fangcansen.www.dao;

import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author it-fang
 */
public class PageDao {
    /**
     *分页条件查询
     * @param start
     * @param rows
     * @param condition
     * @return
     * @throws SQLException
     */
    public static List<Teacher> findByPage(int start, int rows, Map<String, String[]> condition) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql ="" +
                "select * from teachers where 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        Set<String> keyset = condition.keySet();
        int n = 0;
        List<String> params = new ArrayList<String>();
        for(String key:keyset){
            if ("currentPage".equals(key) || "rows".equals(key) || "studentId".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)){
                stringBuilder.append(" and "+key + " like ?");
                n++;
                params.add(value);
            }
        }
        stringBuilder.append(" limit ? , ?");
        PreparedStatement preparedStatement = conn.prepareStatement(String.valueOf(stringBuilder));
        int i = 0;
        for (i = 1; i <= n ; i++){
            preparedStatement.setString(i,"%" + params.get(i-1) + "%");
        }
        preparedStatement.setInt(i,start);
        preparedStatement.setInt(i+1,rows);
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
     *查询总条数
     * @return
     * @throws SQLException
     * @param condition
     */
    public static int findTotalCount(Map<String, String[]> condition) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select count(*) as totalCount from teachers where 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        Set<String> keyset = condition.keySet();
        int n = 0;
        List<String> params = new ArrayList<String>();
        for(String key:keyset){
            if ("currentPage".equals(key) || "rows".equals(key) || "studentId".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)){
                stringBuilder.append(" and "+key + " like ?");
                n++;
                params.add(value);
            }
        }
        PreparedStatement preparedStatement = conn.prepareStatement(String.valueOf(stringBuilder));
        for (int i = 1; i <= n ; i++){
            preparedStatement.setString(i,"%" + params.get(i-1) + "%");
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        int totalCount = 0;
        while(resultSet.next()){
            totalCount = resultSet.getInt("totalCount");
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return totalCount;
    }


}
