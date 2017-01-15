package algos.sorting.mergeSort;

import algos.Recursive;
import algos.sorting.InsertionSort;
import algos.sorting.Sorter;
import utils.Printer;

import java.util.ArrayList;

/**
 * Created by Allan on 1/12/2017.
 */
public class TopDown<T extends Comparable> extends Sorter<T> implements Recursive {

    private InsertionSort<T> insertion = new InsertionSort<T>();
    private final int THRESHOLD = 5;


    int level = 0;
    private void setLevel(int level)
    {
        if (level > this.level)
            this.level = level;
    }



    @Override
    public void sort(ArrayList<T> list) {
        Object[] aux = new Object[list.size()];

        sort(aux, 0, list.size(), list, 1);

    }


    //End is exclusive
    private void sort(Object[] aux, int start, int end, ArrayList<T> list, int level) {

        int size = end - start;

        setLevel(level);

        /*
        if (size < THRESHOLD)
        {
            if (size > 0)
                insertion.sort(list, start, end);

            return;
        }
        */

        if (size <= 1)
            return;
        else if (size == 2)
        {
            T left = list.get(start);
            T right = list.get(start + 1);

            if (left.compareTo(right) > 0)
            {
                exchange(list, start, start+1);
            }

            return;
        }

        super.increment("Sort");

        int mid = (end+start)/2;
        //mid = start + mid;

        sort(aux, start, mid, list, level+1);
        sort(aux, mid, end, list, level+1);
        merge(aux, start, mid, end, list);
    }

    private void merge(Object[] aux, int start, int mid, int end, ArrayList<T> list) {
        super.increment("Merge");

        int i = start;
        int j = mid;

        int ctr = 0;

        while (i < mid && j < end)
        {
            T left = list.get(i);
            T right = list.get(j);

            int compare = left.compareTo(right);

            if (compare < 0) {
                aux[ctr++] = left;
                i++;
            }
            else
            {
                aux[ctr++] = right;
                j++;
            }
        }

        while (i < mid)
        {
            aux[ctr++] = list.get(i++);
        }

        while (j < end)
        {
            aux[ctr++] = list.get(j++);
        }

        for (int x = 0; x < ctr; x++)
        {
            list.set(start + x, (T) aux[x]);
        }
    }

    public void printLevel() {
        Printer.println("Level: " + level);
    }
}
