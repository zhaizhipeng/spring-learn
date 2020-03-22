package com.ysdrzp.dao.impl;

import com.ysdrzp.dao.IAccountDao;
import com.ysdrzp.model.Account;
import com.ysdrzp.utils.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * 账户持久层实现
 */
public class AccountDaoImpl implements IAccountDao {

    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public void save(Account account) {
        try {
            QueryRunner runner = new QueryRunner();
            runner.update(connectionUtil.getThreadConnection(),"insert into account(name,money)values(?,?)",account.getName(),account.getMoney());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Account account) {
        try {
            QueryRunner runner = new QueryRunner();
            runner.update(connectionUtil.getThreadConnection(),"update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer accountId) {
        try {
            QueryRunner runner = new QueryRunner();
            runner.update(connectionUtil.getThreadConnection(),"delete from account where id=?",accountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(Integer accountId) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.query(connectionUtil.getThreadConnection(),"select * from account where id = ?",new BeanHandler<Account>(Account.class),accountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> findAll() {
        try {
           QueryRunner runner = new QueryRunner();
           return runner.query(connectionUtil.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findByName(String name) {
        try {
            QueryRunner runner = new QueryRunner();
            return runner.query(connectionUtil.getThreadConnection(),"select * from account where name = ?",new BeanHandler<Account>(Account.class), name);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
