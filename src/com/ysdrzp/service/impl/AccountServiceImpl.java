package com.ysdrzp.service.impl;

import com.ysdrzp.dao.IAccountDao;
import com.ysdrzp.model.Account;
import com.ysdrzp.service.IAccountService;

import java.util.List;

/**
 * 账户业务层实现
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.delete(accountId);
    }

    @Override
    public Account findAccountById(Integer accountId) {
        Account account = null;
        account = accountDao.findById(accountId);
        return account;
    }

    @Override
    public List<Account> findAllAccount() {
        List<Account> accounts = null;
        accounts = accountDao.findAll();
        return accounts;
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        Account source = accountDao.findByName(sourceName);
        Account target = accountDao.findByName(targetName);
        source.setMoney(source.getMoney()-money);
        target.setMoney(target.getMoney()+money);
        accountDao.update(source);
        //int i=1/0;
        accountDao.update(target);
    }

}
