package hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allan on 1/14/2017.
 */
public class RichieRich {


    public static class Pair
    {
        int leftIdx;
        int leftOriginal;
        int rightIdx;
        int rightOriginal;

        Integer leftFinal = null;
        Integer rightFinal = null;
    }

    /*

    1. Split in middle
    2. Find haw many changes needed from A -> B
    4. If > Limit => -1


     */


    public static void main(String[] args) {
        String value;
        int subs = 0;

        //subs = 5;
        //value = "01624";

        //subs = 1;
        //value = "3943";

        //subs = 3;
        //value = "092282";

        subs = 1;
        value = "0011";

        System.out.println(maxPalindrome(value, subs));
    }

    private static final String NA = "-1";

    public static String maxPalindrome(String original, int changesAllowed)
    {
        int[] values = toArray(original);

        List<Pair> changes = findChanges(values);
        if (changes.size() > changesAllowed)
            return NA;

        Map<Integer, Integer> indexesChanged = pickChanges(changes);
        makeChanges(indexesChanged, values);

        //Handle remaining
        int changesLeft = changesAllowed - changes.size();

        if (changesLeft > 0)
        {
            adjustRemaining(indexesChanged, values, changesLeft);
        }

        //....

        StringBuilder sb = new StringBuilder();
        for (int i =0; i < values.length; i++)
        {
            sb.append(values[i]);
        }

        String result = sb.toString();
        return result;
    }

    private static void makeChanges(Map<Integer, Integer> indexesChanged, int[] values) {
        for (int i =0; i < values.length; i++)
        {
            if (indexesChanged.containsKey(i))
                values[i] = indexesChanged.get(i);
        }
    }

    private static void adjustRemaining(Map<Integer, Integer> indexesChanged, int[] values, int remaining) {

        int left = 0;
        int right = values.length - 1;

        while (left < right && remaining > 0) {
            int v_l = values[left];

            if (v_l != 9)
            {
                if (indexesChanged.containsKey(left) || indexesChanged.containsKey(right))
                {
                    values[left] = values[right] = 9;
                    remaining--;
                }
                else if (remaining >= 2)
                {
                    values[left] = values[right] = 9;
                    remaining = remaining - 2;
                }
            }

            left++;
            right--;
        }

        if (remaining > 0 && values.length % 2 == 1)
        {
            int idx_mid = (values.length - 1) / 2;
            values[idx_mid] = 9;
        }
    }

    private static HashMap<Integer, Integer> pickChanges(List<Pair> changes) {

        HashMap<Integer, Integer> indexes = new HashMap<>();

        for (Pair p : changes)
        {
            if (p.leftOriginal < p.rightOriginal) {
                p.leftFinal = p.rightOriginal;
                indexes.put(p.leftIdx, p.leftFinal);
            }
            else // p.rightOriginal < p.LeftOriginal
            {
                p.rightFinal = p.leftOriginal;
                indexes.put(p.rightIdx, p.rightFinal);
            }
        }

        return indexes;
    }

    private static List<Pair> findChanges(int[] values) {

        ArrayList<Pair> pairs = new ArrayList<>();

        int left = 0; int right = values.length - 1;

        while (left < right)
        {
            int v_l = values[left];
            int v_r = values[right];

            if (v_l != v_r)
            {
                Pair p = new Pair();
                p.leftIdx = left;
                p.rightIdx = right;
                p.leftOriginal = v_l;
                p.rightOriginal = v_r;

                pairs.add(p);
            }

            left++;
            right--;
        }

        return pairs;
    }


    public static int[] toArray(String str)
    {
        int[] x = new int[str.length()];

        for (int i = 0; i < str.length(); i++)
        {
            int val = Integer.parseInt(String.valueOf(str.charAt(i)));
            x[i] = val;
        }

        return x;
    }


}
