package com.study.javamodel.juc.objectpool.lock;
import com.study.javamodel.juc.objectpool.Pool;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/11 20:00
 * @Version V1.0
 */
public class ObjectPoolLock implements  Pool<SessionObject> {
    LinkedList<SessionObject> linkedList = new LinkedList();
    ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public SessionObject get(){
        long l = System.currentTimeMillis();
        SessionObject o =null;
        try {
            lock.lock();
            while(linkedList.isEmpty()){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            o = linkedList.removeFirst();
        } finally {
            lock.unlock();
        }
        long l1 = System.currentTimeMillis();
        System.out.println("获取一个链接：剩余："+linkedList.size()+"个===="+(l1-l)+" ms=====>>"+(l1-l)/1000+" s");
        return o;
    }
    @Override
    public void put(SessionObject sessionObject){
        long l = System.currentTimeMillis();
        try {
            lock.lock();
            if(sessionObject!=null){
                linkedList.addLast(sessionObject);
                condition.signal();
            }
           // System.out.println("-------------put--------------------");
        } finally {
            lock.unlock();
        }
        long l1 = System.currentTimeMillis();
        System.out.println("释放一个链接：剩余："+linkedList.size()+"个====》》"+(l1-l)+" ms==="+(l1-l)/1000+" s");
    }

    @Override
    public Integer getCount() {
        return linkedList.size();
    }
}
