package com.study.javamodel.javadesignmodel.decorator;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/19 11:22
 * @Version V1.0
 */
public class HongQiDecorator extends CarDecorator {

    //private Car carDecorator;
    public HongQiDecorator(Car decoratcaror) {
        super(decoratcaror);
        //this.carDecorator = decoratcaror;
    }
    public void pre(){
        System.out.println("红旗汽车启动前要记着加油啊！！");
    }

    @Override
    public void run() {
        pre();
        decorator.run();
        end();
    }

    public void end(){
        System.out.println("红旗汽车运行以后要记着保养啊！！！");

    }

}
