package com.company.strategy;

public class MainStrategy {

    public static void main(String[] args) {
        Shapes s = new Shapes();
        Triangle triangle = new Triangle();
        Circle circle = new Circle();
        Rect rect = new Rect();
        
        triangle.draw();
        circle.draw();
        rect.draw();

        s.registerShape("Triangle");
        s.registerShape("Rect");
        s.registerShape("Triangle");
        s.registerShape("Circle");

        s.drawAll();

    }

}


class Triangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Triangle!!!");
    }

}

class Rect implements Shape{

    @Override
    public void draw() {
        System.out.println("Rect!!!");
    }

}

class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("Circle!!!");
    }

}