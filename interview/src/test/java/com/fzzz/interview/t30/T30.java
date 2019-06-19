package com.fzzz.interview.t30;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T30 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i / 2);
        }
        //[0, 0, 1, 1, 2]
        System.out.println(list);
    }
}
