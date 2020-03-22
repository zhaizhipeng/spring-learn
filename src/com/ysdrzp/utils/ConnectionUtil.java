package com.ysdrzp.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接管理类
 */
public class ConnectionUtil {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public void setThreadLocal(ThreadLocal<Connection> threadLocal) {
        this.threadLocal = threadLocal;
    }

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection() {
        try{
            //1.先从ThreadLocal上获取
            Connection conn = threadLocal.get();
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3.从数据源中获取一个连接，并且存入ThreadLocal中
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        threadLocal.remove();
    }

}
