package com.ysdrzp.factory;

import com.ysdrzp.service.IAccountService;
import com.ysdrzp.service.impl.AccountServiceImpl;

/**
 * 模拟实例工厂
 * 此工厂创建对象，必须现有工厂实例对象，再调用方法
 */
public class InstanceFactory {

    public IAccountService createAccountService(){
        return new AccountServiceImpl();
    }
}
