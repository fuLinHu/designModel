package com.study.javamodel.nio;

import java.nio.ByteBuffer;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/26 11:46
 * @Version V1.0
 */
public class Buffer {
    public static void main(String[] args) {
        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
        int limit = byteBuffer.limit();
        int capacity = byteBuffer.capacity();
        int position = byteBuffer.position();
        //java.nio.Buffer mark = put.mark();
        System.out.println("position:"+position);
        System.out.println("limit:"+limit);
        System.out.println("capacity:"+capacity);
        System.out.println("------------创建-------------------------------");

        //添加数据
        ByteBuffer put = byteBuffer.put("abcdef".getBytes());
        limit = byteBuffer.limit();
        capacity = byteBuffer.capacity();
        position = byteBuffer.position();
        System.out.println("position:"+position);
        System.out.println("limit:"+limit);
        System.out.println("capacity:"+capacity);
        System.out.println("---------------添加----------------------------");

        limit = put.limit();
        capacity = put.capacity();
        position = put.position();
        System.out.println("position:"+position);
        System.out.println("limit:"+limit);
        System.out.println("capacity:"+capacity);
        System.out.println("--------------添加-----------------------------");

        //切换到读
        put.flip();
        limit = put.limit();
        capacity = put.capacity();
        position = put.position();

        System.out.println("position:"+position);
        System.out.println("limit:"+limit);
        System.out.println("capacity:"+capacity);
        System.out.println("-----------------切换--------------------------");

        byte[] bytes = new byte[put.limit()];
        put.get(bytes);

        limit = put.limit();
        capacity = put.capacity();
        position = put.position();

        System.out.println("position:"+position);
        System.out.println("limit:"+limit);
        System.out.println("capacity:"+capacity);
        System.out.println("-----------------值--------------------------"+new String(bytes));

        //重复读
        put.rewind();
        limit = put.limit();
        capacity = put.capacity();
        position = put.position();

        System.out.println("position:"+position);
        System.out.println("limit:"+limit);
        System.out.println("capacity:"+capacity);
        System.out.println("-----------------重复读--------------------------");

        bytes = new byte[put.limit()];
        put.get(bytes);
        System.out.println("-----------------值--------------------------"+new String(bytes));

        //清除  只是清除掉位置 不清空数据
        put.clear();

        limit = put.limit();
        capacity = put.capacity();
        position = put.position();

        System.out.println("position:"+position);
        System.out.println("limit:"+limit);
        System.out.println("capacity:"+capacity);
        System.out.println("-----------------清空--------------------------");
        bytes = new byte[put.limit()];
        put.get(bytes);
        System.out.println("-----------------清空--------------------------"+new String(bytes));
        test();
    }


    public static void test(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        byteBuffer.put("付林虎".getBytes());
        System.out.println(byteBuffer.position());
        byteBuffer.flip();
        byte[] bytes=new byte[1024];
        byteBuffer.get(bytes,0,2);
        System.out.println(new String(bytes,0,2));
        System.out.println(byteBuffer.position());

        //记录当前的position
        byteBuffer.mark();

        bytes=new byte[1024];
        byteBuffer.get(bytes,2,6);
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.position());

        //回复到make标记的位置
        byteBuffer.reset();

        System.out.println(byteBuffer.position());
    }


}
