package com.example.xb.imagecompress.sample;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class ExampleUnitTest {

    @Test
    public void main(){
        Boss boss = new Boss();
        Mishu mishu = new Mishu(boss);

        mishu.meet();
    }

    @Test
    public void main2(){
        Boss boss = new Boss();

        DongtaiZhixing dongtaiZhixing = new DongtaiZhixing(boss);

        Meeting someOne = (Meeting) Proxy.newProxyInstance(
                boss.getClass().getClassLoader(),
                boss.getClass().getInterfaces(),
                dongtaiZhixing
        );
        someOne.meet();



        DongtaiZhixing2 dongtaiZhixing2 = new DongtaiZhixing2(boss);
        Action someOne2 = (Action) Proxy.newProxyInstance(
                boss.getClass().getClassLoader(),
                boss.getClass().getInterfaces(),
                dongtaiZhixing
        );
        someOne2.play();
    }
}
