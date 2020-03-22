package com.ysdrzp;

import com.ysdrzp.model.Account;
import com.ysdrzp.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 账户 CRUD 测试类
 */
public class AccountServiceTest {

    private ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    private IAccountService as = ac.getBean("accountService",IAccountService.class);

    @Test
    public void testSaveAccount() {
        Account account = new Account();
        account.setName("黑马程序员");
        account.setMoney(100000f);
        as.saveAccount(account);
    }

    @Test
    public void testFindAccountById() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testUpdateAccount() {
        Account account = as.findAccountById(1);
        account.setMoney(20301050f);
        as.updateAccount(account);
    }

    @Test
    public void testDeleteAccount() {
        as.deleteAccount(1);
    }

    @Test
    public void testFindAllAccount() {
        List<Account> list = as.findAllAccount();
        for(Account account : list) {
            System.out.println(account);
        }
    }
}
