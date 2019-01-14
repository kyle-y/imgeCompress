package com.example.xb.imagecompress

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class ProxyHander<T : Any> : InvocationHandler {

    private var action: T? = null
    private var aopAction: AOPAction? = null

    fun bind(action: T, aopAction: AOPAction): T {
        this.action = action
        this.aopAction = aopAction
        return Proxy.newProxyInstance(
            this.action!!.javaClass.classLoader,
            this.action!!.javaClass.interfaces, this
        ) as T
    }

    @Throws(Throwable::class)
    override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? {

        var obj: Any? = null

        aopAction!!.doBefore()
        obj = method.invoke(action, *args)
        aopAction!!.doAfter()
        return obj
    }

}