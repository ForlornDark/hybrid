package com.lfm.test.aop;

public class ProxyTarget implements TargetInterface {

    public String execute(String param){
        return "param:"+param;
    }
}
