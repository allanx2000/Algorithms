package hackerrank;

import utils.Printer;

import javax.naming.OperationNotSupportedException;

/**
 * Created by Allan on 1/14/2017.
 */
public class NewYearChaos {
    public static void main(String[] args) {
        int[] q;
        //q = new int[]{2, 1, 5, 3, 6, 4};
        q = new int[]{2, 5, 1, 3, 4};
        //q = new int[]{3,2,1};


        int min = getMin(q);
        Printer.println(min == NA ? "Too chaotic" : min);
    }

    //TODO: should be check from left to right

    static final int NA = -1;

    public static int getMin(int[] queue) {
        int moves = 0;

        boolean swapped;

        for (int x = queue.length - 1; x >= 0; x--) {

            do {
                swapped = false;

                for (int i = x; i >= 0; i--) {
                    //Look for original person

                    if (queue[i] - 1 == i)
                        continue;

                    int idx = findProper(queue, i, i + 1);

                    if (idx == NA)
                        continue;

                    int diff = i - idx;

                    moveRight(queue, idx, i);
                    swapped = true;
                    moves += diff;

                    Printer.printIterable(queue);
                }
            }
            while (swapped);
        }

        boolean inOrder = checkOrder(queue);
        if (!inOrder)
            return NA;

        return moves;
    }

    private static int findProper(int[] queue, int i, int sticker) {
        int idx = NA;

        for (int j = i - 1; j >= 0 && j >= i - 2; j--) {

            if (sticker == queue[j]) {
                idx = j;
                break;
            }
        }
        return idx;
    }

    private static boolean checkOrder(int[] queue) {
        for (int i = 0; i < queue.length; i++) {
            if (i != queue[i] - 1)
                return false;
        }

        return true;
    }

    private static void moveRight(int[] queue, int start, int end) {
        for (int i = start; i < end; i++) {
            exchange(queue, i, i + 1);
        }
    }

    private static void exchange(int[] queue, int a, int b) {
        int tmp = queue[a];

        queue[a] = queue[b];
        queue[b] = tmp;
    }
}
