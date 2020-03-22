package com.ysdrzp;

import com.ysdrzp.model.Account;
import com.ysdrzp.service.IAccountService;
import config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 纯注解方式测试
 */
public class AccountServiceAnnoTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);

    @Test
    public void testSaveAccount() {
        Account account = new Account();
        account.setName("黑马程序员");
        account.setMoney(100000f);
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        as.saveAccount(account);
    }

    @Test
    public void testFindAccountById() {
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testUpdateAccount() {
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        Account account = as.findAccountById(1);
        account.setMoney(20301050f);
        as.updateAccount(account);
    }

    @Test
    public void testDeleteAccount() {
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        as.deleteAccount(1);
    }

    @Test
    public void testFindAllAccount() {
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        List<Account> list = as.findAllAccount();
        for(Account account : list) {
            System.out.println(account);
        }
    }
}
