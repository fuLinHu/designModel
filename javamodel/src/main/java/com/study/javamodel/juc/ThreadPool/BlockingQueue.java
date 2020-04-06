package com.study.javamodel.juc.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class BlockingQueue<T> {
    //线程队列
    private Deque<T> deque = new ArrayDeque<>();
    //锁
    private ReentrantLock lock = new ReentrantLock();
    //消费者等待条件
    private Condition customcondition = lock.newCondition();
    //生产者等待条件
    private Condition productcondition = lock.newCondition();
    //容量
    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    public T poll(long timeOut, TimeUnit timeUnit){
        lock.lock();
        try{
            long nanos = timeUnit.toNanos(timeOut);
            while(deque.isEmpty()){
                try {
                    if(nanos<=0){
                        throw new RuntimeException("获取超时！！！");
                    }
                    //返回的是剩余的时间
                    nanos = customcondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
        T t = deque.removeFirst();
        productcondition.signal();
        return t;

    }
    public T get(){
        lock.lock();
        try{
            log.info("查看队列长度，看是否为空，如果为空则等待获取。【"+deque.size()+"】");
            while(deque.isEmpty()){
                log.info("队列长度，为空，则等待获取。【"+deque.size()+"】");
                try {
                    customcondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("队列长度不为空，则获取。【"+deque.size()+"】");
            T t = deque.removeFirst();
            productcondition.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    public void put(T t){
        log.info("将多余的任务添加到任务队列中");
        lock.lock();
        try{
            while(deque.size()>=capcity){
                log.info("队列长度大于容许容量的时候，等待。。。随后在加入。【"+deque.size()+"】");
                try {
                    productcondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("队列长度小于于容许容量的时候，加入。【"+deque.size()+"】");
            deque.addLast(t);
            customcondition.signal();
        }finally {
            lock.unlock();
        }

    }
    public boolean offer(T t,long timeout,TimeUnit timeUnit){
        log.info("将多余的任务添加到任务队列中");
        lock.lock();
        try{
            long nanos = timeUnit.toNanos(timeout);
            while(deque.size()>=capcity){
                log.info("队列长度大于容许容量的时候，等待。。。随后在加入。【"+deque.size()+"】");
                try {
                    if(nanos<=0){
                        return false;
                    }
                    nanos=productcondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("队列长度小于于容许容量的时候，加入。【"+deque.size()+"】");
            deque.addLast(t);
            customcondition.signal();
        }finally {
            lock.unlock();
        }
        return true;
    }
    public int getSize(){
        lock.lock();
        try{
           return deque.size();
        }finally {
            lock.unlock();
        }

    }






}
