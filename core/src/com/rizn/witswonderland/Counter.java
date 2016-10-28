package com.rizn.witswonderland;

public class Counter {

    public static int total;

    public static int count = 0;

    public static void addCount() {
        Counter.count += 1;
    }

    public static boolean checkAllSelected() {
        return Counter.total == Counter.count;
    }

    public static void reset() {
        Counter.total = -1;
        Counter.count = 0;
    }

}
