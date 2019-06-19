package com.fzzz.interview.t37;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T37 {
    public static void main(String[] args) {
        List<Asteroid> list = new ArrayList<>();
        list.add(new Asteroid("Sylvia", 286));
        list.add(new Asteroid("Pallas", 512));
        list.add(new Asteroid("Eunomia", 268));
        list.add(new Asteroid("Juno", 258));
        list.add(new Asteroid("Hygiea", 631));
        list.add(new Asteroid("Davida", 289));

        Asteroid c = list
                //流式数据处理
                .stream()
                //以get(0)的数据为标准，按照规则返回数据
                .reduce(list.get(1), new BinaryOperator<Asteroid>() {
                    @Override
                    public Asteroid apply(Asteroid a, Asteroid b) {
                        return a.getDiameter() > b.getDiameter() ? a : b;
                    }
                });
        System.out.println(c);
    }
}

class Asteroid {
    private String name;
    private double diameter;

    public Asteroid(String name, double diameter) {
        this.name = name;
        this.diameter = diameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public String toString() {
        return String.format("%s, [%.2f]", getName(), getDiameter());
    }
}