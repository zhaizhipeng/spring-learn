package com.ysdrzp.dao;

import com.ysdrzp.model.Account;

import java.util.List;

/**
 * 账户持久层接口
 */
public interface IAccountDao {

    /**
     * 保存
     * @param account
     */
    void save(Account account);

    /**
     * 更新
     * @param account
     */
    void update(Account account);

    /**
     * 删除
     * @param accountId
     */
    void delete(Integer accountId);

    /**
     * 根据 id 查询
     * @param accountId
     * @return
     */
    Account findById(Integer accountId);

    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 根据名称
     */
    Account findByName(String name);

}
