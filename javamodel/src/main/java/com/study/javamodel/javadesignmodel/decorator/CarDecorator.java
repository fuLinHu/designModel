package com.study.javamodel.javadesignmodel.decorator;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/19 11:12
 * @Version V1.0
 */
public abstract class CarDecorator implements Car {
    private Car decorator;

    public CarDecorator(Car decorator){
        this.decorator=decorator;
    }

    @Override
    public void run() {
        decorator.run();
    }
}
