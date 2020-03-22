package com.ysdrzp;

import com.ysdrzp.proxy.IActor;
import com.ysdrzp.proxy.impl.Actor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyClient {

    public static void main(String[] args) {

        final Actor actor = new Actor();

        /**
         * 代理：间接。
         * 获取代理对象
         * 要求: 被代理类最少实现一个接口
         * 创建的方: Proxy.newProxyInstance(三个参数)
         * 参数含义：
         * ClassLoader：和被代理对象使用相同的类加载器。
         * Interfaces：和被代理对象具有相同的行为。实现相同的接口。
         * InvocationHandler：如何代理。
         */
        IActor proxyActor = (IActor) Proxy.newProxyInstance(
                actor.getClass().getClassLoader(),
                actor.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的任何方法，都会经过该方法,此方法有拦截的功能。
                     * 参数：
                     *   proxy：代理对象的引用，不一定每次都用得到
                     *   method：当前执行的方法对象
                     *   args：执行方法所需的参数
                     * 返回值：当前执行方法的返回值
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String name = method.getName();
                        Float money = (Float) args[0];
                        Object rtValue = null;
                        //经纪公司对不同演出收费不一样，此处开始判断
                        if("basicAct".equals(name)){
                            if(money > 2000){
                                //这就是我们没有修改原来 basicAct 方法源码，对方法进行了增强
                                rtValue = method.invoke(actor, money/2);
                            }
                        }
                        if("dangerAct".equals(name)){
                            //危险演出,没有 5000 不演
                            if(money > 5000){
                                //这就是我们没有修改原来 dangerAct 方法源码，对方法进行了增强
                                rtValue = method.invoke(actor, money/2);
                            }
                        }
                        return rtValue;
                    }
                });

            //没有经纪公司的时候，直接找演员。
            actor.basicAct(1000f);
            actor.dangerAct(5000f);

            //剧组无法直接联系演员，而是由经纪公司找的演员
            proxyActor.basicAct(8000f);
            proxyActor.dangerAct(50000f);
    }

}
