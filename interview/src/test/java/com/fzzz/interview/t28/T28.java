package com.fzzz.interview.t28;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T28 {
    public static void main(String[] args) {
        Child child = new Child();
    }
}

class Parent {
    {
        System.out.println("1");
    }

    static {
        System.out.println("2");
    }
}

class Child extends Parent {
    {
        System.out.println("3");
    }

    static {
        System.out.println("4");
    }
}
