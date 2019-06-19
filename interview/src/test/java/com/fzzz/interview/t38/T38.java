package com.fzzz.interview.t38;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T38 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("foo");

        List<String> list2 = list1;
        List<String> list3 = new ArrayList<>(list2);

        list1.clear();
        list2.add("bar");
        list3.add("baz");

        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
    }
}
