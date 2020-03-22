package com.ysdrzp.dao.impl;

import com.ysdrzp.dao.IAccountDao;
import com.ysdrzp.jdbctemplate.AccountRowMapper;
import com.ysdrzp.model.Account;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class AccountDaoSupportImpl extends JdbcDaoSupport implements IAccountDao {

    @Override
    public void save(Account account) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Integer accountId) {

    }

    @Override
    public Account findById(Integer accountId) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account findByName(String name) {
        return null;
    }

    @Override
    public Account findAccountById(Integer id) {
        List<Account> list = getJdbcTemplate().query("select * from account where id = ? ",new AccountRowMapper(),id);
        return list.isEmpty()?null:list.get(0);
    }
    @Override
    public Account findAccountByName(String name) {
        List<Account> list = getJdbcTemplate().query("select * from account where name = ? ",new AccountRowMapper(),name);
        if(list.isEmpty()){
            return null;
        }
        if(list.size()>1){
            throw new RuntimeException("结果集不唯一，不是只有一个账户对象");
        }
        return list.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        getJdbcTemplate().update("update account set money = ? where id = ? ",account.getMoney(),account.getId());
    }

}
