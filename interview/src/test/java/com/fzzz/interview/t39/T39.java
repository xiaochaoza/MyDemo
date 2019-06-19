package com.fzzz.interview.t39;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T39 {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();

        stack.push("a");
        stack.push("b");
        stack.push("c");
        queue.add(stack.pop());
        stack.push("d");
        stack.push("e");
        queue.add("f");
        stack.push(queue.remove());
        queue.add(stack.pop());
        queue.add(stack.pop());

        System.out.println(stack);
        System.out.println(queue);
    }
}
