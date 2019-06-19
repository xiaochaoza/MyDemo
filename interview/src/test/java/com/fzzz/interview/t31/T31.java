package com.fzzz.interview.t31;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T31 {
    public static void main(String[] args) {
        Collection<String> collection;

//        collection = new LinkedHashSet<>();
//        collection = new LinkedList<>();
//        collection = new PriorityQueue<>();
//        collection = new ArrayList<>();
        collection = new ArrayDeque<>();
//        collection = new TreeSet<>();

        collection.add("foo");
        collection.add("bar");
        collection.add("baz");

        collection.forEach(System.out::println);
    }
}
