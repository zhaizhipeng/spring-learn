package com.ysdrzp.dao.impl;

import com.ysdrzp.dao.IAccountDao;
import com.ysdrzp.model.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 账户持久层实现
 */
@Component("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void save(Account account) {
        try {
            QueryRunner runner = new QueryRunner(dataSource);
            runner.update("insert into account(name,money)values(?,?)",account.getName(),account.getMoney());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Account account) {
        try {
            QueryRunner runner = new QueryRunner(dataSource);
            runner.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer accountId) {
        try {
            QueryRunner runner = new QueryRunner(dataSource);
            runner.update("delete from account where id=?",accountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(Integer accountId) {
        try {
            QueryRunner runner = new QueryRunner(dataSource);
            return (Account)runner.query("select * from account where id = ?",new BeanHandler<Account>(Account.class),accountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Account> findAll() {
        try {
           QueryRunner runner = new QueryRunner(dataSource);
           return (List<Account>)runner.query("select * from account",new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findByName(String name) {
        try {
            QueryRunner runner = new QueryRunner(dataSource);
            return (Account)runner.query("select * from account where name = ?",new BeanListHandler<Account>(Account.class), name);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
