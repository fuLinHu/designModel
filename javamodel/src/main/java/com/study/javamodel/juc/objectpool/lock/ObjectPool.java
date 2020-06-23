package com.study.javamodel.juc.objectpool.lock;
import com.study.javamodel.juc.objectpool.Pool;
import java.util.LinkedList;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/11 20:00
 * @Version V1.0
 */
public class ObjectPool implements Pool<SessionObject> {
    LinkedList<SessionObject> linkedList = new LinkedList();
    public SessionObject get(){
        long l = System.currentTimeMillis();
        for(;;){
            if(!linkedList.isEmpty()){
                SessionObject o = linkedList.removeFirst();
                long l1 = System.currentTimeMillis();
                System.out.println("获取一个链接："+(l1-l)+" ms:剩余："+linkedList.size());
                return o;
            }
        }
    }

    public void put(SessionObject sessionObject){
        long l = System.currentTimeMillis();
        if(sessionObject!=null){
            linkedList.addLast(sessionObject);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("释放一个链接："+(l1-l)+" ms:剩余："+linkedList.size());
    }

    @Override
    public Integer getCount() {
        return linkedList.size();
    }

}
