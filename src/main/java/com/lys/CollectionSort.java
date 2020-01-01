package com.lys;


import java.util.ArrayList;
import java.util.List;
/*
* 函数接口使用（有且只有一个抽象函数的接口，可以有默认函数）
* 行为参数化：排序
*
* */
public class CollectionSort {

    public static void main(String arg[]) {
        String[] colors = {"green", "yellow", "red"
        };
        List<Apple> re = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            re.add(new Apple(i * 2 + i ^ 2 + 100, colors[i % 3]));
        }
        System.out.println(re.toString());

//        sort(re, apple -> "green".endsWith(apple.getColor()));

        sort(re, Apple::getWeight);

    }


    public static void sort(List<Apple> list, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (predicate.test(apple)>105) {
                result.add(apple);
            }
        }

        System.out.println(result.toString());


    }


}

class Apple {
    int weight;
    String color;

    public Apple(int w, String color) {
        this.weight = w;
        this.color=color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return weight + " " + color;

    }
}

interface ApplePredicate {
    int test(Apple apple);
}