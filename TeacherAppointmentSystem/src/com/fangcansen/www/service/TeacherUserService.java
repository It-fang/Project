package com.fangcansen.www.service;

import com.fangcansen.www.dao.*;
import com.fangcansen.www.po.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author it-fang
 */
public class TeacherUserService {
    /**
     * 将老师信息存入数据库并取出其存入数据库时的id
     * @param teacher
     * @return
     * @throws SQLException
     */
    public int registerTeacher(Teacher teacher) throws SQLException {
        TeacherDao teacherDao = new TeacherDao();
        teacherDao.add(teacher);
        Teacher teacherID = teacherDao.get(teacher.getName());
        return teacherID.getId();
    }

    /**
     * 将教师用户信息存入数据库
     * @param teacherUser
     * @throws SQLException
     */
    public void registerTeacherUser(TeacherUser teacherUser) throws SQLException {
        TeacherUserDao teacherUserDao = new TeacherUserDao();
        teacherUserDao.add(teacherUser);
    }

    /**
     *
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    public Page<Teacher> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) throws SQLException {
        //1,创建Page对象
        Page<Teacher> page = new Page<>();
        //2,设置参数
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <= 0){
            currentPage = 1;
        }
        //3,调用Dao查询totalCount
        int totalCount = PageDao.findTotalCount(condition);
        page.setTotalCount(totalCount);
        //4,计算总页码
        int totalPage = totalCount % rows == 0 ? totalCount/rows : totalCount/rows + 1;
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        page.setCurrentPage(currentPage);
        page.setRows(rows);
        page.setTotalPage(totalPage);
        //5,调用Dao查询List集合
        int start = (currentPage - 1)*rows;
        List<Teacher> list =  PageDao.findByPage(start,rows,condition);
        page.setList(list);
        return page;
    }
}
