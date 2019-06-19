package com.fzzz.interview.t29;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T29 {
    public static void main(String[] args) {
        Parent child = new Child();
        System.out.println(child.value + "&" + child.getValue());
    }
}

class Parent {
    String value = "parent";

    String getValue() {
        return value;
    }
}

class Child extends Parent {
    String value = "child";

    String getValue() {
        return value;
    }
}
