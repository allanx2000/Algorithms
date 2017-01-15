package hackerrank;

import com.sun.xml.internal.messaging.saaj.soap.FastInfosetDataContentHandler;

import java.util.*;

/**
 * Created by Allan on 1/14/2017.
 */
public class BearGene {

    /*

    public static void main(String[] args) {


        Scanner ns= new Scanner(System.in);
        System.out.println(ns.nextLine());
        System.out.println(ns.nextLine());

    }
     */

    public static void main(String[] args)
    {
        String input = "CGAAGCCA";
        System.out.println(input);

        int length = find(input.length(), input);
        System.out.println(length);
    }

    private static final char G = 'G';
    private static final char A = 'A';
    private static final char C = 'C';
    private static final char T = 'T';

    private static class Value
    {
        int index;
        char value;

        public Value(int i, char c) {
            index = i;
            value = c;
        }
    }

    private static int find(int length, String pattern)
    {
        int expected = length/4;

        HashMap<Character,Integer> map = new HashMap<>();
        map.put(G,0);
        map.put(A,0);
        map.put(C,0);
        map.put(T,0);

        for (int i = 0; i < pattern.length(); i++)
        {
            char c = pattern.charAt(i);
            map.put(c, map.get(c) + 1);
        }

        //Find characters with to many

        HashMap<Character, Integer> extras = getExtras(map, expected);
        if (extras.size() == 0)
            return 0;

        //Search for shortest array that has all the inputs

        int segLength = 0;

        ArrayList<Value> values = new ArrayList<>();

        for (int i = 0; i < pattern.length(); i++)
        {
            char c = pattern.charAt(i);

            //System.out.println(c);

            if (inExtras(extras, c))
            {
                values.add(new Value(i, c));
                decrease(c, extras);
                
                if (isEmpty(extras)) {
                    break;
                }
            }
        }

        segLength = getLength(values);

        Set<Character> keySet = extras.keySet();

        for (int i = getLast(values).index + 1; i < pattern.length(); i++)
        {

            char c = pattern.charAt(i);
            //System.out.println(c);

            if (keySet.contains(c))
            {
                values.add(new Value(i, c));
                increase(c, extras);
            }

            int tail_idx = i - segLength;
            Value head = values.get(0);
            if (head.index < tail_idx)
            {
                decrease(head.value, extras);
                values.remove(0);
            }

            if (isEmpty(extras))
            {
                int newLength = getLength(values);
                if (newLength < segLength)
                    segLength = newLength;
            }
                /*
            if (c == first.value)
            {
                Value next = values.get(1);
                int firstToNext = next.index - first.index;
                int thisToLast = i - last.index;

                if (thisToLast <= firstToNext)
                {
                    Value v = new Value(i, c);
                    values.add(v);
                    values.remove(0);

                    first = next;
                    last = v;
                }
            }
            */
        }

        int cleanLength = cleanUp(extras, values);

        return cleanLength;
    }

    private static int cleanUp(HashMap<Character, Integer> extras, ArrayList<Value> values) {

        int oldLength = getLength(values);;

        HashMap<Character, Integer> toClean = new HashMap<>();
        for (Map.Entry<Character,Integer> entry : extras.entrySet())
        {
            if (entry.getValue() < 0)
                return oldLength;
            else if (entry.getValue() > 0)
                toClean.put(entry.getKey(), entry.getValue());
        }

        while (values.size() > 0) // & !isEmpty(toClean))
        {
            Value top = values.get(0);
            int ct = toClean.get(top.value);
            if (ct > 0)
            {
                values.remove(0);
                toClean.put(top.value, ct - 1);
            }
            else
                break;
        }

        if (isEmpty(toClean))
            return getLength(values);
        else
        return oldLength;
    }

    private static void increase(char c, HashMap<Character, Integer> extras) {
        extras.put(c, extras.get(c) + 1);
    }

    private static int getLength(ArrayList<Value> values) {
        int start = values.get(0).index;
        int end = getLast(values).index;

        return end - start + 1;
    }

    private static Value getLast(ArrayList<Value> values) {
        return values.get(values.size() - 1);
    }

    private static boolean isEmpty(HashMap<Character, Integer> extras) {
        for (Integer i : extras.values())
        {
            if (i != 0)
                return false;
        }

        return true;
    }

    private static void decrease(char c, HashMap<Character, Integer> extras) {
        extras.put(c, extras.get(c) - 1);
    }

    private static boolean inExtras(HashMap<Character, Integer> extras, char c) {
        return extras.containsKey(c) && extras.get(c) != 0;
    }

    private static HashMap<Character,Integer> getExtras(HashMap<Character, Integer> map, int expected) {
        HashMap<Character,Integer> extras = new HashMap<>();

        for (Map.Entry<Character,Integer> entry : map.entrySet())
        {
            if (entry.getValue() > expected)
            extras.put(entry.getKey(), entry.getValue() - expected);

        }

        return extras;
    }
}
