package com.study.javamodel.javadesignmodel.factory.abstractfactory;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 9:25
 * @Version V1.0
 */
public class HuaWei implements Phone {
    @Override
    public void call() {
        System.out.println("我用huawei在打电话");
    }
}
