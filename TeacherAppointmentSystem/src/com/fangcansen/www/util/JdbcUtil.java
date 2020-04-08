package com.fangcansen.www.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author it-fang
 */
public class JdbcUtil {
    public static DataSource ds;

    static {

        try {
            //1,加载配置文件
            Properties pro = new Properties();
            pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2,初始化连接词对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获得连接池对象
     * @return DataSource ds
     */
    public static DataSource getDataSource(){
        return ds;
    }
    /**
     * 获得Connection对象
     * @return Connection ds.getConnection
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
