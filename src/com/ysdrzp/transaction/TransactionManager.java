package com.ysdrzp.transaction;

import com.ysdrzp.utils.ConnectionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理器
 */
public class TransactionManager {

    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    //开启事务
    public void beginTransaction() {
        try {
            connectionUtil.getThreadConnection().setAutoCommit(false);
            System.out.println("---前置通知---");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提交事务
    public void commit() {
        try {
            connectionUtil.getThreadConnection().commit();
            System.out.println("---后置通知---");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //回滚事务
    public void rollback() {
        try {
            connectionUtil.getThreadConnection().rollback();
            System.out.println("---异常通知---");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //释放资源
    public void release() {
        try {
            connectionUtil.getThreadConnection().close();
            System.out.println("---最终通知---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知
     * @param proceedingJoinPoint
     * spring 框架为我们提供了一个接口：ProceedingJoinPoint，它可以作为环绕通知的方法参数。
     * 在环绕通知执行时，spring 框架会为我们提供该接口的实现类对象，可以直接使用。
     * @return
     */
    public Object transactionAround(ProceedingJoinPoint proceedingJoinPoint) {
        //定义返回值
        Object rtValue = null;
        try {
            //获取方法执行所需的参数
            Object[] args = proceedingJoinPoint.getArgs();
            //前置通知：开启事务
            beginTransaction();
            //执行方法
            rtValue = proceedingJoinPoint.proceed(args);
            //后置通知：提交事务
            commit();
        }catch(Throwable e) {
            //异常通知：回滚事务
            rollback();
            e.printStackTrace();
        }finally {
            //最终通知：释放资源
            release();
        }
        return rtValue;
    }

}
