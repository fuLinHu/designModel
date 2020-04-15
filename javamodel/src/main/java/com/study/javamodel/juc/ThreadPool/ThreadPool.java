package com.study.javamodel.juc.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
@Slf4j(topic = "fu")
public class ThreadPool {
    //任务队列
    private BlockingQueue<Runnable> taskQueue;
    //线程集合
    private HashSet<Work> works = new HashSet<>();
    //核心线程数
    private int coreSize;
    //任务的超时时间
    private long timeout;
    private TimeUnit timeUnit;

    public ThreadPool( int coreSize, long timeout, TimeUnit timeUnit,int quecapcity) {
        this.taskQueue = new BlockingQueue<Runnable>(quecapcity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public void exectuce(Runnable task){
        synchronized (works){
            if(works.size()<coreSize){
                Work work = new Work(task);
                works.add(work);
                work.start();
            }else{
                /*taskQueue.put(task);*/
                taskQueue.offer(task,500,TimeUnit.MILLISECONDS);
            }
        }
    }

    class Work extends Thread{
        private Runnable task;
        public Work(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            log.info("线程启动------");
            while(task!=null||(task=taskQueue.get())!=null){
                try{
                    log.info("调用任务---");
                    task.run();
                }catch (Exception  e){
                    e.printStackTrace();
                }finally {
                    task =null;
                }

            }
            /*synchronized (works){
                works.remove(this);
                log.info("======-------删除完以后的长度：："+works.size());
            }*/
        }
    }
}
