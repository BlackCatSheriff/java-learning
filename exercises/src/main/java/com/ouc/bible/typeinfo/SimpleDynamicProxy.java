package com.ouc.bible.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * SimpleDynamicProxy
 *
 * @author skyUnv
 * created on 2018/8/15 18:42
 */

interface Interface{
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface{

    public void doSomething() {
        System.out.println("doSomething");
    }

    public void somethingElse(String arg) {
        System.out.println("somethingElse: "+arg);
    }
}

class DynamicProxyHandler implements InvocationHandler{
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //可以进行方法过滤
        if(method.getName().equals("somethingElse"))
            return null;
//            System.out.println("catch the method of somethingElse!");
        return  method.invoke(proxied,args);
    }
}

public class SimpleDynamicProxy {
    public static void main(String[] args) {
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),new Class[] {Interface.class},new DynamicProxyHandler(new RealObject()));
        proxy.doSomething();
        proxy.somethingElse("999999");
    }
}
