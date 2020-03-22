package com.ysdrzp.service.impl;

import com.ysdrzp.c3p0.DataSourceConfig;
import com.ysdrzp.dao.IAccountDao;
import com.ysdrzp.model.Account;
import com.ysdrzp.service.IAccountService;
import com.ysdrzp.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

/**
 * 账户业务层实现
 */
@Component("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Override
    public void saveAccount(Account account) {
        Connection connection = null;
        try {
            connection = dataSourceConfig.getConnection();
            TransactionManager.beginTransaction(connection);
            accountDao.save(account);
            TransactionManager.commit(connection);
        } catch (Exception e) {
            TransactionManager.rollback(connection);
            e.printStackTrace();
        }finally {
            TransactionManager.release(connection);
        }
    }

    @Override
    public void updateAccount(Account account) {
        Connection connection = null;
        try {
            connection = dataSourceConfig.getConnection();
            TransactionManager.beginTransaction(connection);
            accountDao.update(account);
            TransactionManager.commit(connection);
        } catch (Exception e) {
            TransactionManager.rollback(connection);
            e.printStackTrace();
        }finally {
            TransactionManager.release(connection);
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        Connection connection = null;
        try {
            connection = dataSourceConfig.getConnection();
            TransactionManager.beginTransaction(connection);
            accountDao.delete(accountId);
            TransactionManager.commit(connection);
        } catch (Exception e) {
            TransactionManager.rollback(connection);
            e.printStackTrace();
        }finally {
            TransactionManager.release(connection);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        Account account = null;
        Connection connection = null;
        try {
            connection = dataSourceConfig.getConnection();
            TransactionManager.beginTransaction(connection);
            account = accountDao.findById(accountId);
            TransactionManager.commit(connection);
            return account;
        } catch (Exception e) {
            TransactionManager.rollback(connection);
            e.printStackTrace();
        }finally {
            TransactionManager.release(connection);
        }
        return null;
    }

    @Override
    public List<Account> findAllAccount() {
        List<Account> accounts = null;
        Connection connection = null;
        try {
            connection = dataSourceConfig.getConnection();
            TransactionManager.beginTransaction(connection);
            accounts = accountDao.findAll();
            TransactionManager.commit(connection);
            return accounts;
        } catch (Exception e) {
            TransactionManager.rollback(connection);
            e.printStackTrace();
        }finally {
            TransactionManager.release(connection);
        }
        return null;
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        Connection connection = null;
        try {
            connection = dataSourceConfig.getConnection();
            TransactionManager.beginTransaction(connection);
            Account source = accountDao.findByName(sourceName);
            Account target = accountDao.findByName(targetName);
            source.setMoney(source.getMoney()-money);
            target.setMoney(target.getMoney()+money);
            accountDao.update(source);
            int i=1/0;
            accountDao.update(target);
            TransactionManager.commit(connection);
        } catch (Exception e) {
            TransactionManager.rollback(connection);
            e.printStackTrace();
        }finally {
            TransactionManager.release(connection);
        }
    }

}
