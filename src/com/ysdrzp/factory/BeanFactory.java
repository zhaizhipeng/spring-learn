package com.ysdrzp.factory;

import com.ysdrzp.service.IAccountService;
import com.ysdrzp.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建客户业务层代理对象工厂
 */
@Component("beanFactory")
public class BeanFactory {

    @Autowired
    private IAccountService accountService;

    @Autowired(required = false)
    private TransactionManager transactionManager;

    /**
     * 创建账户业务层实现类的代理对象
     * @return
     */
    public IAccountService createProxyAccountService() {

        try {
            //创建代理对象
            IAccountService proxyAccountService = (IAccountService) Proxy.newProxyInstance(
                    accountService.getClass().getClassLoader(),
                    accountService.getClass().getInterfaces(),
                    new InvocationHandler() {
                        /**
                         * 执行被代理对象的任何方法，都会经过该方法。
                         * 此处添加事务控制
                         */
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            Object rtValue = null;
                            try {
                                //开启事务
                                transactionManager.beginTransaction();
                                //执行业务层方法
                                rtValue = method.invoke(accountService, args);
                                //提交事务
                                transactionManager.commit();
                            } catch (Exception e) {
                                //回滚事务
                                transactionManager.rollback();
                                e.printStackTrace();
                            } finally {
                                //释放资源
                                transactionManager.release();
                            }
                            return rtValue;
                        }
                    });
            return proxyAccountService;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
