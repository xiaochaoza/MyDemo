package com.fzzz.interview.t33;

import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T33 {
    void addObjectList(List<Object> list) {
        list.add("foo");
    }

    //报错，字符串不一定是T的子类
//    <T> void addGenericsList(List<T> list) {
//        list.add("foo");
//    }

    //报错，集合里是字符串的子类，不能加父类
//    void addExtentStringList(List<? extends String> list) {
//        list.add("foo");
//    }

    void addSuperStringList(List<? super String> list) {
        list.add("foo");
    }

    void addStringList(List<String> list) {
        list.add("foo");
    }

    void addRawList(List list) {
        list.add("foo");
    }
}
