package com.fzzz.interview.t26;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * description: 去除偶数
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T26 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            list.add(r.nextInt(20));
        }
        remove2(list);
        list.forEach(System.out::println);
    }

    private static void remove1(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element % 2 == 0) {
                iterator.remove();
            }
        }
    }

    /**
     *  集合不能遍历过程修改
     * ConcurrentModificationException
     * @param list
     */
    private static void remove2(List<Integer> list) {
        for (Integer element : list) {
            if (element % 2 == 0) {
                list.remove(element);
            }
        }
    }

    private static void remove3(List<Integer> list) {
        List<Integer> tempList = new ArrayList<>();
        for (Integer element : list) {
            if (element % 2 == 0) {
                tempList.add(element);
            }
        }
        list.removeAll(tempList);
    }
}
