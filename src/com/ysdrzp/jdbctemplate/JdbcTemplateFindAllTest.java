package com.ysdrzp.jdbctemplate;

import com.ysdrzp.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTemplateFindAllTest {

    public static void main(String[] args) {
        //1.获取 Spring 容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        //2.根据 id 获取 bean 对象
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
        //3.执行操作
        //查询所有
        List<Account> accounts = jdbcTemplate.query("select * from account where money > ? ", new AccountRowMapper(), 500);
        for(Account o : accounts){
            System.out.println(o);
        }
    }

}
