package com.ysdrzp.c3p0;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component("dataSourceConfig")
public class DataSourceConfig {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection connection;

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public void createConnection() throws SQLException {
        connection = dataSource.getConnection();
    }

    public Connection getConnection(){
        return connection;
    }

}
