package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Plate on 19.09.2018.
 */
public class GraphUtils {
    public static List<Integer>[] invertAdjList(List<Integer>[] source) {
        @SuppressWarnings("unchecked")
        List<Integer>[] result = new List[source.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
        }

        for (int i = 0; i < source.length; i++) {
            for (int vertex : source[i]) {
                result[vertex].add(i);
            }
        }

        return result;
    }
}
