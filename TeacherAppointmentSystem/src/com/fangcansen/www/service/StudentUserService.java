package com.fangcansen.www.service;

import com.fangcansen.www.dao.StudentDao;
import com.fangcansen.www.dao.StudentUserDao;
import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.StudentUser;

import java.sql.SQLException;

/**
 * @author it-fang
 */
public class StudentUserService {
    /**
     * 将学生信息存入数据库并取出其存入数据库时的id
     * @param student
     * @return
     * @throws SQLException
     */
    public int registerStudent(Student student) throws SQLException {
        StudentDao studentDao = new StudentDao();
        studentDao.add(student);
        Student studentID = studentDao.get(student.getNumber());
        return studentID.getId();
    }

    /**
     * 将学生用户信息存入数据库
     * @param studentUser
     * @throws SQLException
     */
    public void registerStudentUser(StudentUser studentUser) throws SQLException {
        StudentUserDao studentUserDao = new StudentUserDao();
        studentUserDao.add(studentUser);
    }
}
