package hackerrank;

import utils.Printer;

import java.util.ArrayList;

import static hackerrank.NewYearChaos.NA;

/**
 * Created by Allan on 1/14/2017.
 */
public class Bonetrousle {
    public static void main(String[] args) {
        int[] q;
        //q = new int[]{2, 1, 5, 3, 6, 4};
        q = new int[]{2, 5, 1, 3, 4};
        //q = new int[]{3,2,1};


        int min = getMin(q);
        Printer.println(min == NA ? "Too chaotic" : min);
    }


    public static int[] find(int sum, int maxNum, int boxes)
    {
        int maxSum = sum(1, maxNum);
        if (maxSum < sum)
            return null;


        int[] values = generateValues(maxNum);

        ArrayList<Integer> selected = select(1, maxNum, sum, boxes);

        return toArray(selected);
    }

    private static int[] toArray(ArrayList<Integer> selected) {
        if (selected == null)
            return null;

        int[] path = new int[selected.size()];

        for (int i = 0; i < path.length; i++)
            path[i] = selected.get(i);

        return path;
    }

    private static class Result
    {
        public Result(Status status) {
            this.status = status;
        }

        public Result(ArrayList<Integer> add)
        {
            this.toAdd = add;
            this.status = Status.Add;
        }

        public enum Status {
            Add,
            Done,
            Impossible
        }

        ArrayList<Integer> toAdd;
        Status status = Status.Add;
    }

    private static Result Done = new Result(Result.Status.Done);
    private static Result Impossible = new Result(Result.Status.Impossible);

    private static Result select(int currentNum, int maxNum, int remaining, int boxes) {
        if (boxes == 0 && remaining == 0)
            return Done;
        else if (boxes < 0 || remaining < 0 || currentNum > maxNum)
            return Impossible;

        int withRemaining = remaining - currentNum;
        int withBoxes = boxes - 1;

        Result with = select(toAdd + 1, maxNum, sum + toAdd, boxes - 1);
        if (with.status == Result.Status.Done)
        {
            return new Result(toAdd, with);
        }

        Result without = select(toAdd + 1, maxNum, sum, boxes);
        if (with.status == Result.Status.Done)
        {
        }
    }

    private static int sum(int to, int from) {
        int n = from-to + 1;

        return (to+from)/2*n;
    }

    private static int[] generateValues(int maxNum) {
        int[] values = new int[maxNum + 1];

        for (int i = 1; i <= maxNum; i++)
            values[i] = i;

        return values;
    }

}
