package utils;

import java.security.Key;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allan on 1/12/2017.
 */
public class Counter {

    private HashMap<String, Integer> counts = new HashMap<>();

    public void increment(String tag)
    {
        if (!counts.containsKey(tag))
            counts.put(tag, 1);
        else
            counts.put(tag, counts.get(tag) + 1);
    }

    public void print()
    {
        for (Map.Entry<String,Integer> kv : counts.entrySet())
        {
            System.out.println(kv.getKey() + ": " + kv.getValue());
        }
    }
}
