package utils;

import java.util.ArrayList;

/**
 * Created by Allan on 1/12/2017.
 */
public class Printer {
    public static void println(Object object)
    {
        System.out.println(object);
    }

    public static void print(Object object)
    {
        System.out.print(object);
    }

    public static void printIterable(Iterable<?> list) {
        for (Object i : list)
            println(i);;
    }

    public static <T extends Object> void printIterable(T[] queue) {
        for (Object i : queue)
            println(i);
    }

    public static void printIterable(int[] queue) {
        for (Object i : queue)
            print(i + ", ");

        println("");
    }
}
