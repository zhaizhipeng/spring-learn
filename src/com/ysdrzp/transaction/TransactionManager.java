package com.ysdrzp.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理器
 */
public class TransactionManager {

    //开启事务
    public static void beginTransaction(Connection connection) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提交事务
    public static void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //回滚事务
    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //释放资源
    public static void release(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
