package com.ysdrzp;

import com.ysdrzp.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Aop 注解方式配置
 */
public class TxTest {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("tx.xml");
        IAccountService as = ac.getBean("accountServiceImpl", IAccountService.class);
        as.transfer("bbb", "ccc" , Float.parseFloat("100"));
    }
}
