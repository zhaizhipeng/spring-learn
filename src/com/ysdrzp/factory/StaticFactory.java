package com.ysdrzp.factory;

import com.ysdrzp.service.IAccountService;
import com.ysdrzp.service.impl.AccountServiceImpl;

/**
 * 静态工厂
 */
public class StaticFactory {

    public static IAccountService createAccountService(){
        return new AccountServiceImpl();
    }
}
