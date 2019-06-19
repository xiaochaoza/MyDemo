package com.fzzz.interview.t25;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T25 {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.makeNoise();
        d.move();
        d.wagTail();
        d.fetch();

        Canine c = new Dog();
        c.makeNoise();
        c.move();
        c.wagTail();
//        c.fetch();

        Animal a = new Dog();
        a.makeNoise();
        a.move();
//        a.wagTail();
//        a.fetch();
    }
}

abstract class Animal {
    public abstract void makeNoise();

    public abstract void move();
}

abstract class Canine extends Animal {
    public void wagTail() {
        System.out.println("wagging");
    }

    @Override
    public void move() {
        System.out.println("run");
    }
}

class Dog extends Canine {
    @Override
    public void makeNoise() {
        System.out.println("bark");
    }

    public void fetch() {
        System.out.println("Fetch");
    }

    @Override
    public void move() {
        System.out.println("dog run");
    }
}