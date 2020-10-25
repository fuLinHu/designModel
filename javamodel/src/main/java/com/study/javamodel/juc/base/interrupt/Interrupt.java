package com.study.javamodel.juc.base.interrupt;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/2 8:51
 * @Version V1.0
 */
public class Interrupt {


    public static void main(String[] args) throws InterruptedException {
        InterMinnorThread interMinnorThread = new InterMinnorThread();
        interMinnorThread.start();
        Thread.sleep(100);
        interMinnorThread.stop();

    }
}
class InterMinnorThread{
    private Thread minor;
    public void start(){
        minor = new Thread(()->{
            while(true){
                Thread thread = Thread.currentThread();

                boolean interrupted = minor.isInterrupted();
                if(interrupted){
                    System.out.println("处理打断以后的后事--------");
                    break;
                }else{
                    try {
                        Thread.sleep(10000);
                        System.out.println("监控----------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        thread.interrupt();
                    }
                }
            }

        });
        minor.start();
    }
    public void stop(){
        minor.interrupt();
    }
}
