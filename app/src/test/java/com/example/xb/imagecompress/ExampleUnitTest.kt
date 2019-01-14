package com.example.xb.imagecompress

import org.junit.Test

import org.junit.Assert.*
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testProxy() {
        var boyAction = BoyAction()


        ProxyHander<Action>().bind(boyAction, object : AOPAction{
            override fun doBefore() {
                System.out.println("准备了游戏机")
            }

            override fun doAfter() {
                System.out.println("玩得好开心")
            }

        }).play()
    }
}
