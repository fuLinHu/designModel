package com.study.javamodel.juc.base.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class cas {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        atomicInteger.updateAndGet(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand*10;
            }
        });
        atomicInteger.updateAndGet(x->x*10);
        System.out.println(atomicInteger.get());


    }
}

