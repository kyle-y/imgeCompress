package com.example.xb.imagecompress.sample;

public class Mishu implements Meeting{

    private Meeting realPerson;

    public Mishu(Meeting realPerson) {
        this.realPerson = realPerson;
    }

    @Override
    public void meet() {

        //找会议室
        System.out.println("找到了一个会议室");
        //让老板开会
        realPerson.meet();
        //做总结报告
        System.out.println("做总结");
    }


}
