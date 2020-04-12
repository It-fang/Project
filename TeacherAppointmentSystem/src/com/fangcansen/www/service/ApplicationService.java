package com.fangcansen.www.service;

import com.fangcansen.www.dao.ApplicationDao;
import com.fangcansen.www.po.Application;

import java.sql.SQLException;

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
}
