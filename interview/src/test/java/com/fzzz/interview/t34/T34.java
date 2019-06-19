package com.fzzz.interview.t34;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T34 implements First, Second {
    @Override
    public String talk() {
        return null;
    }

    @Override
    public String whisper() {
        return null;
    }
}

interface First {
    String talk();

    default String whisper() {
        return talk() + ", but softer";
    }

    default String yell() {
        return talk() + ", but louder";
    }
}

interface Second {
    String talk();

    default String whisper() {
        return talk() + ", but less annying";
    }

    static String shout() {
        return "but less annying";
    }
}