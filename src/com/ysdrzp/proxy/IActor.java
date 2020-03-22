package com.ysdrzp.proxy;

/**
 * 经济公司要求：
 * 能做基本演出和危险演出
 */
public interface IActor {

    /**
     * 基本演出
     * @param money
     */
    void basicAct(float money);

    /**
     * 危险演出
     * @param money
     */
    void dangerAct(float money);

}
