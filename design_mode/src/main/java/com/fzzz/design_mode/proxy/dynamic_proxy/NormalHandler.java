package com.fzzz.design_mode.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class NormalHandler implements InvocationHandler {

    private Object target;

    public NormalHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("man say invoked at " + System.currentTimeMillis());
        method.invoke(target, args);
        return null;
    }
}
