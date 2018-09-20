import graph.GraphUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Alex Plate on 19.09.2018.
 */
public class GraphUtilsTest {

    @Test
    public void testInvertAdjList_OneVertex_VertexItself() throws Exception {
        @SuppressWarnings("unchecked")
        List<Integer>[] adjList = new List[1];
        adjList[0] = new ArrayList<>();

        List<Integer>[] res = GraphUtils.invertAdjList(adjList);

        assertEquals(1, res.length);
        assertEquals(0, res[0].size());
    }

    @Test
    public void testInvertAdjList_TwoVertex_Reversed() throws Exception {
        @SuppressWarnings("unchecked")
        List<Integer>[] adjList = new List[2];
        adjList[0] = new ArrayList<>();
        adjList[1] = new ArrayList<>();

        adjList[0].add(1);

        List<Integer>[] res = GraphUtils.invertAdjList(adjList);

        assertEquals(2, res.length);
        assertEquals(0, res[0].size());
        assertEquals(1, res[1].size());
        assertEquals(0, (int) res[1].get(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testInvertAdjList_DifficultGraph_getReversed() throws Exception {
        int graphSize = 10;
        List<Integer>[] adjList = new List[graphSize];
        List<Integer>[] result = new List[graphSize];

        for (int i = 0; i < graphSize; i++) {
            adjList[i] = new ArrayList<>();
            result[i] = new ArrayList<>();
        }

        adjList[0].addAll(Arrays.asList(3, 8, 9));
        adjList[1].add(2);
        adjList[2].add(7);
        adjList[3].add(5);
        adjList[4].add(3);
        adjList[5].add(4);
        adjList[6].addAll(Arrays.asList(5, 1));
        adjList[8].add(5);
        adjList[9].add(8);

        result[1].add(6);
        result[2].add(1);
        result[3].addAll(Arrays.asList(0, 4));
        result[4].add(5);
        result[5].addAll(Arrays.asList(3, 6, 8));
        result[7].add(2);
        result[8].addAll(Arrays.asList(0, 9));
        result[9].add(0);

        List<Integer>[] invertedAdjList = GraphUtils.invertAdjList(adjList);

        assertArrayEquals(result, invertedAdjList);
    }
}
