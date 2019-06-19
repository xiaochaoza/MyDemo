package com.fzzz.interview.t36;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T36 {
    public static void main(String[] args) {
        Pet pet = new Cat();
        Ball ball = new Ball();

        System.out.println(String.format("My %s is playing with a %s", pet.getName(), ball.getName()));
    }
}

class Pet {
    public String getName() {
        return "pet";
    }
}

class Cat extends Pet {
    @Override
    public String getName() {
        return "cat";
    }
}

class Shape {
    private String name;

    public Shape() {
        name = "shape";
    }

    public String getName() {
        return name;
    }
}

class Ball extends Shape {
    private String name;

    public Ball() {
        name = "ball";
    }

}