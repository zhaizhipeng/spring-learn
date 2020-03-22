package com.ysdrzp;

import com.ysdrzp.model.Account;
import com.ysdrzp.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户 CRUD 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:bean.xml"})
public class AccountServiceTest {

    private IAccountService accountService;

    @Test
    public void testSaveAccount() throws SQLException {
        Account account = new Account();
        account.setName("黑马程序员");
        account.setMoney(100000f);
        accountService.saveAccount(account);
    }

    @Test
    public void testFindAccountById() {
        Account account = accountService.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testUpdateAccount() {
        Account account = accountService.findAccountById(1);
        account.setMoney(20301050f);
        accountService.updateAccount(account);
    }

    @Test
    public void testDeleteAccount() {
        accountService.deleteAccount(1);
    }

    @Test
    public void testFindAllAccount() {
        List<Account> list = accountService.findAllAccount();
        for(Account account : list) {
            System.out.println(account);
        }
    }

    @Test
    public void testTransfer(){
        accountService.transfer("bbb", "ccc", Float.parseFloat("100"));
    }
}
