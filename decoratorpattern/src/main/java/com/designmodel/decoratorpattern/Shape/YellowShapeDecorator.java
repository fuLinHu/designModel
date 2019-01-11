package com.designmodel.decoratorpattern.Shape;

public class YellowShapeDecorator extends ShapeDecorator {
    public YellowShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        drawYeow();
    }

    public void drawYeow(){
        System.out.println("yellow----------------");
    }
}
