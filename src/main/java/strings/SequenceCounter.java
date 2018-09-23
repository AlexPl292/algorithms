package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex Plate on 21.09.2018.
 */
public class SequenceCounter {
    public static Map<Character, Integer> count(String str) {
        Map<Character, Integer> res = new HashMap<>();
        char currentChar = 0;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char strChar = str.charAt(i);
            if (strChar != currentChar) {
                if (!res.containsKey(currentChar) || res.get(currentChar) < count) {
                    res.put(currentChar, count);
                }
                currentChar = strChar;
                count = 1;
            } else {
                count++;
            }
        }
        if (!res.containsKey(currentChar) || res.get(currentChar) < count) {
            res.put(currentChar, count);
        }
        return res;
    }
}
