package com.ysdrzp.service.impl;

import com.ysdrzp.dao.IAccountDao;
import com.ysdrzp.model.Account;
import com.ysdrzp.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账户业务层实现
 */
@Service
@Transactional(readOnly=true,propagation= Propagation.SUPPORTS)
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

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
    public List<Account> findAllAccount() {
        List<Account> accounts = null;
        accounts = accountDao.findAll();
        return accounts;
    }

    @Override
    public Account findAccountById(Integer accountId) {
        Account account = null;
        account = accountDao.findById(accountId);
        return account;
    }

    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    public void transfer(String sourceName, String targetName, Float money) {
        Account source = accountDao.findAccountByName(sourceName);
        System.out.println("source:"+source);
        Account target = accountDao.findAccountByName(targetName);
        System.out.println("target:"+target);
        source.setMoney(source.getMoney()-money);
        target.setMoney(target.getMoney()+money);
        accountDao.updateAccount(source);
        int i=1/0;
        accountDao.updateAccount(target);
    }

}
