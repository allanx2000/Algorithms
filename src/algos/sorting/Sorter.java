package algos.sorting;

import utils.Counter;

import java.util.ArrayList;

/**
 * Created by Allan on 1/12/2017.
 */
public abstract class Sorter<T extends Comparable> {

    private Counter counter = new Counter();

    public void printCounts()
    {
        counter.print();
    }

    protected void increment(String tag)
    {
        counter.increment(tag);
    }


    public abstract void sort(ArrayList<T> list);

    protected void exchange(ArrayList<T> list, int j, int k) {

        increment("Exchange");

        T tmp = list.get(j);
        list.set(j, list.get(k));
        list.set(k, tmp);
    }

}
