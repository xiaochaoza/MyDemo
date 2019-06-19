package com.fzzz.interview.t24;

/**
 * description: set去重
 * author: ShenChao23.png
 * time: 2019-06-18
 * update:
 */
public class T24 {

    public static void main(String[] args) {
        ExceptionTest exceptionTest = new ExceptionTest();
        try {
            exceptionTest.divide(2, 0);
        } catch (Exception e) {
            System.out.println("division by 0");
        }
    }
}

class ExceptionTest {
    protected Integer divide(int a, int b) {
        try {
            return a / b;
        } finally {
            System.out.println("finally");
        }
    }
}

