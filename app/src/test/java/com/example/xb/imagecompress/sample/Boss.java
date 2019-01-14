package com.example.xb.imagecompress.sample;

public class Boss implements Meeting, Action{
    @Override
    public void meet() {
        System.out.println("老板讲话");
    }

    @Override
    public void play() {
        System.out.println("发奖金");
    }
}
