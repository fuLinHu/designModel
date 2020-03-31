package com.study.javamodel.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class Channel {
    public static void main(String[] args) throws IOException {
        /*test();
        test1();
        test2();
        test3();
        test4();*/
        test5();
    }
    public static void test() throws IOException {
        long s = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("F:\\高级架构\\第五节(水平分割取摸算法案例).mp4");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\高级架构\\第五节.mp4");
        FileChannel inchannel = fileInputStream.getChannel();
        FileChannel outchannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while(inchannel.read(byteBuffer)>-1){
            byteBuffer.flip();
            outchannel.write(byteBuffer);
            byteBuffer.clear();
        }
        fileInputStream.close();
        fileOutputStream.close();
        inchannel.close();
        outchannel.close();
        long l = System.currentTimeMillis();
        System.out.println("test()"+(l-s));
    }
    public static void test1() throws IOException {
        long s = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("F:\\高级架构\\第五节(水平分割取摸算法案例).mp4");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\高级架构\\第五节.mp4");
        FileChannel inchannel = fileInputStream.getChannel();
        FileChannel outchannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while(inchannel.read(byteBuffer)>-1){
            byteBuffer.flip();
            outchannel.write(byteBuffer);
            byteBuffer.clear();
        }
        fileInputStream.close();
        fileOutputStream.close();
        inchannel.close();
        outchannel.close();
        long l = System.currentTimeMillis();
        System.out.println("test1()"+(l-s));
    }
    public static void test2() throws IOException {
        long s = System.currentTimeMillis();
        FileChannel openin = FileChannel.open(Paths.get("F:\\高级架构\\第五节(水平分割取摸算法案例).mp4"), StandardOpenOption.READ);
        FileChannel openout = FileChannel.open(Paths.get("F:\\高级架构\\第五节3.mp4"),StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE);

        MappedByteBuffer mapin = openin.map(FileChannel.MapMode.READ_ONLY, 0, openin.size());
        MappedByteBuffer mapout = openout.map(FileChannel.MapMode.READ_WRITE, 0, openin.size());
        byte[] byt = new byte[1024];
        mapin.get(byt);
        mapout.put(byt);
        long l = System.currentTimeMillis();
        openin.close();
        openout.close();
        System.out.println("test2()"+(l-s));
    }
    public static void test3() throws IOException {
        long s = System.currentTimeMillis();
        FileChannel openin = FileChannel.open(Paths.get("F:\\高级架构\\第五节(水平分割取摸算法案例).mp4"), StandardOpenOption.READ);
        FileChannel openout = FileChannel.open(Paths.get("F:\\高级架构\\第五节4.mp4"),StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE);
        openin.transferTo(0,openin.size(),openout);
        openin.close();
        openout.close();
        long l = System.currentTimeMillis();
        System.out.println("test3()"+(l-s));
    }
    //分散//聚集
    public static void test4() throws IOException {
        long s = System.currentTimeMillis();
        FileChannel openin = FileChannel.open(Paths.get("F:\\高级架构\\第五节(水平分割取摸算法案例).mp4"), StandardOpenOption.READ);
        FileChannel openout = FileChannel.open(Paths.get("F:\\高级架构\\第五节5.mp4"),StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0]=byteBuffer1;
        byteBuffers[1]=byteBuffer2;
        while (openin.read(byteBuffers)>-1){
            for (int i = 0; i <byteBuffers.length ; i++) {
                byteBuffers[i].flip();
            }
            openout.write(byteBuffers);
            for (int i = 0; i <byteBuffers.length ; i++) {
                byteBuffers[i].clear();
            }
        }
        openin.close();
        openout.close();
        long l = System.currentTimeMillis();
        System.out.println("test4()"+(l-s));
    }
    //字符编码
    public static void test5() throws CharacterCodingException {
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = stringCharsetSortedMap.entrySet();
       /* for (Map.Entry<String, Charset> item: entries) {
            String key = item.getKey();
            Charset value = item.getValue();
            System.out.println(key+"----"+value);
        }*/
        Charset gkb = Charset.forName("GBK");
        CharsetEncoder charsetEncoder = gkb.newEncoder();
        CharsetDecoder charsetDecoder = gkb.newDecoder();

        CharBuffer  charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("付林虎");
        charBuffer.flip();
        ByteBuffer encode = charsetEncoder.encode(charBuffer);
        byte[] array = encode.array();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        String s = new String(array);

        CharBuffer decode = charsetDecoder.decode(encode);
        char[] array1 = decode.array();
        for (int i = 0; i < array1.length; i++) {
            System.out.println(array1[i]);
        }
        /*CharsetDecoder charsetDecoder = gkb.newDecoder();

        ByteBuffer byteBuffer = gkb.encode("付林虎");
        byteBuffer.flip();

        byte b = byteBuffer.get();
        System.out.println(b);*/



    }


}
