package com.ysdrzp;

import com.ysdrzp.collection.MyCollection;
import com.ysdrzp.dao.IAccountDao;
import com.ysdrzp.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        //1.使用 ApplicationContext 接口，就是在获取 spring 容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据 bean 的 id 获取对象
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        System.out.println(accountService);
        IAccountDao accountDao = (IAccountDao) ac.getBean("accountDao");
        System.out.println(accountDao);
        MyCollection myCollection = (MyCollection) ac.getBean("myCollection");
        myCollection.println();
    }
}
