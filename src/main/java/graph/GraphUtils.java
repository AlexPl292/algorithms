package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Plate on 19.09.2018.
 */
public class GraphUtils {

    public static void initAdjList(List<Integer>[] emptyList) {
        for (int i = 0; i < emptyList.length; i++) {
            emptyList[i] = new ArrayList<>();
        }
    }

    public static List<Integer>[] invertAdjList(List<Integer>[] source) {
        @SuppressWarnings("unchecked")
        List<Integer>[] result = new List[source.length];
        GraphUtils.initAdjList(result);

        for (int i = 0; i < source.length; i++) {
            List<Integer> adjListOfVertex = source[i] == null ? new ArrayList<>() : source[i];
            for (int vertex : adjListOfVertex) {
                result[vertex].add(i);
            }
        }

        return result;
    }
}
