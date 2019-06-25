package com.lfm.test.aop;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ProxyHandle implements InvocationHandler {

    private Object target;

    private final Map<String, Method> methods = new HashMap<>();

    public Object bind(Object o){
        this.target = o;
//        Method[] declaredMethods = o.getClass().getDeclaredMethods();
//        for (Method m:declaredMethods){
//            methods.put(m.getName(),m);
//        }
        return Proxy.newProxyInstance(o.getClass().getClassLoader(),o.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info(" start proxy");
        Object invoke = method.invoke(target, args);
        log.info("result:"+invoke.toString());
        log.info("end proxy");
        return invoke;
    }

    public static void main(String[] args) {
        ProxyHandle proxyHandle = new ProxyHandle();
        ProxyTarget target = (ProxyTarget) proxyHandle.bind(new ProxyTarget());
        target.execute("ds");
    }
}
