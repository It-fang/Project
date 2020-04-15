package com.fangcansen.www.service;

import com.fangcansen.www.dao.ApplicationDao;
import com.fangcansen.www.dao.StudentDao;
import com.fangcansen.www.po.Application;
import com.fangcansen.www.po.Student;

import java.sql.SQLException;
import java.util.List;

public class ApplicationService {
    /**
     *
     * @param application
     * @throws SQLException
     */
    public void add(Application application) throws SQLException {
        ApplicationDao applicationDao = new ApplicationDao();
        applicationDao.add(application);
    }

    /**
     *
     * @param teacherId
     * @return
     * @throws SQLException
     */
    public List<Application> query(String teacherId) throws SQLException {
        ApplicationDao applicationDao = new ApplicationDao();
        List<Application> applications = applicationDao.query(teacherId);
        return applications;
    }

    /**
     *
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Application> query(int studentId) throws SQLException {
        ApplicationDao applicationDao = new ApplicationDao();
        List<Application> applications = applicationDao.query(studentId);
        return applications;
    }
    /**
     *
     * @param studentNumber
     * @param teacherId
     * @throws SQLException
     */
    public void delete(String studentNumber,int teacherId) throws SQLException {
        ApplicationDao applicationDao = new ApplicationDao();
        applicationDao.delete(studentNumber,teacherId);
    }



    public void update(int teacherId, String studentNumber, String ifAgree) throws SQLException {
        ApplicationDao applicationDao = new ApplicationDao();
        applicationDao.update(teacherId,studentNumber,ifAgree);
    }

    public void agreeSelect(int teacherId, String[] studentNumbers) throws SQLException {
        if (studentNumbers != null && studentNumbers.length>0 ){
            for (String studentNumber:studentNumbers){
                ApplicationDao applicationDao = new ApplicationDao();
                applicationDao.update(teacherId,studentNumber);
            }
        }
    }
}
