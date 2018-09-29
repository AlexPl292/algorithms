package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Plate on 29.09.2018.
 */
public class SubstringSearch {
    public static Integer[] getIndexes(String substring, String source) {
        String fullString = substring + '$' + source;
        int[] function = ZFunction.getFunction(fullString);
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < function.length; i++) {
            if (function[i] == substring.length()) {
                indexes.add(i - (substring.length() + 1));
            }
        }

        return indexes.toArray(new Integer[0]);
    }
}
