package com.fangcansen.www.service;

import com.fangcansen.www.dao.StudentDao;
import com.fangcansen.www.dao.StudentUserDao;
import com.fangcansen.www.dao.TeacherDao;
import com.fangcansen.www.dao.TeacherUserDao;
import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.StudentUser;
import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.po.TeacherUser;

import java.sql.SQLException;

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
     * 将学生用户信息存入数据库
     * @param teacherUser
     * @throws SQLException
     */
    public void registerTeacherUser(TeacherUser teacherUser) throws SQLException {
        TeacherUserDao teacherUserDao = new TeacherUserDao();
        teacherUserDao.add(teacherUser);
    }
}
