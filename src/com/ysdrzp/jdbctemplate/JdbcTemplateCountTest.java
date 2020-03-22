package com.ysdrzp.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateCountTest {

    public static void main(String[] args) {
        //1.获取 Spring 容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        //2.根据 id 获取 bean 对象
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
        //3.执行操作
        //查询返回一行一列：使用聚合函数，在不使用 group by 字句时，都是返回一行一列。最长用的就是分页中获取总记录条数
        Integer total = jdbcTemplate.queryForObject("select count(*) from account where money > ? ",Integer.class,500);
        System.out.println(total);
    }
}
