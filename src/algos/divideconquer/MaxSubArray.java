package algos.divideconquer;

import algos.Recursive;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.Counter;
import utils.Printer;
import utils.RandomUtil;

import java.util.ArrayList;

/**
 * Created by Allan on 1/13/2017.
 */
public class MaxSubArray {

    public static class MaxResult implements Comparable
    {

        public final int start;
        public final int end;
        public final Integer sum;

        public MaxResult(int start, int end, Integer sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        public String toString()
        {
            return "Start: " + start + ", End: " + end + ", Sum: " + sum;
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof MaxResult))
                throw new NotImplementedException();

            return this.sum.compareTo(((MaxResult) o).sum);
        }

    }

    private Counter counter = new Counter();

    public void printCounts()
    {
        counter.print();
    }


    private int level = 0;

    public static void main(String[] args)
    {
        int count = 100000;
        int offset = (int) (-1*(.55*count));

        ArrayList<Integer> array = RandomUtil.generateRandom(123,count, offset);
        //Printer.printIterable(array);

        MaxSubArray msa = new MaxSubArray();
        MaxResult result = msa.findMax(array);

        Printer.println(result);

        //for (int i = result.start; i < result.end; i++)
        //    Printer.println(array.get(i));

        Printer.println("Level: " + msa.level);
        msa.counter.print();

    }

    public MaxResult findMax(ArrayList<Integer> array) {
        return findMax(array, 0, array.size(), 0);
    }

    private MaxResult findMax(ArrayList<Integer> array, int start, int end, int level) {

        counter.increment("findMax");
        if (level > this.level)
            this.level = level;

        if (end - start <= 1) {
            counter.increment(">= 1");
            return new MaxResult(start, start + 1, array.get(start));
        }
        int mid = (start+end)/2;

        MaxResult left = findMax(array, start, mid, level+ 1);          //T(N/2)
        MaxResult right = findMax(array, mid, end, level+ 1);           //T(N/2)
        MaxResult cross = findCross(array, start, mid, end);            //N
                                                                        //T(N) = 2*T(N/2) + O(n)

        if (left.compareTo(right) >= 0 && left.compareTo(cross) >= 0)
            return left;
        else if (right.compareTo(left) >= 0 && right.compareTo(cross) >= 0)
            return right;
        else
            return cross;
    }

    private MaxResult findCross(ArrayList<Integer> array, int lo, int mid, int hi) {

        counter.increment("findCross");

        int leftSum, rightSum;
        leftSum = rightSum = Integer.MIN_VALUE;

        int start = mid, end = mid;

        int curSum = 0;

        for (int i = mid - 1; i >= lo; i--)
        {
            curSum += array.get(i);

            if (curSum > leftSum)
            {
                leftSum = curSum;
                start = i;
            }
        }

        curSum = 0;

        for (int i = mid; i < hi; i++)
        {
            curSum += array.get(i);

            if (curSum > rightSum)
            {
                rightSum = curSum;
                end = i;
            }
        }

        end = end + 1; //Make Exclusive

        MaxResult result = new MaxResult(start, end, leftSum + rightSum);
        return result;
    }
}
