package com.fzzz.design_mode.anno;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-19
 * update:
 */
public class MyAnnoTest {

    @MyAnnotation(hello = "hello, beijing", world = "world, world")
    public void output(){
        System.out.println("method output is running");
    }
}
