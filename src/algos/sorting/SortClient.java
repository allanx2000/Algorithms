package algos.sorting;

import algos.Recursive;
import algos.sorting.mergeSort.TopDown;
import utils.Printer;
import utils.RandomUtil;

import java.util.ArrayList;

/**
 * Created by Allan on 1/12/2017.
 */
public class SortClient {
    public static void main(String[] args)
    {
        ArrayList<Integer> ints = RandomUtil.generateRandom(12345, (int) Math.pow(2,16));

        Printer.println("Items: " + ints.size());

        boolean printItems = false;

        if (printItems)
            Printer.printIterable(ints);

        Printer.println("");

        Sorter<Integer> sorter;

        //sorter = new InsertionSort<>();
        sorter = new TopDown<>();

        sorter.sort(ints);

        if (printItems)
            Printer.printIterable(ints);

        sorter.printCounts();

        if (sorter instanceof Recursive)
            ((Recursive) sorter).printLevel();
    }

}
