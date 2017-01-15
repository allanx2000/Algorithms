package algos.sorting;

import java.util.ArrayList;

/**
 * Created by Allan on 1/12/2017.
 */
public class InsertionSort<T extends Comparable> extends Sorter {



    @Override
    public void sort(ArrayList list)
    {
        for (int i = 1; i < list.size(); i++)
            sort(i, list);

    }

    public void sort(ArrayList<T> list, int start, int end) {
        ArrayList<T> sub = new ArrayList<T>(list.subList(start, end));

        sort(1, sub);

        for (int i = 0; i < sub.size(); i++)
            list.set(start + i, sub.get(i));
    }


    private void sort(int idx, ArrayList<T> list)
    {
        increment("Sort");

        if (idx == list.size())
            return;

        for (int j = idx; j > 0; j--)
        {
            Comparable current = list.get(j);
            Comparable last = list.get(j - 1);

            if (current.compareTo(last) < 0)
                exchange(list, j, j-1);
        }
    }

}
