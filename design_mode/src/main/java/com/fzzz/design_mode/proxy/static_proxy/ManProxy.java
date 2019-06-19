package com.fzzz.design_mode.proxy.static_proxy;

import com.fzzz.design_mode.proxy.IPerson;

/**
 * description: 静态代理
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class ManProxy implements IPerson {
    private IPerson target;

    public IPerson getTarget() {
        return target;
    }

    public ManProxy setTarget(IPerson target) {
        this.target = target;
        return this;
    }

    @Override
    public void say() {
        if (null != target) {
            System.out.println("man say at " + System.currentTimeMillis());
            target.say();
        }
    }
}
