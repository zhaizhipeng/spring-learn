package com.ysdrzp.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDeleteTest {

    public static void main(String[] args) {
        //1.获取 Spring 容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        //2.根据 id 获取 bean 对象
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
        //3.执行操作
        jdbcTemplate.update("delete from account where id = ?",6);
    }

}
