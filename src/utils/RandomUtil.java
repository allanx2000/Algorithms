package utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Allan on 1/13/2017.
 */
public class RandomUtil {
    public static ArrayList<Integer> generateRandom(long seed, int values) {
        return generateRandom(seed, values, 0);
    }

    public static ArrayList<Integer> generateRandom(long seed, int values, int offset) {
        ArrayList<Integer> ints = new ArrayList<>();

        Random random = new Random(seed);

        for (int i = 0; i < values; i++)
            ints.add(random.nextInt(values) + offset);

        return ints;
    }
}
