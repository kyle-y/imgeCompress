package com.example.xb.imagecompress.sample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DongtaiZhixing2 implements InvocationHandler {


    private Meeting realMeet;

    public DongtaiZhixing2(Meeting realMeet) {
        this.realMeet = realMeet;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object obj;

        //找会议室
        System.out.println("找到了一个银行");
        //让老板开会
        obj = method.invoke(realMeet, args);
        //做总结报告
        System.out.println("请吃饭");


        return obj;
    }
}
