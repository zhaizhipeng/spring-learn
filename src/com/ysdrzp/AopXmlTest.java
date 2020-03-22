package com.ysdrzp;

import com.ysdrzp.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopXmlTest {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        as.transfer("bbb", "ccc" , Float.parseFloat("100"));
    }

}
