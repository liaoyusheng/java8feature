package com.lys.javaStream20191217;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class Main {
    public static void main(String[] arg) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "MILAN");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        Trader tome = new Trader("Tome", "jpanese");


        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950),
                new Transaction(tome, 2013, 960)
        );

        //提取2011的记录
        List<Transaction> tr2011 = transactions.stream().filter(transaction -> transaction.getYear() == 2011).collect(toList());
        System.out.println(tr2011);


        //提取所有城市（方法一 去重）
        List<String> citys = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(toList());
        System.out.println(citys);

        //提取所有城市（方法二 去重）
        Set<String> city_set = transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(Collectors.toSet());
        System.out.println(city_set);

        //指定城市交易记录（去重   按名称排序）
        List<Trader> traders = transactions.stream().map(transaction -> transaction.getTrader()).
                filter(trader -> "Cambridge".equals(trader.getCity())).distinct()
                .sorted(Comparator.comparing(Trader::getName)).collect(toList());

        System.out.println(traders);

        //最小的交易值
        Optional<Integer> highestValue = transactions.stream().map(transaction -> transaction.getValue()).reduce(Integer::min);
        System.out.println(highestValue);

        //最小的交易值
        Optional<Transaction> h1 = transactions.stream().min(Comparator.comparing(transaction -> transaction.getValue()));
        System.out.println(h1);

        //最小的交易值的记录
        Optional<Transaction> h2 = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(h2);

        //判定一个数是否为整数
        System.out.println(1120.366 % 1);//0.366
        System.out.println(12 % 1);//0

        //迭代器，计算斐波那契数列前20项
        //第一项：b1=new int[]{0, 1}
        //第二项：b2=new int int[]{b1[1], b1[0] + b1[1]}}
        //第三项： 同理
        Stream.iterate(new int[]{0, 1}, b -> new int[]{b[1], b[0] + b[1]}).limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));


    }
}
