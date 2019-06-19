package com.fzzz.interview.t35;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T35 {
}

class SubClass extends Outer.inter {
    //因为内部类在创建时需要外部类的引用，所以在内部类的继承上也需要外部类的协助。.super()不能省，否则报错
    public SubClass(Outer outer){
        outer.super();
    }
}

class Outer{
    public Outer() {

    }
    class inter{

    }
}