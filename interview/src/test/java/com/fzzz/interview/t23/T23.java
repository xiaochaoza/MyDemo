package com.fzzz.interview.t23;

import java.util.Set;
import java.util.TreeSet;

/**
 * description: set去重
 * author: ShenChao23.png
 * time: 2019-06-18
 * update:
 */
public class T23 {

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();

        set.add(3);
        set.add((int)3.0);
        set.add(2);
        set.add(2);
        set.add(new Integer(2));
        set.add(Integer.parseInt("2"));

        //2019-06-18 10:38:14.057 14141-14141/com.fzzz.mydemo I/System.out: [2, 3]
        System.out.println(set);
    }
}
