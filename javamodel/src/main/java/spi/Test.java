package spi;

import java.util.ServiceLoader;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/4/20 19:14
 * @Version V1.0
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Ainamal> load = ServiceLoader.load(Ainamal.class);
        load.forEach(o->{
            o.run();
        });
    }
}
