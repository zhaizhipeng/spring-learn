package com.ysdrzp.c3p0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component("dataSourceConfig")
public class DataSourceConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
